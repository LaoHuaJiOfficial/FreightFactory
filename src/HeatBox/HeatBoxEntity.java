package HeatBox;

import arc.struct.IntMap;
import arc.struct.IntSet;
import arc.struct.Queue;
import arc.struct.Seq;
import mindustry.gen.Building;

public class HeatBoxEntity {

    public static IntMap<HeatBoxEntity> HeatBoxAll = new IntMap<>();

    protected transient boolean added;
    private static final Queue<HeatBoxBlock.HeatBoxBlockBuild> queue = new Queue<>();
    private static final Seq<Building> outArray1 = new Seq<>();
    private static final Seq<Building> outArray2 = new Seq<>();
    private static final IntSet closedSet = new IntSet();

    //do not modify any of these unless you know what you're doing!
    public final Seq<Building> producers = new Seq<>(false, 16, Building.class);
    public final Seq<Building> consumers = new Seq<>(false, 16, Building.class);
    public final Seq<Building> HeatBoxBuildings = new Seq<>(false, 16, Building.class);
    public final Seq<Building> HeatBoxBuildingAll = new Seq<>(false, 16, Building.class);

    public float HeatBoxArea = 0f;
    public float HeatBoxHeight = 0f;
    public final int HeatBoxID;

    public static int HeatBoxLastID = 0;

    public HeatBoxEntity(float area, float height){
        this.HeatBoxArea = area;
        this.HeatBoxHeight = height;
        HeatBoxID = HeatBoxLastID++;
        HeatBoxAll.put(HeatBoxID, this);
    }

    public int getID(){
        return HeatBoxID;
    }
    //add heat to current heat box
    public void HeatBoxExpandArea(float area){
        this.HeatBoxArea += area;
    }
    public float GetHeatBoxHeight(){
        return this.HeatBoxHeight;
    }
    public int GetHeatBoxID(){
        return HeatBoxID;
    }
    public void HeatAdd(){

    }
    //reduce heat to current heat box
    public void HeatRemove(){

    }


    public void update(){

    }
    /** This Method would merge another heat box.
     *  In Test.
     *  @param heatBox the heat box that would be merged.
     */
    public void UpdateAddHeatBox(HeatBoxEntity heatBox){
        if(heatBox == this) return;

        //merge into other graph instead.
        if(this.HeatBoxBuildingAll.size > heatBox.HeatBoxBuildingAll.size){
            for(Building tile : heatBox.HeatBoxBuildingAll){
                this.HeatBoxAddBuild((HeatBoxBlock.HeatBoxBlockBuild) tile);
            }
            heatBox.RemoveHeatBox();

        }else {
            for(Building tile : this.HeatBoxBuildingAll){
                heatBox.HeatBoxAddBuild((HeatBoxBlock.HeatBoxBlockBuild) tile);
            }
            this.RemoveHeatBox();
        }

        //other entity should be removed as the graph was merged
        //checkAdd();
    }


    /** add a build to heat box.
     *  In Test.
     *  @param build the build that would be merged.
     */
    public void HeatBoxAddBuild(HeatBoxBlock.HeatBoxBlockBuild build){

        //todo change this shitty code

        build.HeatBox = this;

        this.HeatBoxBuildingAll.add(build);
        this.HeatBoxBuildings.add(build);

        /*
        if(build.power.graph != this || !build.power.init){
            //any old graph that is added here MUST be invalid, remove it
            if(build.power.graph != null && build.power.graph != this){
                if(build.power.graph.entity != null) build.power.graph.entity.remove();
            }

            build.power.graph = this;
            build.power.init = true;
            all.add(build);

            if(build.block.outputsPower && build.block.consumesPower && !build.block.consPower.buffered){
                producers.add(build);
                consumers.add(build);
            }else if(build.block.outputsPower && build.block.consumesPower){
                batteries.add(build);
            }else if(build.block.outputsPower){
                producers.add(build);
            }else if(build.block.consumesPower && build.block.consPower != null){
                consumers.add(build);
            }
        }

         */
    }

    public void HeatBoxRemoveBuild(HeatBoxBlock.HeatBoxBlockBuild build){
        build.HeatBox = null;

        this.HeatBoxBuildingAll.remove(build);
        this.HeatBoxBuildings.remove(build);
    }

    public void checkAdd(){
        this.CreateHeatBox();
    }

    public void clear(){
        HeatBoxBuildingAll.clear();
        HeatBoxBuildings.clear();
        producers.clear();
        consumers.clear();
    }

    public void reflow(HeatBoxBlock.HeatBoxBlockBuild tile){
        queue.clear();
        queue.addLast(tile);
        closedSet.clear();
        while(queue.size > 0){
            HeatBoxBlock.HeatBoxBlockBuild child = queue.removeFirst();
            HeatBoxAddBuild(child);
            checkAdd();
            for(Building next : child.getPowerConnections(outArray2)){
                if(closedSet.add(next.pos())){
                    queue.addLast((HeatBoxBlock.HeatBoxBlockBuild) next);
                }
            }
        }
    }

    /** Note that this does not actually remove the Building from the graph;
     * it creates *new* graphs that contain the correct buildings. Doing this invalidates the graph. */
    public void remove(HeatBoxBlock.HeatBoxBlockBuild tile){

        //go through all the connections of this tile
        for(HeatBoxBlock.HeatBoxBlockBuild other : tile.AdjoinHeatBoxBlock()){
            //a graph has already been assigned to this tile from a previous call, skip it
            if(other.HeatBox != this) continue;

            //create graph for this branch
            HeatBoxEntity entity = new HeatBoxEntity(0,0);
            entity.checkAdd();
            entity.HeatBoxAddBuild(other);
            //add to queue for BFS
            queue.clear();
            queue.addLast(other);
            while(queue.size > 0){
                //get child from queue
                HeatBoxBlock.HeatBoxBlockBuild child = queue.removeFirst();
                //add it to the new branch graph
                entity.HeatBoxAddBuild(child);
                //go through connections
                for(HeatBoxBlock.HeatBoxBlockBuild next : child.AdjoinHeatBoxBlock()){
                    //make sure it hasn't looped back, and that the new graph being assigned hasn't already been assigned
                    //also skip closed tiles
                    if(next != tile && next.HeatBox != entity){
                        entity.HeatBoxAddBuild(next);
                        queue.addLast(next);
                    }
                }
            }
            //update the graph once so direct consumers without any connected producer lose their power
            entity.update();
        }

        //implied empty graph here
        this.RemoveHeatBox();

    }

    public void CreateHeatBox() {
        if (!this.added) {
            HeatBoxAll.put(this.HeatBoxID, this);
            this.added = true;
        }
    }
    public void RemoveHeatBox(){
        if (this.added){
            this.clear();
            HeatBoxAll.remove(this.HeatBoxID);
            this.added = false;
        }
    }
}
