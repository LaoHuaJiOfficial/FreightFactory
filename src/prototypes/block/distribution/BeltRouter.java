package prototypes.block.distribution;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.struct.Queue;
import arc.util.Eachable;
import arc.util.Nullable;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.content.Items;
import mindustry.entities.TargetPriority;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Building;
import mindustry.gen.Unit;
import mindustry.type.Item;
import mindustry.ui.Bar;
import mindustry.world.DirectionalItemBuffer;
import mindustry.world.Edges;
import mindustry.world.Tile;
import mindustry.world.blocks.ItemSelection;
import mindustry.world.meta.BlockGroup;
import prototypes.block.HeatBox.BlockF;

import static mindustry.Vars.*;
import static mindustry.Vars.content;

public class BeltRouter extends BlockF {
    public TextureRegion baseRegion, topRegion;
    public float itemPerSecond;


    public BeltRouter(String name){
        super(name);

        group = BlockGroup.transportation;
        update = true;
        solid = false;
        hasItems = true;
        unloadable = false;
        itemCapacity = 1;
        noUpdateDisabled = true;
        configurable = true;
        saveConfig = true;
        rotate = true;
        clearOnDoubleTap = true;
        underBullets = true;
        priority = TargetPriority.transport;

        config(Item.class, (BeltRouterBuild tile, Item item) -> tile.sortItem = item);
        configClear((BeltRouterBuild tile) -> tile.sortItem = null);
    }

    @Override
    public void load(){
        super.load();

        baseRegion = Core.atlas.find(name + "-base");
        topRegion = Core.atlas.find(name + "-top");
    }

    @Override
    public void setBars(){
        super.setBars();
        removeBar("items");
    }

    @Override
    public void drawPlanConfig(BuildPlan plan, Eachable<BuildPlan> list){
        drawPlanConfigCenter(plan, plan.config, "center", true);
    }

    @Override
    public boolean outputsItems(){
        return true;
    }

    @Override
    public int minimapColor(Tile tile){
        var build = (BeltRouterBuild)tile.build;
        return build == null || build.sortItem == null ? 0 : build.sortItem.color.rgba();
    }

    @Override
    protected TextureRegion[] icons(){
        return new TextureRegion[]{baseRegion, topRegion};
    }

    public class BeltRouterBuild extends BuildF {
        public @Nullable Item sortItem;
        public Queue<Item> ItemQueueBuffer = new Queue<>(10);
        public float speed = Time.toSeconds / itemPerSecond;
        public float progress;

        public short acceptFront, acceptLeft, acceptRight;


        @Override
        public void configured(Unit player, Object value){
            super.configured(player, value);

            if(!headless){
                renderer.minimap.update(tile);
            }
        }

        @Override
        public void draw(){
            Draw.rect(baseRegion, x, y);
            if (sortItem == null){
                Draw.rect(topRegion, x, y, rotation * 90);
            }else {
                Draw.color(sortItem.color);
                Draw.rect(topRegion, x, y, rotation * 90);
            }
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            return
                !(ItemQueueBuffer.size >= 10) &&
                (Edges.getFacingEdge(source.tile(), tile).relativeTo(tile) == rotation);
        }

        @Override
        public void handleItem(Building source, Item item){
            ItemQueueBuffer.addFirst(item);
        }

        @Override
        public void updateTile(){
            progress += edelta() / speed * 2f;

            if(ItemQueueBuffer.size > 0){
                if(progress >= (1f - 1f/speed)){
                    var target = target();
                    if(target != null){
                        target.handleItem(this, ItemQueueBuffer.last());
                        ItemQueueBuffer.removeLast();
                        progress %= (1f - 1f/speed);
                    }
                }
            }else{
                progress = 0;
            }
        }

        @Nullable
        public Building target(){
            if(ItemQueueBuffer.last() == null) return null;

            int dump = cdump;

            for(int i = 0; i < proximity.size; i++){
                Building other = proximity.get((i + dump) % proximity.size);
                int rel = relativeTo(other);

                if(!(sortItem != null && (ItemQueueBuffer.last() == sortItem) != (rel == rotation)) && !(rel == (rotation + 2) % 4) && other.team == team && other.acceptItem(this, ItemQueueBuffer.last())){
                    incrementDump(proximity.size);
                    return other;
                }

                incrementDump(proximity.size);
            }

            return null;
        }

        @Override
        public void buildConfiguration(Table table){
            ItemSelection.buildTable(BeltRouter.this, table, content.items(), () -> sortItem, this::configure, selectionRows, selectionColumns);


        }

        @Override
        public Item config(){
            return sortItem;
        }

        @Override
        public byte version(){
            return 2;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.s(sortItem == null ? -1 : sortItem.id);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            sortItem = content.item(read.s());

            if(revision == 1){
                new DirectionalItemBuffer(20).read(read);
            }
        }
    }
}
