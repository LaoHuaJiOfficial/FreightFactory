package prototypes.block.production;

import arc.Core;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.scene.ui.Image;
import arc.scene.ui.ImageButton;
import arc.scene.ui.ScrollPane;
import arc.scene.ui.layout.Stack;
import arc.scene.ui.layout.Table;
import arc.struct.EnumSet;
import arc.struct.Seq;
import arc.util.*;
import mindustry.Vars;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Building;
import mindustry.gen.Sounds;
import mindustry.type.*;
import mindustry.ui.ItemDisplay;
import mindustry.ui.Styles;
import mindustry.world.consumers.ConsumePowerDynamic;
import mindustry.world.draw.DrawBlock;
import mindustry.world.draw.DrawDefault;
import mindustry.world.meta.BlockFlag;
import prototypes.block.HeatBox.BlockF;
import prototypes.block.consumer.ConsumeItemDynamicF;
import prototypes.block.consumer.ConsumeLiquidsDynamicF;
import prototypes.block.consumer.ConsumeShow;
import prototypes.recipe.Recipe;
import utilities.ui.display.ArrowTempDisplay;
import utilities.ui.display.HeatDisplay;
import utilities.ui.display.LiquidDisplayF;
import utilities.ui.display.PowerDisplay;

import static mindustry.Vars.control;
import static mindustry.Vars.iconMed;

public class AssemblerBlock extends BlockF {

    public float warmupSpeed = 0.02f;
    public int[] capacities = {};
    public Seq<Recipe> recipeSeq = new Seq<>(4);
    public DrawBlock drawer = new DrawDefault();
    public TextureRegion timeIcon;

    public AssemblerBlock(String name) {
        super(name);
        HasHeat = false;
        HeatCapacity = 0f;

        itemCapacity = 30;
        liquidCapacity = 30f;
        update = true;
        solid = true;
        hasItems = true;
        ambientSound = Sounds.machine;
        sync = true;
        ambientSoundVolume = 0.03f;
        flags = EnumSet.of(BlockFlag.factory);
        drawArrow = false;

        //TODO maybe merge this?

        consume(new ConsumeItemDynamicF((AssemblerBlockBuild e) -> e.CurrentRecipeIndex != -1 ? recipeSeq.get(Math.min(e.CurrentRecipeIndex, recipeSeq.size - 1)).inputItems : null));
        consume(new ConsumeLiquidsDynamicF((AssemblerBlockBuild e) -> e.CurrentRecipeIndex != -1 ? recipeSeq.get(Math.min(e.CurrentRecipeIndex, recipeSeq.size - 1)).inputLiquids : null));
        consume(new ConsumePowerDynamic(p -> {
            AssemblerBlockBuild e = (AssemblerBlockBuild) p;
            return e.getInputPower();
        }));

        consume(new ConsumeShow(
            (AssemblerBlockBuild e) -> e.CurrentRecipeIndex != -1 ? recipeSeq.get(Math.min(e.CurrentRecipeIndex, recipeSeq.size - 1)).inputItems : null,
            (AssemblerBlockBuild e) -> e.CurrentRecipeIndex != -1 ? recipeSeq.get(Math.min(e.CurrentRecipeIndex, recipeSeq.size - 1)).inputLiquids : null
        ));

        configurable = true;
        saveConfig = true;

        config(Integer.class, (AssemblerBlockBuild tile, Integer i) -> {
            if (!configurable) return;

            if (tile.CurrentRecipeIndex == i) return;
            tile.CurrentRecipeIndex = i < 0 || i >= recipeSeq.size ? -1 : i;
            tile.progress = 0;
        });

        config(Recipe.class, (AssemblerBlockBuild tile, Recipe val) -> {
            if (!configurable) return;

            int next = recipeSeq.indexOf(val);
            if (tile.CurrentRecipeIndex == next) return;
            tile.CurrentRecipeIndex = next;
            tile.progress = 0;
        });

        configClear((AssemblerBlockBuild tile) -> tile.CurrentRecipeIndex = -1);

    }

    @Override
    public void load() {
        super.load();
        timeIcon = Core.atlas.find("ff-time-icon");
        drawer.load(this);

        for (Recipe r : recipeSeq){
            r.recipeName = Core.bundle.get("recipe." + r.name + ".name");
            r.recipeDescription = Core.bundle.get("recipe." + r.name + ".desc");
        }
    }


    @Override
    public void init() {
        super.init();
        capacities = new int[Vars.content.items().size];
        for (Recipe r : recipeSeq) {
            if (r.HasHeat()) {
                HasHeat = true;
            }
            if (r.outputHeatAmount > 0) {
                OutputHeat = true;
            }
            if (r.inputHeatAmount > 0) {
                InputHeat = true;
            }
            if (r.inputItems != null) {
                for (ItemStack stack : r.inputItems) {
                    capacities[stack.item.id] = Math.max(capacities[stack.item.id], stack.amount * 10);
                    itemCapacity = Math.max(itemCapacity, stack.amount * 2);
                }
            }

            if (r.inputLiquids != null || r.outputLiquids != null){
                hasLiquids = true;
            }
        }
    }

    @Override
    public void setBars() {
        super.setBars();
        removeBar("items");
        removeBar("liquid");
        removeBar("power");
        //todo add bar here..
    }


    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list) {
        drawer.drawPlan(this, plan, list);
    }

    @Override
    public TextureRegion[] icons() {
        return drawer.finalIcons(this);
    }

    @Override
    public void getRegionsToOutline(Seq<TextureRegion> out) {
        drawer.getRegionsToOutline(this, out);
    }

    public class AssemblerBlockBuild extends BuildF {
        public float progress;
        public float totalProgress;
        public float warmup;
        public int CurrentRecipeIndex = -1;


        @Override
        public void draw() {
            drawer.draw(this);
        }

        @Override
        public void drawLight() {
            super.drawLight();
            drawer.drawLight(this);
        }

        public Recipe current() {
            if (CurrentRecipeIndex != -1) {
                return recipeSeq.get(CurrentRecipeIndex);
            } else {
                return null;
            }

        }

        @Override
        public void created() {
            super.created();

            //todo can add some event here

        }

        public float getInputPower() {
            if (CurrentRecipeIndex != -1) {
                return current().inputPower;
            } else {
                return 0f;
            }
        }

        @Override
        public Object config() {
            return CurrentRecipeIndex;
        }

        @Override
        public void updateTile() {

            //this cv from UnitFactory.java

            if (!configurable) {
                CurrentRecipeIndex = 0;
            }

            if (CurrentRecipeIndex < 0 || CurrentRecipeIndex >= recipeSeq.size) {
                CurrentRecipeIndex = -1;
            }

            if (efficiency > 0 && CurrentRecipeIndex != -1) {

                LiquidStack[] OutputLiquids = current().outputLiquids;

                progress += getProgressIncrease(current().craftTime);
                warmup = Mathf.approachDelta(warmup, warmupTarget(), warmupSpeed);

                //continuously output based on efficiency
                if (OutputLiquids != null) {
                    float inc = getProgressIncrease(1f);
                    for (var output : OutputLiquids) {
                        handleLiquid(this, output.liquid, Math.min(output.amount * inc, liquidCapacity - liquids.get(output.liquid)));
                    }
                }

                if(wasVisible && Mathf.chanceDelta(current().updateEffectChance)){
                    current().updateEffect.at(x + Mathf.range(size * 4f), y + Mathf.range(size * 4));
                }

            } else {
                warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
            }

            //TODO may look bad, revert to edelta() if so
            totalProgress += warmup * Time.delta;

            if (progress >= 1f) {
                craft();
                if (current().outputHeatAmount > 0) {
                    HeatBox.HeatAdd(current().outputHeatAmount, current().outputTempThreshold);
                }
                if (current().inputHeatAmount > 0) {
                    HeatBox.HeatRemove(current().inputHeatAmount, current().inputTempThreshold);
                }
            }

            dumpOutputs();
        }

        @Override
        public void buildConfiguration(Table table) {

            Table selection = new Table();
            selection.left().defaults().size(60f);

            Table cont = (new Table()).top();
            cont.left().defaults().left().growX();

            Runnable rebuild = () -> {
                selection.clearChildren();
                if (recipeSeq.size > 0) {
                    for (Recipe r : recipeSeq) {

                        ImageButton button = new ImageButton();

                        button.table(info -> {
                            info.table(t -> {
                                //input draw left
                                info.add(new Stack() {{
                                    add(new Image(timeIcon).setScaling(Scaling.fit));

                                    Table t = new Table().left().bottom();
                                    t.add(Strings.autoFixed(r.craftTime / 60f , 1)+ "s").style(Styles.outlineLabel);
                                    add(t);
                                }}).size(iconMed).padRight(12);
                                t.left();
                                //input items
                                if (r.inputItems != null) {
                                    for (int i = 0; i < r.inputItems.length; i++) {
                                        ItemStack stack = r.inputItems[i];
                                        info.add(new ItemDisplay(stack.item, stack.amount, false)).padRight(3);
                                    }
                                }
                                //input liquids
                                if (r.inputLiquids != null) {
                                    for (int i = 0; i < r.inputLiquids.length; i++) {
                                        LiquidStack stack = r.inputLiquids[i];
                                        info.add(new LiquidDisplayF(stack.liquid, stack.amount * r.craftTime, false)).padRight(3);
                                    }
                                }
                                //todo payload here
                                //input heat
                                if (r.inputHeatAmount > 0 && !r.isCoolant) {
                                    info.add(new HeatDisplay(r.inputHeatAmount, false));
                                }
                                if (r.outputHeatAmount > 0 && r.isCoolant) {
                                    info.add(new HeatDisplay(r.outputHeatAmount, true));
                                }
                                //input power
                                if (r.inputPower > 0) {
                                    info.add(new PowerDisplay(r.inputPower, true));
                                }

                                //arrow
                                info.add(new ArrowTempDisplay(0, true));

                                //output items
                                if (r.outputItems != null) {
                                    for (int i = 0; i < r.outputItems.length; i++) {
                                        ItemStack stack = r.outputItems[i];
                                        info.add(new ItemDisplay(stack.item, stack.amount, false)).padRight(3);
                                    }
                                }
                                //output liquids
                                if (r.outputLiquids != null) {
                                    for (int i = 0; i < r.outputLiquids.length; i++) {
                                        LiquidStack stack = r.outputLiquids[i];
                                        info.add(new LiquidDisplayF(stack.liquid, stack.amount * r.craftTime, false)).padRight(3);
                                    }
                                }
                                //todo payload here
                                //output heat
                                if (r.inputHeatAmount > 0 && r.isCoolant) {
                                    info.add(new HeatDisplay(r.inputHeatAmount, false));
                                }
                                if (r.outputHeatAmount > 0 && !r.isCoolant) {
                                    info.add(new HeatDisplay(r.outputHeatAmount, true));
                                }

                                //output power
                                if (r.outputPower > 0) {
                                    info.add(new PowerDisplay(r.outputPower, false));
                                }
                            });
                        }).style(recipeSeq.indexOf(r) % 2 == 0? Styles.flati: Styles.cleari).left().expand().pad(10f)
                            .tooltip("[accent]"+ r.recipeName+ "[]\n[white]"+ r.recipeDescription+"[]" );

                        button.setStyle(Styles.clearNoneTogglei);
                        button.changed(() -> {
                            CurrentRecipeIndex = recipeSeq.indexOf(r);
                            this.items.clear();
                            this.liquids.clear();
                            this.update();
                            configure(CurrentRecipeIndex);
                            control.input.config.hideConfig();
                        });
                        button.update(() ->
                            button.setChecked(CurrentRecipeIndex == recipeSeq.indexOf(r))
                        );
                        cont.add(button);
                        cont.row();
                    }
                }

            };
            rebuild.run();

            Table main = (new Table()).background(Styles.black6);
            main.add(selection).left().row();
            ScrollPane pane = new ScrollPane(cont, Styles.smallPane);
            pane.setScrollingDisabled(true, false);
            if (this.block != null) {
                pane.setScrollYForce(this.block.selectScroll);
                pane.update(() -> this.block.selectScroll = pane.getScrollY());
            }
            pane.setOverscroll(false, false);
            main.add(pane).maxHeight(500.0F);
            table.top().add(main);
        }

        @Override
        public boolean shouldConsume() {
            if (CurrentRecipeIndex == -1) return false;

            if (current().outputItems != null) {
                for (var output : current().outputItems) {
                    if (items.get(output.item) + output.amount > itemCapacity) {
                        return false;
                    }
                }
            }
            if (current().outputLiquids != null && !current().ignoreLiquidFullness) {
                boolean allFull = true;
                for (var output : current().outputLiquids) {
                    if (liquids.get(output.liquid) >= liquidCapacity - 0.001f) {
                        if (!current().dumpExtraLiquid) {
                            return false;
                        }
                    } else {
                        //if there's still space left, it's not full for all liquids
                        allFull = false;
                    }
                }

                //if there is no space left for any liquid, it can't reproduce
                if (allFull) {
                    return false;
                }
            }

            return enabled;
        }

        @Override
        public int getMaximumAccepted(Item item) {
            return capacities[item.id];
        }

        @Override
        public boolean acceptItem(Building source, Item item) {
            return (CurrentRecipeIndex != -1 && current().inputItems != null && this.items.get(item) < this.getMaximumAccepted(item) &&
                Structs.contains(current().inputItems, stack -> stack.item == item));
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid) {
            return (CurrentRecipeIndex != -1 && current().inputLiquids != null && this.liquids.get(liquid) < this.block.liquidCapacity) &&
                Structs.contains(current().inputLiquids, stack -> stack.liquid == liquid);
        }


        @Override
        public float getProgressIncrease(float baseTime) {
            LiquidStack[] OutputLiquids = current().outputLiquids;
            if (current().ignoreLiquidFullness) {
                return super.getProgressIncrease(baseTime);
            }

            //limit progress increase by maximum amount of liquid it can produce
            float scaling = 1f, max = 1f;
            if (OutputLiquids != null) {
                max = 0f;
                for (var s : OutputLiquids) {
                    float value = (liquidCapacity - liquids.get(s.liquid)) / (s.amount * edelta());
                    scaling = Math.min(scaling, value);
                    max = Math.max(max, value);
                }
            }

            //when dumping excess take the maximum value instead of the minimum.
            return super.getProgressIncrease(baseTime) * (current().dumpExtraLiquid ? Math.min(max, 1f) : scaling);
        }

        public float warmupTarget() {
            return 1f;
        }

        @Override
        public float warmup() {
            return warmup;
        }

        @Override
        public float totalProgress() {
            return totalProgress;
        }

        public void craft() {
            if (CurrentRecipeIndex != -1) {
                consume();
                ItemStack[] OutputItems = current().outputItems;
                if (OutputItems != null) {
                    for (var output : OutputItems) {
                        for (int i = 0; i < output.amount; i++) {
                            offload(output.item);
                        }
                    }
                }

                if(wasVisible){
                    current().craftEffect.at(x, y);
                }

                progress %= 1f;
            }
        }

        public void dumpOutputs() {
            if (CurrentRecipeIndex != -1) {

                ItemStack[] OutputItems = current().outputItems;
                LiquidStack[] OutputLiquids = current().outputLiquids;
                int[] liquidOutputDirections = current().liquidOutputDirections;

                if (OutputItems != null && timer(timerDump, dumpTime / timeScale)) {
                    for (ItemStack output : OutputItems) {
                        dump(output.item);
                    }
                }

                if (OutputLiquids != null) {
                    for (int i = 0; i < OutputLiquids.length; i++) {
                        int dir = liquidOutputDirections.length > i ? liquidOutputDirections[i] : -1;

                        dumpLiquid(OutputLiquids[i].liquid, 2f, dir);
                    }
                }
            }
        }
    }
}
