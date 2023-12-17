package contents;

import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.entities.bullet.ArtilleryBulletType;
import mindustry.gen.EntityMapping;
import mindustry.gen.LegsUnit;
import mindustry.gen.Legsc;
import mindustry.gen.Sounds;
import mindustry.graphics.Layer;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.ammo.ItemAmmoType;

public class FFUnitTypes {
    public static UnitType TestUnit;

    public static void load(){

        TestUnit = new UnitType("test-unit") {
            {
                constructor = LegsUnit::create;
                speed = 1.5f;
                hitSize = 5f;
                health = 2024520;
                armor = 4f;
                ammoType = new ItemAmmoType(Items.coal);
                groundLayer = Layer.legUnit;

                legSplashDamage = 2024;
                legSplashRange = 30;
                legSpeed = 0.2f;
                legCount = 12;
                legMoveSpace = 1f;
                legPairOffset = 3;
                legLength = 50;
                legExtension = -15;
                legBaseOffset = 10f;
                weapons.add(new Weapon(){{
                    y = -7f;
                    x = 9f;
                    shootY = 7f;
                    reload = 45;
                    shake = 3f;
                    rotateSpeed = 2f;
                    ejectEffect = Fx.casing1;
                    shootSound = Sounds.artillery;
                    rotate = true;
                    shadow = 8f;
                    recoil = 3f;

                    bullet = new ArtilleryBulletType(2f, 12) {};
                }});
            }
        };
    }
}
