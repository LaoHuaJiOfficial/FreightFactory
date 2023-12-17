package world.block.production;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.scene.style.TextureRegionDrawable;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.*;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Building;
import mindustry.gen.Icon;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.logic.LAccess;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.type.Liquid;
import mindustry.type.LiquidStack;
import mindustry.ui.ItemDisplay;
import mindustry.ui.LiquidDisplay;
import mindustry.ui.Styles;
import mindustry.world.Block;
import mindustry.world.blocks.ItemSelection;
import mindustry.world.consumers.ConsumeItemDynamic;
import mindustry.world.consumers.ConsumeLiquidsDynamic;
import mindustry.world.meta.Stat;

import static mindustry.Vars.state;

public class Assembler extends Block {
    public int[] capacities = {};

    public Seq<AssemblerPlan> plans = new Seq<>(4);


    public float warmupSpeed = 0.019f;

    public Assembler(String name){
        super(name);
        update = true;
        hasPower = true;
        hasItems = true;
        solid = true;
        configurable = true;
        clearOnDoubleTap = true;
        ambientSound = Sounds.respawning;

        config(Integer.class, (AssemblerBuild tile, Integer i) -> {
            if(!configurable) return;

            if(tile.currentPlan == i) return;
            tile.currentPlan = i < 0 || i >= plans.size ? -1 : i;
            tile.progress = 0;
        });

        config(ItemStack.class, (AssemblerBuild tile, ItemStack val) -> {
            if(!configurable) return;

            int next = plans.indexOf(p -> p.OutputItem == val);
            if(tile.currentPlan == next) return;
            tile.currentPlan = next;
            tile.progress = 0;
        });


        consume(new ConsumeItemDynamic((AssemblerBuild e) -> e.currentPlan != -1 ? plans.get(Math.min(e.currentPlan, plans.size - 1)).InputItem : ItemStack.empty));
        consume(new ConsumeLiquidsDynamic((AssemblerBuild e) -> e.currentPlan != -1 ? plans.get(Math.min(e.currentPlan, plans.size - 1)).InputLiquid : LiquidStack.empty));
    }

    @Override
    public void init(){

        capacities = new int[Vars.content.items().size];
        for(AssemblerPlan plan : plans){
            if (plan.OutputItem != null){
                for(ItemStack stack : plan.InputItem){
                    capacities[stack.item.id] = Math.max(capacities[stack.item.id], stack.amount * 2);
                    itemCapacity = Math.max(itemCapacity, stack.amount * 2);
                }
            }
            if (plan.OutputLiquid != null){
                hasLiquids = true;

            }
        }

        super.init();
    }

    @Override
    public void setBars(){
        super.setBars();
    }

    @Override
    public boolean outputsItems(){
        return false;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.remove(Stat.itemCapacity);

        stats.add(Stat.output, table -> {
            table.row();

            for(var plan : plans){
                table.table(Styles.grayPanel, t -> {

                    if(plan.OutputItem.item.isHidden() || plan.OutputLiquid.isHidden()){
                        t.image(Icon.cancel).color(Pal.remove).size(40);
                        return;
                    }
                    if(plan.OutputItem.item.unlockedNow() || plan.OutputLiquid.unlockedNow()){
                        t.image(plan.OutputItem.item.uiIcon).size(40).pad(10f).left().scaling(Scaling.fit);
                        t.table(info -> {
                            info.add(plan.OutputItem.item.localizedName).left();
                            info.row();
                            info.add(Strings.autoFixed(plan.time / 60f, 1) + " " + Core.bundle.get("unit.seconds")).color(Color.lightGray);
                        }).left();

                        t.image(plan.OutputLiquid.uiIcon).size(40).pad(10f).padLeft(4f).scaling(Scaling.fit);
                        t.table(info -> {
                            info.add(plan.OutputLiquid.localizedName).padLeft(4f);
                            info.row();
                            info.add(Strings.autoFixed(plan.time / 60f, 1) + " " + Core.bundle.get("unit.seconds")).color(Color.lightGray);
                        }).padLeft(4f);

                        t.table(req -> {
                            req.right();
                            for(int i = 0; i < plan.InputItem.length; i++){
                                if(i % 6 == 0){
                                    req.row();
                                }

                                ItemStack stack = plan.InputItem[i];
                                req.add(new ItemDisplay(stack.item, stack.amount, false)).pad(5);
                            }
                            req.row();
                            for(int i = 0; i < plan.InputLiquid.length; i++){
                                if(i % 6 == 0){
                                    req.row();
                                }

                                LiquidStack stack = plan.InputLiquid[i];
                                req.add(new LiquidDisplay(stack.liquid, stack.amount, false)).pad(5);
                            }
                        }).right().grow().pad(10f);
                    }else{
                        t.image(Icon.lock).color(Pal.darkerGray).size(40);
                    }
                }).growX().pad(5);
                table.row();
            }
        });
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region};
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
    }

    public static class AssemblerPlan{

        public ItemStack[] InputItem;
        public LiquidStack[] InputLiquid;
        public ItemStack OutputItem;

        public Liquid OutputLiquid;
        public float time;
        public float powerUsage;
        public AssemblerPlan(float time, ItemStack[] InputItem, LiquidStack[] InputLiquid, ItemStack OutputItem, Liquid OutputLiquid, float powerUsage){
            this.time = time;
            this.InputItem = InputItem;
            this.InputLiquid = InputLiquid;
            this.OutputItem = OutputItem;
            this.OutputLiquid = OutputLiquid;
            this.powerUsage = powerUsage;
        }
        public AssemblerPlan(float time, ItemStack[] InputItem, ItemStack OutputItem, Liquid OutputLiquid, float powerUsage){
            this.time = time;
            this.InputItem = InputItem;
            this.OutputItem = OutputItem;
            this.OutputLiquid = OutputLiquid;
            this.powerUsage = powerUsage;
        }

        public AssemblerPlan(float time, ItemStack[] InputItem, ItemStack OutputItem, float powerUsage){
            this.time = time;
            this.InputItem = InputItem;
            this.OutputItem = OutputItem;
            this.powerUsage = powerUsage;
        }
    }

    public class AssemblerBuild extends Building {

        public float progress;
        public float totalProgress;
        public float warmup;
        public int currentPlan = -1;

        @Override
        public Object senseObject(LAccess sensor){
            if(sensor == LAccess.config) return currentPlan == -1 ? null : plans.get(currentPlan).OutputItem.item;
            return super.senseObject(sensor);
        }

        @Override
        public boolean shouldActiveSound(){
            return shouldConsume();
        }

        @Override
        public double sense(LAccess sensor){
            if(sensor == LAccess.itemCapacity) return Mathf.round(itemCapacity * state.rules.unitCost(team));
            return super.sense(sensor);
        }

        @Override
        public void buildConfiguration(Table table){
            Seq<Item> items = Seq.with(plans).map(p -> p.OutputItem.item).retainAll(p -> p.unlockedNow() && !p.isHidden());

            if(items.any()){
                ItemSelection.buildTable(Assembler.this, table, items, () -> currentPlan == -1 ? null : plans.get(currentPlan).OutputItem.item, item -> configure(plans.indexOf(u -> u.OutputItem.item == item)), selectionRows, selectionColumns);
            }else{
                table.table(Styles.black3, t -> t.add("@none").color(Color.lightGray));
            }
        }

        @Override
        public void display(Table table){
            super.display(table);

            TextureRegionDrawable reg = new TextureRegionDrawable();

            table.row();
            table.table(t -> {
                t.left();
                t.image().update(i -> {
                    i.setDrawable(currentPlan == -1 ? Icon.cancel : reg.set(plans.get(currentPlan).OutputItem.item.uiIcon));
                    i.setScaling(Scaling.fit);
                    i.setColor(currentPlan == -1 ? Color.lightGray : Color.white);
                }).size(32).padBottom(-4).padRight(2);
                t.label(() -> currentPlan == -1 ? "@none" : plans.get(currentPlan).OutputItem.item.localizedName).wrap().width(230f).color(Color.lightGray);
            }).left();
        }

        @Override
        public Object config(){
            return currentPlan;
        }

        @Override
        public void draw(){
            Draw.rect(region, x, y);
        }

        @Override
        public void updateTile(){
            if(!configurable){
                currentPlan = 0;
            }

            if(currentPlan < 0 || currentPlan >= plans.size){
                currentPlan = -1;
            }

            if(currentPlan != -1){
                AssemblerPlan plan = plans.get(currentPlan);

                //make sure to reset plan when the unit got banned after placement
                if(plan.OutputItem.item.isHidden()){
                    currentPlan = -1;
                    return;
                }

                if(efficiency > 0){

                    progress += getProgressIncrease(plan.time);
                    warmup = Mathf.approachDelta(warmup, warmupTarget(), warmupSpeed);

                    if(plan.OutputLiquid != null){
                        float inc = getProgressIncrease(1f);
                        handleLiquid(this, plan.OutputLiquid, Math.min(1.5f * inc, liquidCapacity - liquids.get(plan.OutputLiquid)));
                    }

                }else{
                    warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
                }

                totalProgress += warmup * Time.delta;

                if(progress >= 1f){
                    craft();
                }

                dumpOutputs();
            }
        }

        public float getProgressIncrease(float baseTime){
            //limit progress increase by maximum amount of liquid it can produce
            float scaling = 1f;
            AssemblerPlan plan = plans.get(currentPlan);

            if(plan.OutputLiquid != null){
                float value = (liquidCapacity - liquids.get(plan.OutputLiquid)) / 20f;
                scaling = Math.min(scaling, value);
            }

            //when dumping excess take the maximum value instead of the minimum.
            return super.getProgressIncrease(baseTime) * scaling;
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
            consume();

            AssemblerPlan plan = plans.get(currentPlan);

            if(plan.OutputItem != null){
                for(int i = 0; i < plan.OutputItem.amount; i++){
                    offload(plan.OutputItem.item);
                }
            }
            progress %= 1f;
        }

        public void dumpOutputs(){
            AssemblerPlan plan = plans.get(currentPlan);

            if(plan.OutputItem != null && timer(timerDump, dumpTime / timeScale)){
                dump(plan.OutputItem.item);
            }

            if(plan.OutputLiquid != null){
                dumpLiquid(plan.OutputLiquid);
            }
        }

        @Override
        public boolean shouldConsume(){
            if(currentPlan == -1) return false;

            AssemblerPlan plan = plans.get(currentPlan);

            if(plan.OutputItem != null){
                if(items.get(plan.OutputItem.item) + plan.OutputItem.amount > itemCapacity){
                    return false;
                }
            }
            if(plan.OutputLiquid != null){
                if(liquids.get(plan.OutputLiquid) >= liquidCapacity - 0.001f){
                    return false;
                }
            }
            return enabled;
        }

        @Override
        public int getMaximumAccepted(Item item){
            return Mathf.round(capacities[item.id] * state.rules.unitCost(team));
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            return currentPlan != -1 && items.get(item) < getMaximumAccepted(item) &&
                Structs.contains(plans.get(currentPlan).InputItem, stack -> stack.item == item);
        }

        @Override
        public byte version(){
            return 2;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.s(currentPlan);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            currentPlan = read.s();
        }
    }
}
