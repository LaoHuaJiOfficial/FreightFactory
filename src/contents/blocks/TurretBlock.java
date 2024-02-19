package contents.blocks;

import contents.FFBullets;
import contents.FFSounds;
import mindustry.content.Items;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.gen.Sounds;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.draw.DrawTurret;

import static mindustry.type.ItemStack.with;

public class TurretBlock {
    public static Block
        IronCurtain, Crysta;

    public static void load(){

        IronCurtain = new ItemTurret("iron-curtain"){{
            requirements(Category.turret, with(Items.copper, 10));

            ammo(
                Items.graphite, FFBullets.IronCurtain_0,
                Items.thorium, FFBullets.IronCurtain_1
            );
            shoot = new ShootAlternate(){{
                spread = 4.8f;
                shotDelay = 4;
                shots = 4;
                barrels = 4;
            }};

            reload = 40f;
            shootY = 12f;
            rotateSpeed = 5f;
            shootCone = 15f;
            consumeAmmoOnce = true;
            shootSound = Sounds.largeCannon;

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

            shootWarmupSpeed = 0.08f;

            scaledHealth = 300;
            range = 400f;
            size = 3;

            limitRange(-5f);
        }};

        Crysta = new ItemTurret("crysta"){{
            requirements(Category.turret, with(Items.copper, 10));

            ammo(
                Items.graphite, FFBullets.Crysta_0
            );

            reload = 20f;
            shootY = 10f;
            rotateSpeed = 5f;
            shootCone = 15f;
            consumeAmmoOnce = true;
            shootSound = FFSounds.InfernoShoot;

            drawer = new DrawTurret(){{
                parts.add(
                    new RegionPart("-blade"){{
                        mirror = true;
                        moveX = -1f;
                        moveY = -2f;
                        moveRot = 4f;
                        progress = PartProgress.warmup;

                        heatColor = Pal.techBlue;
                        heatLightOpacity = 0.2f;
                    }},
                    new RegionPart("-barrel"){{
                        moveY = -3f;
                        progress = PartProgress.recoil;

                        heatColor = Pal.techBlue;
                        heatLightOpacity = 0.2f;
                    }},
                    new RegionPart("-bottom"){{
                        mirror = true;
                        under = true;
                        moveY = -0.8f;
                        moveX = 0.8f;
                        progress = PartProgress.recoil;

                        heatColor = Pal.techBlue;
                        heatLightOpacity = 0.2f;
                    }},
                    new RegionPart("-upper"){{
                        mirror = true;
                        under = true;
                    }}
                    );
            }};

            shootWarmupSpeed = 0.08f;

            scaledHealth = 300;
            range = 1200f;
            size = 4;

            limitRange(-5f);
        }};
    }
}
