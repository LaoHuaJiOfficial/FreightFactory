package world.block.consumer;

import arc.func.Func;
import arc.scene.ui.layout.Table;
import mindustry.gen.Building;
import mindustry.type.ItemStack;
import mindustry.ui.ItemImage;
import mindustry.ui.ReqImage;
import mindustry.world.Block;
import mindustry.world.consumers.Consume;

public class ConsumeItemDynamicF extends Consume {
    public final Func<Building, ItemStack[]> items;

    @SuppressWarnings("unchecked")
    public <T extends Building> ConsumeItemDynamicF(Func<T, ItemStack[]> items){
        this.items = (Func<Building, ItemStack[]>)items;
    }

    @Override
    public void apply(Block block){
        if(items != null){
            block.hasItems = true;
            block.acceptsItems = true;
        }
    }

    @Override
    public void build(Building build, Table table){
        if(items != null && items.get(build) != null){
            ItemStack[][] current = {items.get(build)};

            table.table(cont -> {
                table.update(() -> {
                    if(current[0] != items.get(build)){
                        rebuild(build, cont);
                        current[0] = items.get(build);
                    }
                });

                rebuild(build, cont);
            });
        }
    }

    private void rebuild(Building build, Table table){
        if(items != null && items.get(build) != null){
            table.clear();
            int i = 0;

            for(ItemStack stack : items.get(build)){
                table.add(new ReqImage(new ItemImage(stack.item.uiIcon, Math.round(stack.amount * multiplier.get(build))),
                    () -> build.items != null && build.items.has(stack.item, Math.round(stack.amount * multiplier.get(build))))).padRight(8).left();
                if(++i % 4 == 0) table.row();
            }
        }
    }

    @Override
    public void trigger(Building build){
        if(items != null && items.get(build) != null){
            for(ItemStack stack : items.get(build)){
                build.items.remove(stack.item, Math.round(stack.amount * multiplier.get(build)));
            }
        }
    }

    @Override
    public float efficiency(Building build){
        if(items != null && items.get(build) != null){
            return build.consumeTriggerValid() || build.items.has(items.get(build), multiplier.get(build)) ? 1f : 0f;
        }else {
            return 1f;
        }
    }
}
