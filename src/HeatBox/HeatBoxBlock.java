package HeatBox;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Mathf;
import arc.math.geom.Point2;
import arc.struct.IntMap;
import arc.struct.Seq;
import mindustry.Vars;
import mindustry.core.World;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.Block;
import world.BuildingF;
import world.block.tech.Nexus;

public class HeatBoxBlock extends Block {

    protected static final Point2[] AdjoinTile = {
        new Point2(1, 0),
        new Point2(0, 1),
        new Point2(-1, 0),
        new Point2(0, -1),
    };
    public HeatBoxBlock(String name){
        super(name);

        solid = true;
        update = true;
        destructible = true;
        size = 1;
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("Index", (HeatBoxBlockBuild e) -> new Bar(() -> Core.bundle.format("bar.index", e.GetHeatBoxID()), () -> Pal.accent, () -> 1f));

        addBar("IndexTotal", (HeatBoxBlockBuild e) -> new Bar(() -> Core.bundle.format("bar.index-total", HeatBoxEntity.HeatBoxLastID), () -> Pal.accent, () -> 1f));

    }

    public class HeatBoxBlockBuild extends BuildingF {

        public HeatBoxEntity HeatBox;

        @Override
        public void onProximityAdded() {
            super.onProximityAdded();
        }

        @Override
        public void onProximityUpdate() {
            super.onProximityUpdate();
        }

        @Override
        public void onProximityRemoved() {
            super.onProximityRemoved();
        }

        @Override
        public void update() {

        }

        @Override
        public void drawSelect(){
            for(Building build: HeatBox.HeatBoxBuildingAll){
                Draw.color(Pal.accent);
                Fill.square(build.x, build.y, 4, 45);
            }
        }

        @Override
        public void created(){

            HeatBox = new HeatBoxEntity(0, 0);
            HeatBox.HeatBoxAddBuild(this);
            HeatBox.CreateHeatBox();
            for (HeatBoxEntity other: PotentialHeatBoxLinks()){
                HeatBox.UpdateAddHeatBox(other);
            }

            /*
            boolean HasAdjoin = false;
            HeatBoxBlockBuild build = null;
            Seq<HeatBoxEntity> AdjoinHeatBox = new Seq<>();

            //Check Adjoin Tiles
            for (Point2 point2 : AdjoinTile) {
                if (CheckAdjoinTile(point2)) {
                    HasAdjoin = true;
                    break;
                }
            }



            if (!HasAdjoin){
                //register a new heat box if there's no adjoin tiles
                HeatBox = new HeatBoxEntity(0, 0);
                HeatBox.CreateHeatBox();
            }else {
                //adjoin heat box block's heat box add this tile
                for (Point2 pos: AdjoinTile){
                    if(GetAdjoinTile(pos) != null){
                        HeatBox.UpdateHeatBox(GetAdjoinTile(pos).HeatBox);
                    }
                }
            }
            */
        }

        public Seq<HeatBoxEntity> PotentialHeatBoxLinks(){
            Seq<HeatBoxEntity> PotentialLinks = new Seq<>();
            for (Point2 pos: AdjoinTile){
                if(GetAdjoinTile(pos) != null){
                    PotentialLinks.add(GetAdjoinTile(pos).HeatBox);
                }
            }
            return PotentialLinks;
        }

        public Seq<HeatBoxBlockBuild> AdjoinHeatBoxBlock(){
            Seq<HeatBoxBlockBuild> AdjoinLinks = new Seq<>();
            for (Point2 pos: AdjoinTile){
                if(GetAdjoinTile(pos) != null){
                    AdjoinLinks.add(GetAdjoinTile(pos));
                }
            }
            return AdjoinLinks;
        }
        public int GetHeatBoxID(){
            return HeatBoxEntity.HeatBoxAll.findKey(HeatBox, true, -1) + 1;
        }
        public HeatBoxEntity GetHeatBox(){
            return HeatBox;
        }

        @Override
        public void onRemoved(){
            //HeatBox.HeatBoxRemoveBuild(this);

            HeatBox.remove(this);
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

