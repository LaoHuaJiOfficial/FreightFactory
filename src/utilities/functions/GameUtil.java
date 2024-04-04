package utilities.functions;

import arc.Core;
import arc.graphics.Pixmap;
import arc.graphics.Texture;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.math.geom.Vec2;
import arc.struct.Seq;
import arc.util.Log;
import mindustry.content.UnitTypes;
import mindustry.entities.abilities.Ability;
import mindustry.entities.units.WeaponMount;
import mindustry.gen.Unit;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.world.blocks.environment.StaticWall;

public class GameUtil {
    //static TextureRegion region;
    //static Pixmap pixmap;
    public static void ReplaceUnitAbilities(Unit unit, Seq<Ability> abilities){}
    public static void ReplaceUnitAbilities(Unit unit, Ability[]abilities){}
    public static void ReplaceUnitAbilities(Unit unit, Ability ability){}
    public static void AddUnitAbilities(Unit unit, Seq<Ability> abilities){}
    public static void AddUnitAbilities(Unit unit, Ability[] abilities){}
    public static void AddUnitAbilities(Unit unit, Ability ability){}

    public static void ReplaceUnitWeapons(Unit unit, Seq<WeaponMount> weapons){}
    public static void ReplaceUnitWeapons(Unit unit, WeaponMount[] weapons){}
    public static void ReplaceUnitWeapons(Unit unit, WeaponMount weapon){}
    public static void AddUnitWeapons(Unit unit, Seq<WeaponMount> weapons){}
    public static void AddUnitWeapons(Unit unit, WeaponMount[] weapons){}
    public static void AddUnitWeapons(Unit unit, WeaponMount weapon){
        WeaponMount[] weaponTemp = new WeaponMount[unit.mounts.length + 1];
        System.arraycopy(unit.mounts, 0, weaponTemp, 0, unit.mounts.length);
        weaponTemp[unit.mounts.length] = weapon;
        unit.mounts = weaponTemp;
    }

    public static Seq<Point2> getUnitRect(UnitType unit, int scale) {
        Seq<Point2> out = new Seq<>();
        var content = unit.fullIcon;
        Pixmap pixmap = Core.atlas.getPixmap(content).crop();
        int xStep = Math.max(1, content.width / scale);
        int yStep = Math.max(1, content.height / scale);
        int xShift = content.width%scale/2;
        int yShift = content.height%scale/2;

        Point2[] xGridShift = new Point2[]{
            new Point2(scale/4, scale/4),
            new Point2(scale/4, -(scale/4 + 1)),
            new Point2(-(scale/4 + 1), scale/4),
            new Point2(-(scale/4 + 1), -(scale/4 + 1))
        };

        for (int x = 1; x <= xStep; x++) {
            for (int y = 1; y <= yStep; y++) {
                for (Point2 point : Geometry.d8edge) {
                    boolean hasPixel = false;
                    for (Point2 point2 : xGridShift) {
                        int pixel = pixmap.get(
                            xShift + (x * scale - scale / 2) * point.x + point2.x,
                            yShift + (y * scale - scale / 2) * point.y + point2.y
                        );

                        if ((pixel & 0x000000ff) != 0) {
                            hasPixel = true;
                            break;
                        }
                    }
                    if (hasPixel) {
                        out.add(new Point2(
                            x * point.x,
                            (yStep + 1 - y) * point.y
                        ));
                    }
                }

            }
        }
        //Log.info(xStep);
        //Log.info(yStep);
        Log.info(out.size);
        return out;
    }
}
