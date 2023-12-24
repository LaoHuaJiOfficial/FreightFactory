package HeatBox;

import arc.struct.IntMap;
import arc.struct.Seq;
import mindustry.gen.Building;

public class HeatBoxEntity {

    public static IntMap<HeatBoxEntity> HeatBoxAll = new IntMap<>();

    public Seq<Building> HeatBoxBuildings = new Seq<>();

    public float HeatBoxArea = 0f;
    public float HeatBoxHeight = 0f;
    public static int HeatBoxID = 0;

    public HeatBoxEntity(float area, float height){
        this.HeatBoxArea = area;
        this.HeatBoxHeight = height;
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

    public void CreateHeatBox(){

    }

    public static HeatBoxEntity RegisterHeatBox(Building build){
        if (build instanceof HeatBoxBlock.HeatBoxBlockBuild){
            HeatBoxEntity HeatBox = new HeatBoxEntity(0,0);
            HeatBox.HeatBoxBuildings.add(build);
            HeatBoxAll.put(HeatBoxID, HeatBox);
            HeatBoxID++;
            return HeatBox;
        }else return null;
    }
}
