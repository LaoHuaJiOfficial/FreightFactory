package prototypes.struct;

import arc.struct.Seq;
import mindustry.gen.Building;
import mindustry.type.Item;
import mindustry.world.modules.ItemModule;
import mindustry.world.modules.LiquidModule;

public class LinkedBuildingGroup {
    public Seq<Building> LinkedBuilding;
    public ItemModule LinkedItem;
    public LiquidModule LinkedLiquid;
    //might not necessary?
    //PowerModule LinkedPower;

    public void init(){

    }

    public void add(){

    }

    public void remove(){

    }

    public boolean acceptItem(Item item){
        return false;
    }

    public void handleItem(){

    }


}
