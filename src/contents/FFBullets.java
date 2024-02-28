package contents;

import arc.graphics.Color;
import contents.bullets.HelixLaserBulletType;
import contents.bullets.HelixTrailBulletType;
import contents.bullets.PointLaserLightningBulletType;
import mindustry.content.Fx;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.bullet.PointLaserBulletType;
import mindustry.graphics.Pal;

public class FFBullets {
    public static BulletType
        IronCurtain_0, IronCurtain_1, Crysta_0, Cathode_0;

    public static void load(){
        IronCurtain_0 = new BasicBulletType(){{
            //Draw Part
            backSprite = "missile-large-back";
            sprite = "mine-bullet";

            height = 12f;
            width = 10f;

            frontColor = Color.white;
            backColor = trailColor = hitColor = Color.sky;

            trailChance = 0.44f;
            trailLength = 12;
            trailWidth = 2f;
            trailEffect = Fx.disperseTrail;
            trailRotation = true;

            shootEffect = Fx.shootBig2;
            smokeEffect = Fx.shootSmokeDisperse;
            hitEffect = despawnEffect = Fx.hitBulletColor;

            despawnShake = 7f;

            speed = 5.2f;
            shrinkY = 0.3f;

            //Damage Part
            damage = 40;
            splashDamageRadius = 25f;
            splashDamage = 20f;
            scaledSplashDamage = true;

            //Homing
            homingDelay = 0f;
            homingRange = 40f;
            homingPower = 0.05f;

            //Meta Part
            ammoMultiplier = 3f;
            lifetime = 80f;
        }};

        IronCurtain_1 = new BasicBulletType(){{
            //Draw Part
            backSprite = "missile-large-back";
            sprite = "mine-bullet";

            height = 15f;
            width = 12f;

            frontColor = Pal.reactorPurple;
            backColor = trailColor = hitColor = Pal.reactorPurple2;

            trailChance = 0.44f;
            trailLength = 12;
            trailWidth = 2f;
            trailEffect = Fx.disperseTrail;
            trailRotation = true;

            shootEffect = Fx.shootBig2;
            smokeEffect = Fx.shootSmokeDisperse;
            hitEffect = despawnEffect = Fx.blastExplosion;

            despawnShake = 7f;

            speed = 7f;
            shrinkY = 0.3f;
            //Damage Part
            damage = 100;
            splashDamageRadius = 35f;
            splashDamage = 50f;
            scaledSplashDamage = true;

            //Homing
            homingDelay = 20f;
            homingRange = 100f;
            homingPower = 0.05f;

            //Meta Part
            ammoMultiplier = 4f;
            reloadMultiplier = 1.35f;
            lifetime = 60f;

        }};

        Crysta_0 = new HelixTrailBulletType(){{
            //Draw Part
            backSprite = "missile-large-back";
            sprite = "mine-bullet";

            height = 18f;
            width = 14f;

            frontColor = Color.white;
            backColor = trailColor = hitColor = Color.sky;

            trailInterval = 3f;
            trailLength = 20;
            trailWidth = 2f;
            trailRotation = true;

            sideTrailLength = 15;
            sideTrailWidth = 1.5f;
            sideTrailSpace = 10f;
            sideTrailTime = 10f;

            shootEffect = Fx.shootBig2;
            smokeEffect = Fx.shootSmokeDisperse;
            hitEffect = despawnEffect = Fx.hitBulletColor;

            despawnShake = 7f;

            speed = 10f;
            shrinkY = 0.3f;

            //Damage Part
            damage = 40;
            splashDamageRadius = 25f;
            splashDamage = 20f;
            scaledSplashDamage = true;

            //Meta Part
            ammoMultiplier = 3f;
            lifetime = 200f;
        }};

        Cathode_0 = new PointLaserLightningBulletType();
    }
}
