package util;

import arc.func.Cons;
import mindustry.world.Block;
import mindustry.world.Tile;

import static mindustry.Vars.world;

public class TileExpand extends Tile {
    public TileExpand(int x, int y) {
        super(x, y);
    }

    public void ScanTile(Block block, Cons<Tile> tmpArray){
        int size = block.size * 2, o = block.sizeOffset;
        for(int dx = 0; dx < size; dx++){
            for(int dy = 0; dy < size; dy++){
                Tile other = world.tile(x + dx + o, y + dy + o);
                if(other != null) tmpArray.get(other);
            }
        }
    }
}
