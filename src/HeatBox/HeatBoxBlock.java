package HeatBox;

import arc.math.geom.Point2;
import mindustry.Vars;
import mindustry.core.World;
import mindustry.gen.Building;
import mindustry.world.Block;

public class HeatBoxBlock extends Block {

    protected static final Point2[] AdjoinTile = {
        new Point2(1, 0),
        new Point2(0, 1),
        new Point2(-1, 0),
        new Point2(0, -1),
    };

    public HeatBoxEntity HeatBox;

    public HeatBoxBlock(String name){
        super(name);

        solid = true;
        update = false;
        destructible = true;
        size = 1;
    }

    public class HeatBoxBlockBuild extends Building{

        @Override
        public void update() {

        }

        @Override
        public void drawSelect(){
            return;
        }

        @Override
        public void created(){
            boolean HasAdjoin = false;
            HeatBoxBlockBuild build = null;

            //Check Adjoin Tiles
            for (Point2 pos: AdjoinTile){
                if(CheckAdjoinTile(pos)){
                    HasAdjoin = true;
                    build = GetAdjoinTile(pos);
                    break;
                }
            }

            if (!HasAdjoin){
                //register a new heat box if there's no adjoin tiles
                HeatBox = HeatBoxEntity.RegisterHeatBox(this);
            }else {
                //adjoin heat box block's heat box add this tile
                if (build != null){
                    HeatBox = build.GetHeatBox();
                }
            }
        }

        public HeatBoxEntity GetHeatBox(){
            return HeatBox;
        }

        @Override
        public void onRemoved(){

        }

        public boolean CheckAdjoinTile(Point2 pos){
            Building build = Vars.world.build(tileX() + pos.x, tileY() + pos.y);
            return build instanceof HeatBoxBlockBuild;
        }

        public HeatBoxBlockBuild GetAdjoinTile(Point2 pos){
            Building build = Vars.world.build(tileX() + pos.x, tileY() + pos.y);
            return (build instanceof HeatBoxBlockBuild? (HeatBoxBlockBuild) build : null);
        }
    }
}

