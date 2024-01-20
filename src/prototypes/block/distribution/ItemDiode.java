package prototypes.block.distribution;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.util.Eachable;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.entities.TargetPriority;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Building;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.world.Block;
import mindustry.world.blocks.ItemSelection;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Env;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

import static mindustry.Vars.content;

public class ItemDiode extends Block {

    public float speed = 1f;
    public boolean allowCoreUnload = false;

    public ItemDiode(String name) {
        super(name);

        group = BlockGroup.transportation;
        update = true;
        solid = true;
        hasItems = true;
        configurable = true;
        saveConfig = true;
        rotate = true;
        itemCapacity = 0;
        noUpdateDisabled = true;
        unloadable = false;
        isDuct = true;
        envDisabled = Env.none;
        clearOnDoubleTap = true;
        priority = TargetPriority.transport;

        config(Item.class, (ItemDiodeBuild tile, Item item) -> tile.unloadItem = item);
        configClear((ItemDiodeBuild tile) -> tile.unloadItem = null);
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.add(Stat.speed, 60f / speed, StatUnit.itemsSecond);
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list) {
        Draw.rect(region, plan.drawx(), plan.drawy());
        drawPlanConfig(plan, list);
    }

    @Override
    public void drawPlanConfig(BuildPlan plan, Eachable<BuildPlan> list) {
        drawPlanConfigCenter(plan, plan.config, "duct-unloader-center");
    }

    @Override
    public void setBars() {
        super.setBars();
        removeBar("items");
    }

    @Override
    public TextureRegion[] icons() {
        return new TextureRegion[]{region};
    }

    public class ItemDiodeBuild extends Building {
        public float unloadTimer = 0f;
        public Item unloadItem = null;
        public int offset = 0;

        @Override
        public void updateTile() {
            if ((unloadTimer += edelta()) >= speed) {
                Building front = front(), back = back();

                if (unloadItem != null && front != null && back != null && back.items != null && front.team == team && back.team == team && back.canUnload() && (allowCoreUnload || !(back instanceof CoreBlock.CoreBuild || (back instanceof StorageBlock.StorageBuild sb && sb.linkedCore != null)))) {
                    if (back.items.has(unloadItem) && front.acceptItem(this, unloadItem)) {
                        front.handleItem(this, unloadItem);
                        back.items.remove(unloadItem, 1);
                        back.itemTaken(unloadItem);
                    }
                }

                unloadTimer %= speed;
            }
        }

        @Override
        public void draw() {
            Draw.rect(region, x, y);


        }

        @Override
        public void drawSelect() {
            drawIO();

            Draw.reset();
        }

        private void drawIO() {
            Building front = front(), back = back();

            if (unloadItem != null && front != null && back != null && back.items.has(unloadItem.id)) {
                float alpha = Math.abs(100f - (Time.time * 2f) % 100f) / 100f;

                float ix = front.x;
                float iy = front.y;
                float ox = back.x;
                float oy = back.y;
                float px = Mathf.lerp(ix, ox, alpha);
                float py = Mathf.lerp(iy, oy, alpha);

                //background
                Draw.z(Layer.blockOver);
                Draw.color(Pal.gray);
                Lines.stroke(2.5f);
                Fill.square(ix, iy, 2.5f, 45);
                Fill.square(ox, oy, 2.5f, 45);
                Lines.stroke(4f);

                Lines.line(ix, iy, ox, oy);
                //Colored
                Draw.z(Layer.blockOver + 0.0001f);
                Draw.color(unloadItem == null ? Pal.gray : unloadItem.color);
                Fill.square(ix, iy, 1f, 45);
                Fill.square(ox, oy, 1f, 45);
                Lines.stroke(1f);

                Lines.line(ix, iy, ox, oy);

                //Point
                Draw.z(Layer.blockOver + 0.0002f);
                Draw.mixcol(Draw.getColor(), 1f);
                Draw.color();
                Fill.square(px, py, 1f, 45);
                Draw.mixcol();
            }
        }

        @Override
        public void buildConfiguration(Table table) {
            ItemSelection.buildTable(ItemDiode.this, table, content.items(), () -> unloadItem, this::configure);
        }

        @Override
        public Item config() {
            return unloadItem;
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            write.s(unloadItem == null ? -1 : unloadItem.id);
            write.s(offset);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            int id = read.s();
            unloadItem = id == -1 ? null : content.items().get(id);
            offset = read.s();
        }
    }
}
