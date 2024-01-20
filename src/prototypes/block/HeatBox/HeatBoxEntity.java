package prototypes.block.HeatBox;

import arc.struct.IntMap;
import arc.struct.Queue;
import arc.struct.Seq;
import mindustry.gen.Building;

public class HeatBoxEntity {

    private static final Queue<BlockF.BuildF> queue = new Queue<>();
    public static IntMap<HeatBoxEntity> HeatBoxAll = new IntMap<>();
    public static int HeatBoxLastID = 0;
    //do not modify any of these unless you know what you're doing!
    public final Seq<BlockF.BuildF> HeatBoxProducers = new Seq<>(false, 16, Building.class);
    public final Seq<BlockF.BuildF> HeatBoxConsumers = new Seq<>(false, 16, Building.class);
    public final Seq<BlockF.BuildF> HeatBoxDistributors = new Seq<>(false, 16, Building.class);
    public final Seq<BlockF.BuildF> HeatBoxBuildingAll = new Seq<>(false, 16, Building.class);
    public final int HeatBoxID;
    public float HeatBoxArea;
    public float CurrentTemp;
    public float HeatBoxHeat = 0f;
    protected transient boolean added;

    public HeatBoxEntity(float area, float temp) {
        HeatBoxArea = area;
        CurrentTemp = temp;

        HeatBoxID = HeatBoxLastID++;
        HeatBoxAll.put(HeatBoxID, this);
    }

    public HeatBoxEntity(float area) {
        HeatBoxArea = area;
        CurrentTemp = 300f;

        HeatBoxID = HeatBoxLastID++;
        HeatBoxAll.put(HeatBoxID, this);
    }

    public float GetHeatBoxHeat() {
        return this.HeatBoxHeat;
    }

    public float GetCurrentTemp() {
        return this.CurrentTemp;
    }

    public void CalcCurrentHeight() {
        this.CurrentTemp = HeatBoxHeat / HeatBoxArea;
    }


    /**
     * Add heat to current heatbox if current temperature is lower than the threshold
     *
     * @param heat      the heat amount
     * @param threshold the max temperature
     */
    public void HeatAdd(float heat, float threshold) {
        if (threshold > this.CurrentTemp) {
            this.HeatBoxHeat += heat * 100;
        }
        CalcCurrentHeight();
    }

    /**
     * Add heat to current heatbox if current temperature is higher than the threshold
     *
     * @param heat      the heat amount
     * @param threshold the min temperature
     */
    public void HeatRemove(float heat, float threshold) {
        if (threshold < this.CurrentTemp) {
            this.HeatBoxHeat -= heat * 100;
        }
        CalcCurrentHeight();
    }

    /**
     * This Method would merge another heat box.
     * In Test.
     *
     * @param heatBox the heat box that would be merged.
     */
    public void UpdateAddHeatBox(HeatBoxEntity heatBox) {
        if (heatBox == this) return;

        //merge into other graph instead.
        if (this.HeatBoxBuildingAll.size > heatBox.HeatBoxBuildingAll.size) {
            for (BlockF.BuildF tile : heatBox.HeatBoxBuildingAll) {
                this.AddBuild(tile, heatBox.CurrentTemp);
                this.CalcCurrentHeight();
                //this.HeatBoxHeat += heatBox.HeatBoxHeat;
            }
            heatBox.RemoveHeatBox();

        } else {
            for (BlockF.BuildF tile : this.HeatBoxBuildingAll) {
                heatBox.AddBuild(tile, this.CurrentTemp);
                heatBox.CalcCurrentHeight();
                //heatBox.HeatBoxHeat += this.HeatBoxHeat;
            }
            this.RemoveHeatBox();
        }

        //other entity should be removed as the graph was merged
        //checkAdd();
    }


    /**
     * add a build to heat box.
     * In Test.
     *
     * @param build the build that would be merged.
     */
    public void AddBuild(BlockF.BuildF build, float temp) {

        if (build.HasHeat()) {
            if (!HeatBoxBuildingAll.contains(build)) {

                HeatBoxArea += build.GetHeatBoxArea();
                HeatBoxHeat += build.GetHeatBoxArea() * temp;

                this.HeatBoxBuildingAll.add(build);

                if (build.GetHeatBoxArea() > 0) {
                    this.HeatBoxDistributors.add(build);
                }
                if (build.IsHeatConsumer()) {
                    this.HeatBoxConsumers.add(build);
                }
                if (build.IsHeatProducer()) {
                    this.HeatBoxProducers.add(build);
                }

                CalcCurrentHeight();

                build.HeatBox = this;

            }
        }
        //todo change this shitty code
    }

    public void Register() {
        this.CreateHeatBox();
    }

    public void clear() {
        HeatBoxBuildingAll.clear();
        HeatBoxDistributors.clear();
        HeatBoxProducers.clear();
        HeatBoxConsumers.clear();
    }

    /**
     * Note that this does not actually remove the Building from the graph;
     * it creates *new* graphs that contain the correct buildings. Doing this invalidates the graph.
     */
    public void remove(BlockF.BuildF tile) {

        float temp = CurrentTemp;
        //go through all the connections of this tile
        for (BlockF.BuildF other : tile.GetAdjoinBuildingF()) {
            //a graph has already been assigned to this tile from a previous call, skip it
            if (other.HeatBox != this) continue;

            //create graph for this branch
            HeatBoxEntity entity = new HeatBoxEntity(0, temp);

            entity.Register();
            entity.AddBuild(other, temp);
            //add to queue for BFS
            queue.clear();
            queue.addLast(other);
            while (queue.size > 0) {
                //get child from queue
                BlockF.BuildF child = queue.removeFirst();
                //add it to the new branch graph

                //entity.HeatBoxAddBuild(child);

                //go through connections
                for (BlockF.BuildF next : child.GetAdjoinBuildingF()) {
                    //make sure it hasn't looped back, and that the new graph being assigned hasn't already been assigned
                    //also skip closed tiles
                    if (next != tile && !entity.HeatBoxBuildingAll.contains(next)) {
                        entity.AddBuild(next, temp);
                        queue.addLast(next);
                    }
                }
            }

            entity.HeatBoxHeat = temp * entity.HeatBoxArea;
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

    public void RemoveHeatBox() {
        if (this.added) {
            this.clear();
            HeatBoxAll.remove(this.HeatBoxID);
            this.added = false;
        }
    }

}
