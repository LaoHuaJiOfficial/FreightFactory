package contents.blocks;

import contents.FFBullets;
import contents.FFItems;
import contents.FFSounds;
import mindustry.content.Items;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.entities.pattern.ShootMulti;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.Sounds;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ContinuousTurret;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.draw.DrawTurret;

import static mindustry.type.ItemStack.with;

public class TurretBlock {
    public static Block
        IronCurtain, Interferer, Crysta, anode, Cathode, testTurret;

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
                parts.addAll(
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

            minWarmup = 0.8f;

            scaledHealth = 300;
            range = 400f;
            size = 3;

            limitRange(-5f);
        }};

        Interferer = new ItemTurret("interferer"){{
            requirements(Category.turret, with(Items.copper, 10));

            ammo(
                FFItems.aluminium, FFBullets.IronCurtain_0
            );
            shoot = new ShootAlternate(){{
                spread = 11f;
                shots = 2;
                barrels = 2;
            }};

            reload = 40f;
            shootY = 12f;
            rotateSpeed = 5f;
            shootCone = 15f;
            consumeAmmoOnce = true;
            shootSound = Sounds.mediumCannon;

            drawer = new DrawTurret(){{
                parts.addAll(
                    new RegionPart("-barrel"){{
                        under = true;
                        moveY = -3.5f;
                        progress = PartProgress.recoil;
                    }},
                    new RegionPart("-center"){{
                        moveY = -2.5f;
                        progress = PartProgress.warmup;
                    }},
                    new RegionPart("-under"){{
                        under = true;
                    }});
            }};

            shootWarmupSpeed = 0.08f;

            minWarmup = 0.8f;

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

            reload = 60f;
            shootY = 10f;
            rotateSpeed = 5f;
            shootCone = 15f;
            consumeAmmoOnce = true;
            shootSound = FFSounds.InfernoShoot;

            consumePower(1000/60f);

            drawer = new DrawTurret(){{
                parts.addAll(
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

            minWarmup = 0.8f;

            shootWarmupSpeed = 0.08f;

            scaledHealth = 300;
            range = 600f;
            size = 4;

            limitRange(-5f);
        }};

        Cathode = new ContinuousTurret("cathode"){{
            requirements(Category.turret, with(Items.copper, 10));

            shootType = FFBullets.Cathode_0;

            reload = 60f;
            shootY = 10f;
            rotateSpeed = 1f;
            aimChangeSpeed = 5f;
            shootCone = 8f;
            consumeAmmoOnce = true;
            shootSound = Sounds.none;
            loopSound = Sounds.pulse;

            consumePower(2000/60f);

            drawer = new DrawTurret(){{
                parts.addAll(
                    new RegionPart("-middle"),
                    new RegionPart("-front"){{
                        mirror = true;
                        moveX = 2.7f;
                        moveY = -2.7f;
                        moveRot = -10;
                        progress = PartProgress.warmup;
                    }},
                    new RegionPart("-upper"){{
                        mirror = true;
                        under = true;
                        moveX = 12f;
                        moveY = -1f;
                        moveRot = -10;
                        progress = PartProgress.warmup;
                    }},
                    new RegionPart("-blade"){{
                        mirror = true;
                        under = true;
                        moveY = 3.5f;
                        moveX = 4f;
                        moveRot = 25;
                        progress = PartProgress.warmup;
                    }}
                );
            }};

            minWarmup = 0.8f;

            shootWarmupSpeed = 0.05f;

            scaledHealth = 300;
            range = 400f;
            size = 4;
        }};

        anode = new PowerTurret("anode"){{
            requirements(Category.turret, with(Items.copper, 10));

            shootType = FFBullets.Anode_0;

            reload = 120f;
            shootY = 10f;
            rotateSpeed = 5f;
            shootCone = 15f;
            consumeAmmoOnce = true;
            shootSound = FFSounds.InfernoShoot;

            moveWhileCharging = false;
            accurateDelay = false;

            consumePower(1500/60f);

            drawer = new DrawTurret(){{
                parts.addAll(
                    new RegionPart("-middle"){{
                        moveY = 2f;
                        progress = PartProgress.warmup;
                    }},
                    new RegionPart("-ground"),
                    new RegionPart("-blade"){{
                        mirror = true;
                        moveY = 3.5f;
                        moveX = 4f;
                        moveRot = 10;
                        progress = PartProgress.warmup;
                    }},
                    new RegionPart("-center"){{
                        mirror = true;
                        under = true;
                        moveX = 1f;
                        moveY = -2f;
                        moveRot = -10;
                        progress = PartProgress.warmup;
                    }},
                    new RegionPart("-upper"){{
                        mirror = true;
                        under = true;
                        moveX = 6f;
                        moveY = -1f;
                        moveRot = 0;
                        progress = PartProgress.warmup;
                    }},
                    new RegionPart("-upper"){{
                        mirror = true;
                        under = true;
                        moveX = 4f;
                        moveY = -2f;
                        moveRot = -45;
                        progress = PartProgress.warmup;
                    }}
                );
            }};

            minWarmup = 0.8f;

            shootWarmupSpeed = 0.05f;

            scaledHealth = 300;
            range = 380f;
            size = 4;
        }};

        testTurret = new ItemTurret("test-turret"){{
            requirements(Category.turret, with(Items.copper, 10));

            ammo(
                Items.graphite, FFBullets.Crysta_0
            );

            drawer = new DrawTurret(){{
                parts.addAll(
                    new RegionPart("-upper")
                );
            }};
            reload = 20f;
            shootY = 10f;
            rotateSpeed = 5f;
            shootCone = 15f;
            consumeAmmoOnce = true;
            shootSound = FFSounds.InfernoShoot;

            shootWarmupSpeed = 0.08f;

            scaledHealth = 300;
            range = 1200f;
            size = 2;

            limitRange(-5f);
        }};
    }
}
