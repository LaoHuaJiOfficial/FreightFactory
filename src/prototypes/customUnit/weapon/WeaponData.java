package prototypes.customUnit.weapon;

import arc.Core;
import arc.graphics.g2d.TextureRegion;
import arc.util.Nullable;
import contents.FFSprites;
import mindustry.entities.units.WeaponMount;
import mindustry.gen.Unit;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import utilities.functions.GameUtil;

public class WeaponData{
    /** Weapon id, for key in WeaponList.weapons. */
    public int id;
    /** Unit Weapon Index. For weapon name generate. */
    public int weaponIndex;
    /** Weapon name, auto generated, should have a locale. */
    public String name;
    /** The weapon itself. weapon's X and Y are set to 0. */
    public Weapon weapon;
    /** The unit which first have this weapon. Used for name. Can be null */
    public @Nullable UnitType unit;
    /** Weapon Icon. Special sprite when no icon. (shouldn't be oh no) */
    public TextureRegion icon;

    /** TODO content */
    public WeaponCategory category;
    public boolean unlocked;

    public WeaponData(Weapon weapon, UnitType unit, int index, int id){
        this.id = id;
        this.weaponIndex = index;
        this.weapon = weaponCopy(weapon);
        this.unit = unit;
        this.icon = weaponIcon(weapon);
        this.name = weaponName();
    }

    private String weaponName(){
        if(weapon.name.isEmpty()){
            return unit.name + "-" + weaponIndex;
        }else return weapon.name;
    }

    private Weapon weaponCopy(Weapon weapon){
        Weapon cpy = weapon.copy();
        cpy.mirror = false;
        cpy.alternate = false;
        cpy.x = 0;
        cpy.y = 0;
        return cpy;
    }

    private TextureRegion weaponIcon(Weapon weapon){
        if (Core.atlas.isFound(weapon.outlineRegion)){
            return weapon.outlineRegion;
        }else if (Core.atlas.isFound(weapon.region)){
            return weapon.region;
        }else {
            return FFSprites.WeaponIcon;

        }
    }

    public void apply(Unit unit, int x, int y){
        var wep = this.weapon.copy();
        wep.x = x;
        wep.y = y;
        GameUtil.AddUnitWeapons(unit, new WeaponMount(wep));
    }

    public enum WeaponCategory{

    }
}
