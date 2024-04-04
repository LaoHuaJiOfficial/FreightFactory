package prototypes.customUnit.grid;

import arc.graphics.g2d.TextureRegion;
import mindustry.Vars;
import mindustry.gen.Unit;
import mindustry.type.Weapon;
import prototypes.customUnit.weapon.WeaponData;

import static prototypes.customUnit.CustomUnitVar.GridLen;

public class GridPartData {
    public int width;
    public int height;
    public int startX;
    public int startY;
    public WeaponData weapon;
    public TextureRegion icon;

    public GridPartData(WeaponData weapon){
        WeaponDataGrid(weapon);
    }

    public GridPartData setStart(int x, int y){
        this.startX = x;
        this.startY = y;
        return this;
    }

    public void WeaponDataGrid(WeaponData weapon){
        this.weapon = weapon;
        this.width = calcLength(weapon.weapon.region.width);
        this.height = calcLength(weapon.weapon.region.height);
        this.icon = weapon.icon;
    }

    public int calcLength(int len){
        return (len / GridLen) > 0? (len / GridLen): 1;
    }

    public void apply(Unit unit){
        var region = unit.type.region;
        weapon.apply(unit,
            (region.width%GridLen/2 + (startX-1)*GridLen + width*GridLen/2 - region.width/2)/ 4,
            (region.height%GridLen/2 + (startY-1)*GridLen + height*GridLen/2 - region.height/2)/ 4
        );
    }
}
