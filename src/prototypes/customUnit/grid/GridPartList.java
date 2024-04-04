package prototypes.customUnit.grid;

import arc.struct.Seq;
import prototypes.customUnit.weapon.WeaponData;
import prototypes.customUnit.weapon.WeaponList;

public class GridPartList {
    public static Seq<GridPartData> GridWeaponList;

    public static void init(){
        GridWeaponList = new Seq<>();
        for (WeaponData weapon: WeaponList.weapons){
            GridWeaponList.add(new GridPartData(weapon));
        }
    }
}
