package world.block.production;

import HeatBox.BlockF;
import HeatBox.HeatBoxEntity;
import arc.graphics.Color;
import arc.math.Mathf;
import arc.scene.ui.ImageButton;
import arc.scene.ui.ScrollPane;
import arc.scene.ui.layout.Table;
import arc.struct.EnumSet;
import arc.struct.Seq;
import arc.util.Nullable;
import arc.util.Structs;
import arc.util.Time;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.gen.Building;
import mindustry.gen.Iconc;
import mindustry.gen.Sounds;
import mindustry.type.*;
import mindustry.ui.ItemDisplay;
import mindustry.ui.Styles;
import mindustry.world.Block;
import mindustry.world.consumers.ConsumePowerDynamic;
import mindustry.world.meta.BlockFlag;
import ui.LiquidDisplayF;
import ui.PowerDisplay;
import world.block.consumer.ConsumeItemDynamicF;
import world.block.consumer.ConsumeLiquidsDynamicF;

public class AssemblerBlock extends BlockF {

    public float warmupSpeed = 0.02f;
    public int[] capacities = {};
    public Seq<Recipe> recipes = new Seq<>(4);
    public AssemblerBlock(String name){
        super(name);
        HeatCapacity = 10f;
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

        consume(new ConsumeItemDynamicF((AssemblerBlockBuild e) -> e.CurrentRecipeIndex != -1 ? recipes.get(Math.min(e.CurrentRecipeIndex, recipes.size - 1)).InputItems: null));
        consume(new ConsumeLiquidsDynamicF((AssemblerBlockBuild e) -> e.CurrentRecipeIndex != -1 ? recipes.get(Math.min(e.CurrentRecipeIndex, recipes.size - 1)).InputLiquids: null));
        consume(new ConsumePowerDynamic(p ->{AssemblerBlockBuild e = (AssemblerBlockBuild) p;return e.getInputPower();}));

        configurable = true;
        saveConfig = true;

        config(Integer.class, (AssemblerBlockBuild tile, Integer i) -> {
            if(!configurable) return;

            if(tile.CurrentRecipeIndex == i) return;
            tile.CurrentRecipeIndex = i < 0 || i >= recipes.size ? -1 : i;
            tile.progress = 0;
        });

        config(Recipe.class, (AssemblerBlockBuild tile, Recipe val) -> {
            if(!configurable) return;

            int next = recipes.indexOf(val);
            if(tile.CurrentRecipeIndex == next) return;
            tile.CurrentRecipeIndex = next;
            tile.progress = 0;
        });
    }

    @Override
    public void init(){
        super.init();
        HeatCapacity = 10f;
        capacities = new int[Vars.content.items().size];
        for(Recipe r: recipes){
            if (r.HasHeat()){
                HasHeat = true;
            }
            if (r.OutputHeatAmount > 0){
                OutputHeat = true;
            }
            if (r.InputHeatAmount > 0){
                InputHeat = true;
            }
            if (r.InputItems != null){
                for(ItemStack stack: r.InputItems){
                    capacities[stack.item.id] = Math.max(capacities[stack.item.id], stack.amount * 10);
                    itemCapacity = Math.max(itemCapacity, stack.amount * 2);
                }
            }
        }

        super.init();
    }

    @Override
    public void setBars(){
        super.setBars();
        removeBar("items");
        removeBar("liquid");
        removeBar("power");
        //todo add bar here..
    }

    public static class Recipe {
        public @Nullable ItemStack[] InputItems;
        public @Nullable ItemStack[] OutputItems;
        public @Nullable LiquidStack[] InputLiquids;
        public @Nullable LiquidStack[] OutputLiquids;
        public float InputHeatAmount = 0f;
        public float InputTempThreshold = 300f;
        public float OutputHeatAmount = 0f;
        public float OutputTempThreshold = 300f;
        public @Nullable PayloadStack[] InputPayloads;
        public @Nullable PayloadStack[] OutputPayloads;
        public @Nullable float InputPower;
        public @Nullable float OutputPower;
        public float CraftTime = 60f;

        public int[] liquidOutputDirections = {-1};
        public boolean dumpExtraLiquid = true;
        public boolean ignoreLiquidFullness = false;

        public Effect craftEffect = Fx.none;
        public Effect updateEffect = Fx.none;
        public float updateEffectChance = 0.04f;
        public @Nullable Color TintColor;

        public boolean HasHeat(){
            return InputHeatAmount > 0 || OutputHeatAmount > 0;
        }

    }
    public class AssemblerBlockBuild extends BuildF{
        public float progress;
        public float totalProgress;
        public float warmup;
        public int CurrentRecipeIndex = -1;

        public Recipe current(){
            if(CurrentRecipeIndex != -1){
                return recipes.get(CurrentRecipeIndex);
            }else {
                return null;
            }

        }

        @Override
        public void created(){
            //todo can add some event here
            super.created();
        }

        public float getInputPower(){
            if(CurrentRecipeIndex != -1){
                return current().InputPower;
            }else {
                return 0f;
            }
        }

        @Override
        public Object config(){
            return CurrentRecipeIndex;
        }

        @Override
        public void updateTile(){

            //this cv from UnitFactory.java

            if(!configurable){
                CurrentRecipeIndex = 0;
            }

            if(CurrentRecipeIndex < 0 || CurrentRecipeIndex >= recipes.size){
                CurrentRecipeIndex = -1;
            }

            if(efficiency > 0 && CurrentRecipeIndex != -1){

                LiquidStack[] OutputLiquids = current().OutputLiquids;

                progress += getProgressIncrease(current().CraftTime);
                warmup = Mathf.approachDelta(warmup, warmupTarget(), warmupSpeed);

                //continuously output based on efficiency
                if(OutputLiquids != null){
                    float inc = getProgressIncrease(1f);
                    for(var output : OutputLiquids){
                        handleLiquid(this, output.liquid, Math.min(output.amount * inc, liquidCapacity - liquids.get(output.liquid)));
                    }
                }

            }else{
                warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
            }

            //TODO may look bad, revert to edelta() if so
            totalProgress += warmup * Time.delta;

            if(progress >= 1f){
                craft();
                HeatBox.HeatAdd(current().OutputHeatAmount, current().OutputTempThreshold);
                HeatBox.HeatRemove(current().InputHeatAmount, current().InputTempThreshold);
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
                if (recipes.size > 0){
                    for (Recipe r: recipes){
                        ImageButton button = new ImageButton();

                        button.table(info -> {
                            info.table(t ->{
                                //input draw left
                                t.left();
                                //input items
                                if (r.InputItems != null) {
                                    for(int i = 0; i < r.InputItems.length; i++){
                                        ItemStack stack = r.InputItems[i];
                                        info.add(new ItemDisplay(stack.item, stack.amount, false)).left().pad(5);
                                    }
                                }
                                //input liquids
                                if (r.InputLiquids != null) {
                                    for(int i = 0; i < r.InputLiquids.length; i++){
                                        LiquidStack stack = r.InputLiquids[i];
                                        info.add(new LiquidDisplayF(stack.liquid, stack.amount, false)).left().pad(5);
                                    }
                                }
                                //todo payload here
                                //input power
                                if (r.InputPower > 0) {
                                    info.add(new PowerDisplay(r.InputPower));
                                }

                                //arrow
                                info.add("" + Iconc.right).pad(10f);

                                //output items
                                if (r.OutputItems != null) {
                                    for(int i = 0; i < r.OutputItems.length; i++){
                                        ItemStack stack = r.OutputItems[i];
                                        info.add(new ItemDisplay(stack.item, stack.amount, false)).left().pad(5);
                                    }
                                }
                                //output liquids
                                if (r.OutputLiquids != null) {
                                    for(int i = 0; i < r.OutputLiquids.length; i++){
                                        LiquidStack stack = r.OutputLiquids[i];
                                        info.add(new LiquidDisplayF(stack.liquid, stack.amount, false)).left().pad(5);
                                    }
                                }
                                //todo payload here
                                //output power
                                if (r.OutputPower > 0) {
                                    info.add(new PowerDisplay(r.OutputPower));
                                }
                            });
                        }).grow().pad(10f);

                        button.setStyle(Styles.clearNoneTogglei);
                        button.changed(() -> {
                            CurrentRecipeIndex = recipes.indexOf(r);
                            this.items.clear();
                            this.liquids.clear();
                            configure(CurrentRecipeIndex);
                        });
                        button.update(() ->
                            button.setChecked(CurrentRecipeIndex == recipes.indexOf(r))
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
        public boolean shouldConsume(){
            if(CurrentRecipeIndex == -1) return false;

            if(current().OutputItems != null){
                for(var output : current().OutputItems){
                    if(items.get(output.item) + output.amount > itemCapacity){
                        return false;
                    }
                }
            }
            if(current().OutputLiquids != null && !current().ignoreLiquidFullness){
                boolean allFull = true;
                for(var output : current().OutputLiquids){
                    if(liquids.get(output.liquid) >= liquidCapacity - 0.001f){
                        if(!current().dumpExtraLiquid){
                            return false;
                        }
                    }else{
                        //if there's still space left, it's not full for all liquids
                        allFull = false;
                    }
                }

                //if there is no space left for any liquid, it can't reproduce
                if(allFull){
                    return false;
                }
            }

            return enabled;
        }

        @Override
        public int getMaximumAccepted(Item item){
            return capacities[item.id];
        }

        @Override
        public boolean acceptItem(Building source, Item item) {
            return (CurrentRecipeIndex != -1 && current().InputItems != null && this.items.get(item) < this.getMaximumAccepted(item) &&
                Structs.contains(current().InputItems, stack -> stack.item == item));
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid) {
            return (CurrentRecipeIndex != -1 && current().InputLiquids != null && this.liquids.get(liquid) < this.block.liquidCapacity) &&
                Structs.contains(current().InputLiquids, stack -> stack.liquid == liquid);
        }


        @Override
        public float getProgressIncrease(float baseTime){
            LiquidStack[] OutputLiquids = current().OutputLiquids;
            if(current().ignoreLiquidFullness){
                return super.getProgressIncrease(baseTime);
            }

            //limit progress increase by maximum amount of liquid it can produce
            float scaling = 1f, max = 1f;
            if(OutputLiquids != null){
                max = 0f;
                for(var s : OutputLiquids){
                    float value = (liquidCapacity - liquids.get(s.liquid)) / (s.amount * edelta());
                    scaling = Math.min(scaling, value);
                    max = Math.max(max, value);
                }
            }

            //when dumping excess take the maximum value instead of the minimum.
            return super.getProgressIncrease(baseTime) * (current().dumpExtraLiquid ? Math.min(max, 1f) : scaling);
        }

        public float warmupTarget(){
            return 1f;
        }

        @Override
        public float warmup(){
            return warmup;
        }

        @Override
        public float totalProgress(){
            return totalProgress;
        }

        public void craft(){
            if (CurrentRecipeIndex != -1){
                consume();
                ItemStack[] OutputItems = current().OutputItems;
                if(OutputItems != null){
                    for(var output : OutputItems){
                        for(int i = 0; i < output.amount; i++){
                            offload(output.item);
                        }
                    }
                }

                progress %= 1f;
            }
        }

        public void dumpOutputs(){
            if (CurrentRecipeIndex != -1){

                ItemStack[] OutputItems = current().OutputItems;
                LiquidStack[] OutputLiquids = current().OutputLiquids;
                int[] liquidOutputDirections = current().liquidOutputDirections;

                if(OutputItems != null && timer(timerDump, dumpTime / timeScale)){
                    for(ItemStack output : OutputItems){
                        dump(output.item);
                    }
                }

                if(OutputLiquids != null){
                    for(int i = 0; i < OutputLiquids.length; i++){
                        int dir = liquidOutputDirections.length > i ? liquidOutputDirections[i] : -1;

                        dumpLiquid(OutputLiquids[i].liquid, 2f, dir);
                    }
                }
            }
        }
    }
}
