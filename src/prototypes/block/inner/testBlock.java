package prototypes.block.inner;

import arc.struct.Seq;
import contents.FFBlock;
import mindustry.Vars;
import mindustry.core.World;
import mindustry.world.Tile;
import prototypes.block.production.AssemblerBlock;

public class testBlock extends AssemblerBlock {
    public testBlock(String name) {
        super(name);
    }

    public class testBuild extends AssemblerBlockBuild{
        @Override
        public void created() {
            super.created();

            Seq<Tile> tar = new Seq<>();
            tar.add(Vars.world.tile(tile.x + 2, tile.y));
            tar.add(Vars.world.tile(tile.x + 2, tile.y + 1));
            tar.add(Vars.world.tile(tile.x - 1, tile.y));
            tar.add(Vars.world.tile(tile.x - 1, tile.y + 1));

            for(var tile: tar){
                tile.setBlock(FFBlock.linkBuild, this.team);
                ((LinkBlock.LinkBuild)tile.build).updateLink(this);
            }
        }
    }
}
