package contents.blocks;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.draw.DrawTurret;

import static mindustry.type.ItemStack.with;

public class TurretBlock {
    public static Block IronCurtain;

    public static void load(){

        IronCurtain = new ItemTurret("iron-curtain"){{
            requirements(Category.turret, with(Items.copper, 10));

            ammo(Items.tungsten, new BasicBulletType(){{
                damage = 65;
                speed = 8.5f;
                width = height = 16;
                shrinkY = 0.3f;
                backSprite = "large-bomb-back";
                sprite = "mine-bullet";
                velocityRnd = 0.11f;
                collidesGround = false;
                collidesTiles = false;
                shootEffect = Fx.shootBig2;
                smokeEffect = Fx.shootSmokeDisperse;
                frontColor = Color.white;
                backColor = trailColor = hitColor = Color.sky;
                trailChance = 0.44f;
                ammoMultiplier = 3f;

                lifetime = 34f;
                rotationOffset = 90f;
                trailRotation = true;
                trailEffect = Fx.disperseTrail;

                hitEffect = despawnEffect = Fx.hitBulletColor;
            }});

            reload = 9f;
            shootY = 15f;
            rotateSpeed = 5f;
            shootCone = 15f;
            consumeAmmoOnce = true;
            shootSound = Sounds.shootBig;

            drawer = new DrawTurret(){{
                parts.add(
                    new RegionPart("-barrel"){{
                        moveY = -2f;
                        progress = PartProgress.recoil;
                    }},
                    new RegionPart("-bottom"){{
                        mirror = true;
                        under = true;
                        moveX = -0.5f;
                        moveY = -2f;
                        moveRot = 45f;
                    }},
                    new RegionPart("-bottom"){{
                        mirror = true;
                        under = true;
                        moveX = -2f;
                        moveY = 0.5f;
                    }},
                    new RegionPart("-front"){{
                        mirror = true;
                        under = true;
                        moveY = -1f;
                        moveX = -1f;
                    }});
            }};

            shoot = new ShootAlternate(){{
                spread = 4.7f;
                shots = 4;
                barrels = 4;
            }};

            shootWarmupSpeed = 0.08f;

            scaledHealth = 280;
            range = 310f;
            size = 3;

            limitRange(-5f);
        }};
    }
}
