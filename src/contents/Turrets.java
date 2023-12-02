package contents;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import contents.bullets.CrystaBullet;
import contents.bullets.InfernoBullet;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.UnitSorts;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.PointBulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.gen.Sounds;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.meta.Env;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;
import static mindustry.type.ItemStack.with;

public class Turrets {
    public static Block Crysta, Inferno;

    public static void load(){
        Crysta = new ItemTurret("crysta"){{
            float brange = range = 500f;

            requirements(Category.turret, with(Items.copper, 1));
            ammo(
                Items.copper, new CrystaBullet(){{
                    trailSpacing = 10f;
                    damage = 1350;
                    buildingDamageMultiplier = 0.2f;
                    speed = brange;
                    hitShake = 6f;
                    ammoMultiplier = 1f;
                }}
            );

            maxAmmo = 40;
            ammoPerShot = 5;
            rotateSpeed = 2f;
            reload = 7.5f;
            ammoUseEffect = Fx.casing3Double;
            recoil = 5f;
            cooldownTime = reload;
            shake = 4f;
            size = 4;
            shootCone = 2f;
            shootSound = FFSounds.CrystaShoot;
            unitSort = UnitSorts.strongest;
            envEnabled |= Env.space;

            coolantMultiplier = 0.4f;
            scaledHealth = 150;

            coolant = consumeCoolant(1f);
            //consumePower(10f);
        }};

        Inferno = new ItemTurret("inferno"){{
            requirements(Category.turret, with(Items.copper, 900, Items.graphite, 300, Items.surgeAlloy, 250, Items.plastanium, 175, Items.thorium, 250));
            ammo(
                Items.graphite, new InfernoBullet(7.5f, 50){{
                    hitSize = 4.8f;
                    width = 15f;
                    height = 21f;
                    shootEffect = Fx.shootBig;
                    ammoMultiplier = 4;
                    reloadMultiplier = 1.7f;
                    knockback = 0.3f;
                    trailWidth = 2f;
                    trailLength = 15;
                }}
            );
            reload = 60f;
            recoilTime = reload * 2f;
            coolantMultiplier = 0.5f;
            ammoUseEffect = Fx.casing3;
            range = 400f;
            inaccuracy = 3f;
            recoil = 3f;
            shoot = new ShootAlternate(8f);
            shake = 2f;
            size = 5;
            shootCone = 24f;
            shootSound = FFSounds.InfernoShoot;

            scaledHealth = 160;
            coolant = consumeCoolant(1f);

            limitRange();
        }};
    }
}
