package prototypes.block.distribution;

import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.gen.Building;
import utilities.game.ItemQueueBuffer;

public class BeltBridge extends Bridge{
    public float itemPerSecond = 10f;
    public int bufferCapacity = 50;

    public BeltBridge(String name){
        super(name);
        hasPower = false;
        hasItems = true;
        canOverdrive = true;
    }

    public class BeltBridgeBuild extends BridgeBuild {
        public float speed = Time.toSeconds / itemPerSecond;
        public float progress;
        public float timeInterval = 0.9f * speed;
        ItemQueueBuffer ItemQueueBuffer = new ItemQueueBuffer(bufferCapacity);

        @Override
        public void updateTransport(Building other){
            progress += edelta();
            int dist = Math.abs(other.tile.x - tile.x) + Math.abs(other.tile.y - tile.y);

            if(ItemQueueBuffer.accepts() && items.total() > 0){
                ItemQueueBuffer.accept(items.take());
            }

            if(ItemQueueBuffer.lastItem() != null && other.acceptItem(this, ItemQueueBuffer.lastItem())){
                if (progress >= timeInterval) {
                    moved = true;
                    other.handleItem(this, ItemQueueBuffer.lastItem());
                    ItemQueueBuffer.remove();
                    progress = 0;
                }
            } else {
                progress = (-140 / itemPerSecond) * dist;
            }
        }

        @Override
        public void doDump(){
            dump();
        }

        @Override
        public void write(Writes write){
            super.write(write);
            ItemQueueBuffer.write(write);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            ItemQueueBuffer.read(read);
        }
    }
}
