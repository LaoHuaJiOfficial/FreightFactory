package prototypes.block.distribution;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.entities.TargetPriority;
import mindustry.gen.Building;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.world.Block;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Env;

import static mindustry.Vars.content;

public class TransportValve extends Block {
    public float speed = 1f;
    public boolean allowCoreUnload = false;
    public TransportValve(String name) {
        super(name);

        update = true;
        solid = true;
        rotate = true;
        canOverdrive = false;

        hasPower = true;
        outputsPower = true;
        consumesPower = false;

        unloadable = false;
        noUpdateDisabled = true;

        group = BlockGroup.transportation;
        priority = TargetPriority.transport;
        envDisabled = Env.none;
    }

    public class TransportValveBuild extends Building{
        public float unloadTimer = 0f;
        public int offsetItem = 0;
        public int offsetLiquid = 0;

        @Override
        public void updateTile() {
            if ((unloadTimer += edelta()) >= speed) {
                Building front = front(), back = back();

                if(front != null && back != null && back.items != null && front.team == team && back.team == team && back.canUnload() &&
                    (allowCoreUnload || !(back instanceof CoreBlock.CoreBuild || (back instanceof StorageBlock.StorageBuild sb && sb.linkedCore != null)))) {

                    {
                        var itemseq = content.items();
                        int itemc = itemseq.size;
                        for (int i = 0; i < itemc; i++) {
                            Item item = itemseq.get((i + offsetItem) % itemc);
                            if (back.items.has(item) && front.acceptItem(this, item)) {
                                front.handleItem(this, item);
                                back.items.remove(item, 1);
                                back.itemTaken(item);
                                offsetItem++;
                                offsetItem %= itemc;
                                break;
                            }
                            /*
                            if (front.dump(item) && back.canDump(null, item) && front.items.get(item) < front.block.itemCapacity && back.items.has(item)){
                                front.handleItem(this, item);
                                back.items.remove(item, 1);
                                back.itemTaken(item);
                                offsetItem++;
                                offsetItem %= itemc;
                                break;
                            }

                             */
                        }
                    }

                    {
                        var liquidseq = content.liquids();
                        int liquidc = liquidseq.size;
                        for (int i = 0; i < liquidc; i++) {
                            Liquid liquid = liquidseq.get((i + offsetLiquid) % liquidc);
                            if (front.acceptLiquid(this, liquid)) {
                                float fl = front.liquids.get(liquid), bl = back.liquids.get(liquid);
                                float fc = front.block.liquidCapacity, bc = back.block.liquidCapacity;
                                if (bl > 0 && bl / bc > fl / fc) {
                                    float amount = Math.min(10 * speed * Time.delta, back.liquids.get(liquid));
                                    float a = Math.min(amount, front.block.liquidCapacity - front.liquids.get(liquid));
                                    float balance = Math.min(a, (bl / bc - fl / fc) * bc);
                                    front.handleLiquid(this, liquid, balance);
                                    back.liquids.remove(liquid, balance);
                                }
                            }
                        }
                    }

                    unloadTimer %= speed;
                }
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

            if (front != null && back != null) {
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
                Draw.color(Pal.accent);
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
        public void write(Writes write) {
            super.write(write);
            write.s(offsetItem);
            write.s(offsetLiquid);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            offsetItem = read.s();
            offsetLiquid = read.s();
        }
    }
}
