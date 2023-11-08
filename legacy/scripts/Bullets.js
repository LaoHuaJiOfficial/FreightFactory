const FFEffects = this.global.FFEffects
//----焦点极姬射器的子弹
const 焦点tmpColor = Color();
//焦点级焦点的颜色最外层(RGB最后两个是透明度)  中间层中间层 中心
const 焦点colors = [Color.valueOf("676bd455"), Color.valueOf("a3a5d4"), Color.valueOf("eeeeff"), Color.valueOf("ffffff")];
const 焦点tscales = [1, 0.7, 0.5, 0.2];
const 焦点strokes = [1, 0.5, 0.3];
const 焦点lenscales = [1, 1.12, 1.15, 1.17];
const 焦点length = 195;
//tscales.all * width
const 融合length = 100;
const 融合2length = 100;

const size = 3
const 光棱tmpColor = Color();
//光棱级光棱的颜色最外层(RGB最后两个是透明度)  中间层中间层 中心
const 光棱tscales = [0.5*size, 0.4*size, 0.3*size, 0.2*size];
const 光棱strokes = [1*size, 0.5*size, 0.3*size];
const 光棱lenscales = [1, 1.12, 1.15, 1.17];
const 光棱length = 450;

const FFBullets = {
    余晖1: extend(ArtilleryBulletType, {
        hit(b) {
            Damage.dynamicExplosion(b.x, b.y, 99/*燃烧等级*/, 50/*爆炸*/, 0/*功率*/, 19/*范围*/, Pal.darkFlame);
            Effects.effect(Fx.nuclearsmoke, b.x, b.y);
            Effects.effect(Fx.explosion, b.x, b.y);
            Effects.effect(Fx.nuclearShockwave, b.x, b.y);
            Effects.effect(Fx.nuclearcloud, b.x, b.y)
            for (var i = 0; i < 2; i++) {
                Bullet.create(FFBullets.余晖1_1, b, b.x, b.y, Mathf.random(360),50);
                
            }

        }
    }),
    余晖1_1: extend(ArtilleryBulletType, {}),
    铝罐炮1: extend(ArtilleryBulletType, {}),
    铝罐炮2: extend(ArtilleryBulletType, {}),
    铝罐炮3: extend(ArtilleryBulletType, {}),
    硬糖炮1: extend(ArtilleryBulletType, {}),
    碎糖炮1: extend(BasicBulletType, {}),
    链式炮1: extend(BasicBulletType, {}),
    链式炮2分裂: extend(BasicBulletType, {}),
    链式炮2: extend(BasicBulletType, {}),
    碎晶炮1分裂: extend(BasicBulletType, {}),
    碎晶炮1: extend(BasicBulletType, {}),
    碎晶炮2: extend(BasicBulletType, {}),
    碎晶炮3分裂: extend(BasicBulletType, {}),
    碎晶炮3: extend(BasicBulletType, {}),
    碎晶炮4分裂: extend(BasicBulletType, {}),
    碎晶炮4: extend(BasicBulletType, {}),
    阻滞炮1: extend(BasicBulletType, {}),
    阻滞炮2: extend(BasicBulletType, {}),
    消融炮1: extend(BasicBulletType, {}),
    增殖炮1: extend(BasicBulletType, {}),
    突触炮1: extend(MissileBulletType, {}),
    铁幕子弹1: extend(MissileBulletType, {}),
    铁幕子弹2: extend(MissileBulletType, {}),
    炎阳3: extend(MissileBulletType, {}),
    U5妖火3: extend(MissileBulletType, {}),
    chzpb: extend(BasicBulletType, {}),
    chzpb1: extend(BasicBulletType, {}),
    chzpb2: extend(BasicBulletType, {}),
    chzpb3: extend(BasicBulletType, {}),
    chzpb4: extend(BasicBulletType, {}),
    chzpb5: extend(BasicBulletType, {}),
    chzpb6: extend(BasicBulletType, {}),
    糖浆子弹: extend(LiquidBulletType, {}),
    可乐子弹: extend(LiquidBulletType, {}),
    冰镇可乐子弹: extend(LiquidBulletType, {}),
    碎冰刃1: extend(BasicBulletType, {
        update(b) {
            if (b.timer.get(1, 1.725)) {
                Effects.effect(FFEffects.尾迹, Color.valueOf("#c2d0e9"), b.x, b.y, b.rot());
            }
        }
    }),
    碎冰刃2: extend(BasicBulletType, {
        update(b) {
            if (b.timer.get(1, 1.725)) {
                Effects.effect(FFEffects.尾迹4, Color.valueOf("#c2d0e9"), b.x, b.y, b.rot());
            }
        }
    }),
    U5碎冰刃1: extend(BasicBulletType, {
        update(b) {
            if (b.timer.get(1, 1.725)) {
                Effects.effect(FFEffects.尾迹, Color.valueOf("#c2d0e9"), b.x, b.y, b.rot());
            }
        }
    }),
    晶束炮1: extend(BasicBulletType, {
        update(b) {
            if (b.timer.get(0.2, 0.5)) {
                Effects.effect(FFEffects.尾迹2, Color.valueOf("#c2d0e9"), b.x, b.y, b.rot());
            }
        }
    }),
    晶束炮2: extend(BasicBulletType, {
        update(b) {
            if (b.timer.get(0.2, 0.5)) {
                Effects.effect(FFEffects.尾迹3, Color.valueOf("#c2d0e9"), b.x, b.y, b.rot());
            }
        }
    }),
    U5晶束炮1: extend(BasicBulletType, {
        update(b) {
            if (b.timer.get(0.2, 0.5)) {
                Effects.effect(FFEffects.尾迹2, Color.valueOf("#c2d0e9"), b.x, b.y, b.rot());
            }
        }
    }),
    爆裂炮1: extend(MissileBulletType, {
        update(b) {
            if (b.timer.get(1, 1.725)) {
                Effects.effect(FFEffects.尾迹5, Color.valueOf("e77831"), b.x, b.y, b.rot());
            }
        }
    }),
    融合炮1: extend(BasicBulletType, {
        range() {
            return 融合length;
        },
        // init(b){
        // },
        update(b) {
            if (b.time() < 0.001) {
                Damage.collideLine(b, b.getTeam(), this.hitEffect, b.x, b.y, b.rot(), 融合length);
            }
        },
        draw(b) {
            const rayLength = 160;

            Draw.color(Color.white, Pal.lancerLaser, b.fin());
            //Draw.alpha(b.fout());
            for (var i = 0; i < 7; i++) {
                Tmp.v1.trns(b.rot(), i * 8);
                const sl = Mathf.clamp(b.fout() - 0.5) * (80 - i * 10);
                Drawf.tri(b.x + Tmp.v1.x, b.y + Tmp.v1.y, 4, sl, b.rot() + 90);
                Drawf.tri(b.x + Tmp.v1.x, b.y + Tmp.v1.y, 4, sl, b.rot() - 90);
            }
            Drawf.tri(b.x, b.y, 20 * b.fout(), (rayLength + 50), b.rot());
            Drawf.tri(b.x, b.y, 20 * b.fout(), 10, b.rot() + 180);
            Draw.reset();
        }
    }),
    铝罐炮4: extend(ArtilleryBulletType, {
        hit(b) {
            Damage.dynamicExplosion(b.x, b.y, 0.2/*燃烧等级*/, 70/*爆炸*/, 0/*功率*/, 4/*范围*/, Pal.darkFlame);
            Effects.effect(Fx.nuclearsmoke, b.x, b.y);
            Effects.effect(Fx.explosion, b.x, b.y);
            Effects.effect(Fx.nuclearShockwave, b.x, b.y);
            Effects.effect(Fx.nuclearcloud, b.x, b.y)

        }
    }),
    铁幕子弹3: extend(MissileBulletType, {
        hit(b) {
            Damage.dynamicExplosion(b.x, b.y, 0.8/*燃烧等级*/, 50/*爆炸*/, 0/*功率*/, 19/*范围*/, Pal.darkFlame);
            Effects.effect(Fx.nuclearsmoke, b.x, b.y);
            Effects.effect(Fx.explosion, b.x, b.y);
            Effects.effect(Fx.nuclearShockwave, b.x, b.y);
            Effects.effect(Fx.nuclearcloud, b.x, b.y)
        }
    }),
    炎阳1: extend(MissileBulletType, {
        hit(b) {

            Bullet.create(FFBullets.炎阳2, b, b.x, b.y, b.rot() + 10);
            Bullet.create(FFBullets.炎阳2, b, b.x, b.y, b.rot() - 10);
        }
    }),
    炎阳2: extend(MissileBulletType, {
        hit(b) {

            Bullet.create(FFBullets.炎阳3, b, b.x, b.y, b.rot() + 10);
            Bullet.create(FFBullets.炎阳3, b, b.x, b.y, b.rot() - 0);
            Bullet.create(FFBullets.炎阳3, b, b.x, b.y, b.rot() - 10);
        }
    }),
    U5妖火1: extend(MissileBulletType, {
        hit(b) {

            Bullet.create(FFBullets.U5妖火2, b, b.x, b.y, b.rot() + 10);
            Bullet.create(FFBullets.U5妖火2, b, b.x, b.y, b.rot() - 10);
        }
    }),
    U5妖火2: extend(MissileBulletType, {
        hit(b) {

            Bullet.create(FFBullets.U5妖火3, b, b.x, b.y, b.rot() + 10);
            Bullet.create(FFBullets.U5妖火3, b, b.x, b.y, b.rot() - 0);
            Bullet.create(FFBullets.U5妖火3, b, b.x, b.y, b.rot() - 10);
        }
    }),
    融合炮2: extend(BasicBulletType, {
        range() {
            return 融合2length;
        },
        // init(b){
        // },
        update(b) {
            if (b.time() < 0.001) {
                Damage.collideLine(b, b.getTeam(), this.hitEffect, b.x, b.y, b.rot(), 融合2length);
            }
        },
        draw(b) {
            const rayLength = 200;

            Draw.color(Color.white, Pal.lancerLaser, b.fin());
            //Draw.alpha(b.fout());
            for (var i = 0; i < 7; i++) {
                Tmp.v1.trns(b.rot(), i * 8);
                const sl = Mathf.clamp(b.fout() - 0.5) * (80 - i * 10);
                Drawf.tri(b.x + Tmp.v1.x, b.y + Tmp.v1.y, 4, sl, b.rot() + 90);
                Drawf.tri(b.x + Tmp.v1.x, b.y + Tmp.v1.y, 4, sl, b.rot() - 90);
            }
            Drawf.tri(b.x, b.y, 20 * b.fout(), (rayLength + 50), b.rot());
            Drawf.tri(b.x, b.y, 20 * b.fout(), 10, b.rot() + 180);
            Draw.reset();
        }
    }),
    焦点激光: extend(BasicBulletType, {
        update(b) {

            Effects.shake(1, 1, b.x, b.y);
            const target = Units.closestTarget(b.getTeam(), b.x, b.y, 190)
            if (target != null) {

                if (b.timer.get(1, 9)) {
                    Damage.collideLine(b, b.getTeam(), this.hitEffect, b.x, b.y, b.rot(), Mathf.dst(b.x, b.y, target.x, target.y), true);

                    Lightning.create(b.getTeam(), Color.valueOf("eeeeff"), 30, target.x, target.y, Mathf.random(360), Mathf.random(30));
                }

            }

        },
        hit(b, hitx, hity) {
            Effects.effect(this.hitEffect, 焦点colors[2], hitx, hity);
            if (Mathf.chance(0.4)) {
                Fire.create(Vars.world.tileWorld(hitx + Mathf.range(5), hity + Mathf.range(5)));
            }
        },
        draw(b) {
            const target = Units.closestTarget(b.getTeam(), b.x, b.y, 190)
            var baseLen = (焦点length) * b.fout();
            if (target != null) {
                baseLen = (Mathf.dst(b.x, b.y, target.x, target.y)) * b.fout();
            }

            Lines.lineAngle(b.x, b.y, b.rot(), baseLen);
            for (var s = 0; s < 焦点colors.length; s++) {
                Draw.color(焦点tmpColor.set(焦点colors[s]).mul(1 + Mathf.absin(Time.time(), 1, 0.1)));
                for (var i = 0; i < 焦点tscales.length; i++) {
                    //Tmp.v1.trns(b.rot() + 180, (lenscales[i] - 1) * 35);
                    Lines.stroke((9 + Mathf.absin(Time.time(), 0.8, 1.5)) * b.fout() * 焦点strokes[s] * 焦点tscales[i]);
                    Lines.lineAngle(b.x, b.y, b.rot(), baseLen * 焦点lenscales[i]);
                }
            }
            Draw.reset();
        }
    }),

    光棱: extend(BasicBulletType, {
        update(b) {

            Effects.shake(1, 1, b.x, b.y);
            const target = Units.closestTarget(b.getTeam(), b.x, b.y, 400)
            if (b.timer.get(1, 9)) {
                Damage.collideLine(b, b.getTeam(), this.hitEffect, b.x, b.y, b.rot(), 光棱length, true);

            }
            if (target != null) {
                Lightning.create(b.getTeam(), Color.valueOf("eeeeff"), 30, target.x, target.y, Mathf.random(360), Mathf.random(30));
            }

        },
        hit(b, hitx, hity) {
            // Effects.effect(this.hitEffect,Color.HSVtoRGB((Mathf.sin(Time.time(), 30 * 2, 360) + 360) / 2,50,100), hitx, hity);
            if (Mathf.chance(0.4)) {
                Fire.create(Vars.world.tileWorld(hitx + Mathf.range(5), hity + Mathf.range(5)));
            }
        },
        draw(b) {//entity.bulletLife
            const 光棱colors = [Color.HSVtoRGB((Mathf.sin(Time.time(), 50 * 2, 360) + 360) / 2,70,100), Color.HSVtoRGB((Mathf.sin(Time.time(), 40 * 2, 360) + 360) / 2,50,100),Color.HSVtoRGB((Mathf.sin(Time.time(), 10 * 2, 360) + 360) / 2,32,100),Color.HSVtoRGB((Mathf.sin(Time.time(), 30 * 2, 360) + 360) / 2,20,100)];

            const target = Units.closestTarget(b.getTeam(), b.x, b.y, 190)
            var baseLen = (光棱length) * b.fout();
            // if (target != null) {
            //     baseLen = (Mathf.dst(b.x, b.y, target.x, target.y)) * b.fout();
            // }

            Lines.lineAngle(b.x, b.y, b.rot(), baseLen);
            for (var s = 0; s < 光棱colors.length; s++) {
                Draw.color(光棱tmpColor.set(光棱colors[s]).mul(1 + Mathf.absin(Time.time(), 1, 0.1)));
                for (var i = 0; i < 光棱tscales.length; i++) {
                    //Tmp.v1.trns(b.rot() + 180, (lenscales[i] - 1) * 35);
                    Lines.stroke((9 + Mathf.absin(Time.time(), 0.8, 1.5)) * b.fout() * 光棱strokes[s] * 光棱tscales[i]);
                    Lines.lineAngle(b.x, b.y, b.rot(), baseLen * 光棱lenscales[i],CapStyle.round);
                }
            }
            Draw.reset();

        }
    }),
    球形闪电: extend(ArtilleryBulletType, {
        update(b) {
            if (Mathf.chance(Time.delta() * 0.8)) {
                for (var i = 0; i < Mathf.random(4); i++) {
                    Lightning.create(b.getTeam(), Color.valueOf("c1cfe9"), 2, b.x, b.y, Mathf.random(360), Mathf.random(25));
                }

            }
            if (Mathf.chance(Time.delta() * 1.2)) {
                for (var i = 0; i < Mathf.random(4); i++) {
                    Lightning.create(b.getTeam(), Pal.lancerLaser, 5, b.x, b.y, Mathf.random(360), 6);
                }

            }
        },
        hit(b) {
            for (var i = 0; i < 20; i++) {
                Lightning.create(b.getTeam(), Color.valueOf("9ba7bf"), 5, b.x, b.y, Mathf.random(360), 7);
            }
        },
        draw(b) {
            Draw.rect(Core.atlas.find("食品工厂-M5"), b.x, b.y, this.bulletWidth, this.bulletHeight);
            for (var i = 0; i < 4; i++) {
                //Draw.color(Color.valueOf("946b9b"),Color.valueOf("fadfff"), i / 4);
                //Draw.rect(Core.atlas.find("食品工厂-球形闪电" + i),b.x,b.y,this.bulletWidth,this.bulletHeight,Time.time() * (12 + i *Mathf.random(40)));
            }
        }

    })


}



FFBullets.余晖1.speed = 8
FFBullets.余晖1.damage = 80
FFBullets.余晖1.knockback = 0
FFBullets.余晖1.splashDamageRadius = 32
FFBullets.余晖1.splashDamage = 50
FFBullets.余晖1.bulletWidth = 23 + 20
FFBullets.余晖1.bulletHeight = 30 + 20
FFBullets.余晖1.shootEffect = Fx.shootBig2
FFBullets.余晖1.ammoMultiplier = 2
FFBullets.余晖1.backColor = Color.valueOf("FF0000")
FFBullets.余晖1.fragBullet = FFBullets.余晖1_1





FFBullets.余晖1_1.speed = 8
FFBullets.余晖1_1.damage = 80
FFBullets.余晖1_1.knockback = 0
FFBullets.余晖1_1.splashDamageRadius = 32
FFBullets.余晖1_1.splashDamage = 50
FFBullets.余晖1_1.bulletWidth = 23
FFBullets.余晖1_1.bulletHeight = 30
FFBullets.余晖1_1.shootEffect = Fx.shootBig2
FFBullets.余晖1_1.ammoMultiplier = 2
FFBullets.余晖1_1.backColor = Color.valueOf("FF0000")
FFBullets.余晖1_1.lifetime = 300, 















FFBullets.chzpb.speed = 8
FFBullets.chzpb.damage = 80
FFBullets.chzpb.knockback = 0
FFBullets.chzpb.splashDamageRadius = 32
FFBullets.chzpb.splashDamage = 50
FFBullets.chzpb.bulletWidth = 23
FFBullets.chzpb.bulletHeight = 30
FFBullets.chzpb.shootEffect = Fx.shootBig2
FFBullets.chzpb.ammoMultiplier = 2
FFBullets.chzpb.backColor = Color.valueOf("613d91")

FFBullets.chzpb.smokeEffect = newEffect(25, e => {
    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("9a6cd9"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2, 45);
        }
    })

    Angles.randLenVectors(e.id, 3, 1 + e.fin() * 20, d);

});

FFBullets.chzpb.hitEffect = newEffect(15, e => {
    Draw.color(Color.valueOf("613d91"), Color.valueOf("613d91"), e.fin());
    const d = new Floatc2({
        get(x, y) {
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 7 + 4);
            Lines.stroke(e.fout() * 1.25);
            Lines.circle(e.x, e.y, e.fin() * 24);
            Lines.stroke(e.fout() * 3);
            Lines.circle(e.x, e.y, e.fin() * 50);
        }
    })
    Angles.randLenVectors(e.id, 6, 1 + 30 * e.fin(), e.rotation, 360, d);
});
//-----------------------红
FFBullets.chzpb1.speed = 8
FFBullets.chzpb1.damage = 80
FFBullets.chzpb1.knockback = 0
FFBullets.chzpb1.splashDamageRadius = 32
FFBullets.chzpb1.splashDamage = 50
FFBullets.chzpb1.bulletWidth = 23
FFBullets.chzpb1.bulletHeight = 30
FFBullets.chzpb1.shootEffect = Fx.shootBig2
FFBullets.chzpb1.ammoMultiplier = 2
FFBullets.chzpb1.backColor = Color.valueOf("bb5f5f")

FFBullets.chzpb1.smokeEffect = newEffect(25, e => {
    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("ea9898"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2, 45);
        }
    })

    Angles.randLenVectors(e.id, 3, 1 + e.fin() * 20, d);

});

FFBullets.chzpb1.hitEffect = newEffect(15, e => {
    Draw.color(Color.valueOf("ea9898"), Color.valueOf("bb5f5f"), e.fin());
    const d = new Floatc2({
        get(x, y) {
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 7 + 4);
            Lines.stroke(e.fout() * 1.25);
            Lines.circle(e.x, e.y, e.fin() * 24);
            Lines.stroke(e.fout() * 3);
            Lines.circle(e.x, e.y, e.fin() * 50);
        }
    })
    Angles.randLenVectors(e.id, 6, 1 + 30 * e.fin(), e.rotation, 360, d);
});
//-----------------------橙
FFBullets.chzpb2.speed = 8
FFBullets.chzpb2.damage = 80
FFBullets.chzpb2.knockback = 0
FFBullets.chzpb2.splashDamageRadius = 32
FFBullets.chzpb2.splashDamage = 50
FFBullets.chzpb2.bulletWidth = 23
FFBullets.chzpb2.bulletHeight = 30
FFBullets.chzpb2.shootEffect = Fx.shootBig2
FFBullets.chzpb2.ammoMultiplier = 2
FFBullets.chzpb2.backColor = Color.valueOf("c7704f")

FFBullets.chzpb2.smokeEffect = newEffect(25, e => {
    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("c7704f"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2, 45);
        }
    })

    Angles.randLenVectors(e.id, 3, 1 + e.fin() * 20, d);

});

FFBullets.chzpb2.hitEffect = newEffect(15, e => {
    Draw.color(Color.valueOf("ee926f"), Color.valueOf("c7704f"), e.fin());
    const d = new Floatc2({
        get(x, y) {
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 7 + 4);
            Lines.stroke(e.fout() * 1.25);
            Lines.circle(e.x, e.y, e.fin() * 24);
            Lines.stroke(e.fout() * 3);
            Lines.circle(e.x, e.y, e.fin() * 50);
        }
    })
    Angles.randLenVectors(e.id, 6, 1 + 30 * e.fin(), e.rotation, 360, d);
});
//-----------------------黄
FFBullets.chzpb3.speed = 8
FFBullets.chzpb3.damage = 80
FFBullets.chzpb3.knockback = 0
FFBullets.chzpb3.splashDamageRadius = 32
FFBullets.chzpb3.splashDamage = 50
FFBullets.chzpb3.bulletWidth = 23
FFBullets.chzpb3.bulletHeight = 30
FFBullets.chzpb3.shootEffect = Fx.shootBig2
FFBullets.chzpb3.ammoMultiplier = 2
FFBullets.chzpb3.backColor = Color.valueOf("b6a958")

FFBullets.chzpb3.smokeEffect = newEffect(25, e => {
    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("b6a958"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2, 45);
        }
    })

    Angles.randLenVectors(e.id, 3, 1 + e.fin() * 20, d);

});

FFBullets.chzpb3.hitEffect = newEffect(15, e => {
    Draw.color(Color.valueOf("eadf98"), Color.valueOf("b6a958"), e.fin());
    const d = new Floatc2({
        get(x, y) {
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 7 + 4);
            Lines.stroke(e.fout() * 1.25);
            Lines.circle(e.x, e.y, e.fin() * 24);
            Lines.stroke(e.fout() * 3);
            Lines.circle(e.x, e.y, e.fin() * 50);
        }
    })
    Angles.randLenVectors(e.id, 6, 1 + 30 * e.fin(), e.rotation, 360, d);
});
//-----------------------绿
FFBullets.chzpb4.speed = 8
FFBullets.chzpb4.damage = 80
FFBullets.chzpb4.knockback = 0
FFBullets.chzpb4.splashDamageRadius = 32
FFBullets.chzpb4.splashDamage = 50
FFBullets.chzpb4.bulletWidth = 23
FFBullets.chzpb4.bulletHeight = 30
FFBullets.chzpb4.shootEffect = Fx.shootBig2
FFBullets.chzpb4.ammoMultiplier = 2
FFBullets.chzpb4.backColor = Color.valueOf("6fb958")

FFBullets.chzpb4.smokeEffect = newEffect(25, e => {
    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("6fb958"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2, 45);
        }
    })

    Angles.randLenVectors(e.id, 3, 1 + e.fin() * 20, d);

});

FFBullets.chzpb4.hitEffect = newEffect(15, e => {
    Draw.color(Color.valueOf("acea98"), Color.valueOf("6fb958"), e.fin());
    const d = new Floatc2({
        get(x, y) {
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 7 + 4);
            Lines.stroke(e.fout() * 1.25);
            Lines.circle(e.x, e.y, e.fin() * 24);
            Lines.stroke(e.fout() * 3);
            Lines.circle(e.x, e.y, e.fin() * 50);
        }
    })
    Angles.randLenVectors(e.id, 6, 1 + 30 * e.fin(), e.rotation, 360, d);
});
//-----------------------青
FFBullets.chzpb5.speed = 8
FFBullets.chzpb5.damage = 80
FFBullets.chzpb5.knockback = 0
FFBullets.chzpb5.splashDamageRadius = 32
FFBullets.chzpb5.splashDamage = 50
FFBullets.chzpb5.bulletWidth = 23
FFBullets.chzpb5.bulletHeight = 30
FFBullets.chzpb5.shootEffect = Fx.shootBig2
FFBullets.chzpb5.ammoMultiplier = 2
FFBullets.chzpb5.backColor = Color.valueOf("55bbad")

FFBullets.chzpb5.smokeEffect = newEffect(25, e => {
    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("55bbad"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2, 45);
        }
    })

    Angles.randLenVectors(e.id, 3, 1 + e.fin() * 20, d);

});

FFBullets.chzpb5.hitEffect = newEffect(15, e => {
    Draw.color(Color.valueOf("98eadf"), Color.valueOf("55bbad"), e.fin());
    const d = new Floatc2({
        get(x, y) {
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 7 + 4);
            Lines.stroke(e.fout() * 1.25);
            Lines.circle(e.x, e.y, e.fin() * 24);
            Lines.stroke(e.fout() * 3);
            Lines.circle(e.x, e.y, e.fin() * 50);
        }
    })
    Angles.randLenVectors(e.id, 6, 1 + 30 * e.fin(), e.rotation, 360, d);
});
//-----------------------蓝
FFBullets.chzpb6.speed = 8
FFBullets.chzpb6.damage = 80
FFBullets.chzpb6.knockback = 0
FFBullets.chzpb6.splashDamageRadius = 32
FFBullets.chzpb6.splashDamage = 50
FFBullets.chzpb6.bulletWidth = 23
FFBullets.chzpb6.bulletHeight = 30
FFBullets.chzpb6.shootEffect = Fx.shootBig2
FFBullets.chzpb6.ammoMultiplier = 2
FFBullets.chzpb6.backColor = Color.valueOf("4f96bc")

FFBullets.chzpb6.smokeEffect = newEffect(25, e => {
    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("4f96bc"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2, 45);
        }
    })

    Angles.randLenVectors(e.id, 3, 1 + e.fin() * 20, d);

});

FFBullets.chzpb6.hitEffect = newEffect(15, e => {
    Draw.color(Color.valueOf("6fbde7"), Color.valueOf("4f96bc"), e.fin());
    const d = new Floatc2({
        get(x, y) {
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 7 + 4);
            Lines.stroke(e.fout() * 1.25);
            Lines.circle(e.x, e.y, e.fin() * 24);
            Lines.stroke(e.fout() * 3);
            Lines.circle(e.x, e.y, e.fin() * 50);
        }
    })
    Angles.randLenVectors(e.id, 6, 1 + 30 * e.fin(), e.rotation, 360, d);
});
//=========================================================================================================================================================================================================================================================================================================================================


FFBullets.chzpb.frontColor = Color.valueOf("9a6cd9")
FFBullets.chzpb1.frontColor = Color.valueOf("ea9898")
FFBullets.chzpb2.frontColor = Color.valueOf("ee926f")
FFBullets.chzpb3.frontColor = Color.valueOf("eadf98")
FFBullets.chzpb4.frontColor = Color.valueOf("acea98")
FFBullets.chzpb5.frontColor = Color.valueOf("98eadf")
FFBullets.chzpb6.frontColor = Color.valueOf("6fbde7")



FFBullets.铝罐炮1.speed = 5, 
FFBullets.铝罐炮1.damage = 0, 
FFBullets.铝罐炮1.knockback = 0, 
FFBullets.铝罐炮1.splashDamageRadius = 10, 
FFBullets.铝罐炮1.splashDamage = 30, 
FFBullets.铝罐炮1.bulletWidth = 14, 
FFBullets.铝罐炮1.bulletHeight = 16, 
FFBullets.铝罐炮1.shootEffect = Fx.shootBigSmoke, 
FFBullets.铝罐炮1.ammoMultiplier = 3, 
FFBullets.铝罐炮1.lifetime = 300, 
FFBullets.铝罐炮1.backColor = Color.valueOf("ababab"), 
FFBullets.铝罐炮1.frontColor = Color.valueOf("d3d4d3")
FFBullets.铝罐炮1.smokeEffect = newEffect(30, e => {

    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("d3d4d3"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2, 45);
        }
    })

    Angles.randLenVectors(e.id, 5, 1 + e.fin() * 12, d);


});
FFBullets.铝罐炮1.hitEffect = newEffect(10, e => {

    Draw.color(Color.valueOf("ababab"));

    const c = new Floatc2({
        get(x, y) {
            Fill.circle(e.x + x, e.y + y, e.fout() * 2);
        }
    })
    Angles.randLenVectors(e.id, 5, 1 + 13 * e.fin(), e.rotation, 360, c);

    Draw.color(Color.valueOf("d3d4d3"));
    Lines.stroke(e.fout() * 4 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 13);

});
FFBullets.铝罐炮2.speed = 3
FFBullets.铝罐炮2.damage = 0
FFBullets.铝罐炮2.knockback = 0
FFBullets.铝罐炮2.splashDamageRadius = 25
FFBullets.铝罐炮2.splashDamage = 35
FFBullets.铝罐炮2.bulletWidth = 14
FFBullets.铝罐炮2.bulletHeight = 16
FFBullets.铝罐炮2.shootEffect = Fx.shootBigSmoke
FFBullets.铝罐炮2.ammoMultiplier = 3
FFBullets.铝罐炮2.lifetime = 600
FFBullets.铝罐炮2.backColor = Color.valueOf("ababab")
FFBullets.铝罐炮2.frontColor = Color.valueOf("d3d4d3")

FFBullets.铝罐炮2.smokeEffect = newEffect(30, e => {

    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("ababab"));

            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2, 45);
        }
    })

    Angles.randLenVectors(e.id, 4, 1 + e.fin() * 18, d);


});

FFBullets.铝罐炮2.hitEffect = newEffect(10, e => {

    Draw.color(Color.valueOf("ababab"));

    const c = new Floatc2({
        get(x, y) {
            Fill.circle(e.x + x, e.y + y, e.fout() * 3);
        }
    })
    Angles.randLenVectors(e.id, 5, 1 + 20 * e.fin(), e.rotation, 360, c);

    Draw.color(Color.valueOf("d3d4d3"));
    Lines.stroke(e.fout() * 5 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 25);

});


FFBullets.铝罐炮3.speed = 2
FFBullets.铝罐炮3.damage = 0
FFBullets.铝罐炮3.knockback = 0
FFBullets.铝罐炮3.splashDamageRadius = 32
FFBullets.铝罐炮3.splashDamage = 50
FFBullets.铝罐炮3.bulletWidth = 15
FFBullets.铝罐炮3.bulletHeight = 17
FFBullets.铝罐炮3.shootEffect = Fx.shootBigSmoke
FFBullets.铝罐炮3.ammoMultiplier = 2
FFBullets.铝罐炮3.lifetime = 60
FFBullets.铝罐炮3.backColor = Color.valueOf("ababab")
FFBullets.铝罐炮3.frontColor = Color.valueOf("d3d4d3")
FFBullets.铝罐炮3.smokeEffect = newEffect(40, e => {

    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("ababab"));

            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2.5, 45);
        }
    })

    Angles.randLenVectors(e.id, 5, 1 + e.fin() * 18, d);


});
FFBullets.铝罐炮3.hitEffect = newEffect(13, e => {

    Draw.color(Color.valueOf("ababab"));

    const c = new Floatc2({
        get(x, y) {
            Fill.circle(e.x + x, e.y + y, e.fout() * 5);
        }
    })
    Angles.randLenVectors(e.id, 5, 1 + 25 * e.fin(), e.rotation, 360, c);

    Draw.color(Color.valueOf("d3d4d3"));
    Lines.stroke(e.fout() * 4 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 32);

});
FFBullets.铝罐炮4.speed = 2.5
FFBullets.铝罐炮4.damage = 0
FFBullets.铝罐炮4.knockback = 0
FFBullets.铝罐炮4.splashDamageRadius = 32
FFBullets.铝罐炮4.splashDamage = 50
FFBullets.铝罐炮4.bulletWidth = 15
FFBullets.铝罐炮4.bulletHeight = 20
FFBullets.铝罐炮4.hitEffect = Fx.flakExplosion
FFBullets.铝罐炮4.ammoMultiplier = 1
FFBullets.铝罐炮4.lifetime = 60
FFBullets.铝罐炮4.backColor = Color.valueOf("52b245")
FFBullets.铝罐炮4.frontColor = Color.valueOf("89e87c")
FFBullets.铝罐炮4.reloadMultiplier = 0.2;
FFBullets.铝罐炮4.shootEffect = newEffect(35, e => {

    Draw.color(Color.valueOf("ababab"))

    const c = new Floatc2({
        get(x, y) {
            Fill.circle(e.x + x, e.y + y, e.fout() * 5);
        }
    })
    Angles.randLenVectors(e.id, 10, 1 + 30 * e.fin(), e.rotation, 10, c);

});
FFBullets.铝罐炮4.smokeEffect = newEffect(40, e => {

    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("ababab"));

            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 3, 45);
        }
    })

    Angles.randLenVectors(e.id, 8, 1 + e.fin() * 18, d);

});

FFBullets.硬糖炮1.speed = 2.5
FFBullets.硬糖炮1.knockback = 1.5
FFBullets.硬糖炮1.damage = 0
FFBullets.硬糖炮1.splashDamageRadius = 50
FFBullets.硬糖炮1.splashDamage = 280
FFBullets.硬糖炮1.bulletWidth = 24
FFBullets.硬糖炮1.bulletHeight = 30
FFBullets.硬糖炮1.shootEffect = Fx.shootLiquid
FFBullets.硬糖炮1.smokeEffect = newEffect(40, e => {

        const d = new Floatc2({
            get(x, y) {
                Draw.color(Color.valueOf("a798c1"), Color.valueOf("e6e0ff"), e.fin());
                Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 3, 45);
            }
        })

        Angles.randLenVectors(e.id, 15, 5 + e.fin() * 18, d);


});
FFBullets.硬糖炮1.ammoMultiplier = 1
FFBullets.硬糖炮1.lifetime = 600
FFBullets.硬糖炮1.backColor = Color.valueOf("a798c1")
FFBullets.硬糖炮1.frontColor = Color.valueOf("e6e0ff")
FFBullets.硬糖炮1.hitEffect = newEffect(20, e => {

    Draw.color(Color.valueOf("a798c1"))
    Lines.stroke(e.fout() * 2.5 + 0.1)
    Lines.circle(e.x, e.y, e.fin() * 55)

    Fill.circle(e.x, e.y, e.fout() * 15)
    Fill.circle(e.x, e.y, e.fout() * 5)

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("a798c1"), Color.valueOf("e6e0ff"), e.fin())
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 3, 45)
        }
    })

    Angles.randLenVectors(e.id, 15, 1 + e.fin() * 60, d)


});
FFBullets.碎糖炮1.speed = 8
FFBullets.碎糖炮1.damage = 70
FFBullets.碎糖炮1.knockback = 0.5
FFBullets.碎糖炮1.bulletWidth = 18
FFBullets.碎糖炮1.bulletHeight = 21
FFBullets.碎糖炮1.shootEffect = Fx.shootBig2
FFBullets.碎糖炮1.smokeEffect = Fx.blockExplosionSmoke
FFBullets.碎糖炮1.ammoMultiplier = 4
FFBullets.碎糖炮1.lifetime = 32
FFBullets.碎糖炮1.backColor = Color.valueOf("a798c1")
FFBullets.碎糖炮1.frontColor = Color.valueOf("e6e0ff")
FFBullets.碎糖炮1.despawnEffect = newEffect(12, e => {

    Draw.color(Color.valueOf("a798c1"));
    Fill.circle(e.x, e.y, e.fout() * 5);
    Fill.circle(e.x, e.y, e.fout() * 3);

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("a798c1"), Color.valueOf("e6e0ff"), e.fin());
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 3, 45);
        }
    })

    Angles.randLenVectors(e.id, 15, 1 + e.fin() * 20, d);


});

FFBullets.碎糖炮1.hitEffect = newEffect(12, e => {

    Draw.color(Color.valueOf("a798c1"))
    Fill.circle(e.x, e.y, e.fout() * 5)
    Fill.circle(e.x, e.y, e.fout() * 3)

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("a798c1"), Color.valueOf("e6e0ff"), e.fin());
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 3, 45);
        }
    })

    Angles.randLenVectors(e.id, 15, 1 + e.fin() * 20, d);

});


FFBullets.链式炮1.speed = 6
FFBullets.链式炮1.damage = 35
FFBullets.链式炮1.knockback = 0.2
FFBullets.链式炮1.bulletWidth = 8
FFBullets.链式炮1.bulletHeight = 16
FFBullets.链式炮1.shootEffect = Fx.shootBig2
FFBullets.链式炮1.smokeEffect = newEffect(25, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("7475c7"), Color.valueOf("a5b8fa"), e.fin());
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2, 45);
        }
    })

    Angles.randLenVectors(e.id, 3, 1 + e.fin() * 20, d);

});

FFBullets.链式炮1.ammoMultiplier = 3
FFBullets.链式炮1.lifetime = 40
FFBullets.链式炮1.backColor = Color.valueOf("7475c7")
FFBullets.链式炮1.frontColor = Color.valueOf("a5b8fa")
FFBullets.链式炮1.despawnEffect = newEffect(10, e => {

    Draw.color(Color.valueOf("7475c7"));
    Lines.stroke(e.fout() * 2.5 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 15);

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("7475c7"), Color.valueOf("a5b8fa"), e.fin());
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2, 45);
        }
    })

    Angles.randLenVectors(e.id, 6, 1 + e.fin() * 20, d);

});

FFBullets.链式炮1.hitEffect = newEffect(10, e => {

    Draw.color(Color.valueOf("7475c7"));
    Lines.stroke(e.fout() * 2.5 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 15);

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("7475c7"), Color.valueOf("a5b8fa"), e.fin());
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2, 45);
        }
    })

    Angles.randLenVectors(e.id, 6, 1 + e.fin() * 20, d);

});

FFBullets.链式炮2分裂.speed = 2,
FFBullets.链式炮2分裂.splashDamageRadius = 10
FFBullets.链式炮2分裂.splashDamage = 5
FFBullets.链式炮2分裂.bulletWidth = 10
FFBullets.链式炮2分裂.bulletHeight = 10
FFBullets.链式炮2分裂.bulletShrink = 1
FFBullets.链式炮2分裂.lifetime = 15
FFBullets.链式炮2分裂.backColor = Color.valueOf("d7d77f")
FFBullets.链式炮2分裂.frontColor = Color.valueOf("fef8c4")
FFBullets.链式炮2分裂.despawnEffect = Fx.none
FFBullets.链式炮2分裂.hitEffect = Fx.none

FFBullets.链式炮2.speed = 5
FFBullets.链式炮2.damage = 15
FFBullets.链式炮2.splashDamageRadius = 25
FFBullets.链式炮2.splashDamage = 30
FFBullets.链式炮2.knockback = 0.4
FFBullets.链式炮2.bulletWidth = 8
FFBullets.链式炮2.bulletHeight = 16
FFBullets.链式炮2.shootEffect = Fx.shootBig2
FFBullets.链式炮2.ammoMultiplier = 3
FFBullets.链式炮2.lifetime = 40
FFBullets.链式炮2.backColor = Color.valueOf("d7d77f")
FFBullets.链式炮2.frontColor = Color.valueOf("fef8c4")
FFBullets.链式炮2.fragBullets = 5
FFBullets.链式炮2.fragBullet = FFBullets.链式炮2分裂

FFBullets.链式炮2.smokeEffect = newEffect(25, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("d7d77f"), Color.valueOf("fef8c4"), e.fin());
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2, 45);
        }
    })

    Angles.randLenVectors(e.id, 3, 1 + e.fin() * 20, d);

});

FFBullets.链式炮2.hitEffect = newEffect(13, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("d7d77f"), Color.valueOf("fef8c4"), e.fin());
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2, 45);
        }
    })

    Angles.randLenVectors(e.id, 6, 1 + e.fin() * 25, d);

    Lines.stroke(e.fout() * 2.5 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 25);

});


FFBullets.碎晶炮1分裂.speed = 3
FFBullets.碎晶炮1分裂.splashDamageRadius = 10
FFBullets.碎晶炮1分裂.splashDamage = 2
FFBullets.碎晶炮1分裂.knockback = 0
FFBullets.碎晶炮1分裂.bulletWidth = 7
FFBullets.碎晶炮1分裂.bulletHeight = 10
FFBullets.碎晶炮1分裂.bulletShrink = 0.7
FFBullets.碎晶炮1分裂.lifetime = 15
FFBullets.碎晶炮1分裂.pierce = true
FFBullets.碎晶炮1分裂.backColor = Color.valueOf("0D47A1")
FFBullets.碎晶炮1分裂.frontColor = Color.valueOf("BBDEFB")
FFBullets.碎晶炮1分裂.despawnEffect = Fx.none
FFBullets.碎晶炮1分裂.hitEffect = Fx.none

FFBullets.碎晶炮1.speed = 5
FFBullets.碎晶炮1.damage = 40
FFBullets.碎晶炮1.knockback = 0.5
FFBullets.碎晶炮1.bulletWidth = 15
FFBullets.碎晶炮1.bulletHeight = 19
FFBullets.碎晶炮1.ammoMultiplier = 1
FFBullets.碎晶炮1.lifetime = 50
FFBullets.碎晶炮1.backColor = Color.valueOf("0D47A1")
FFBullets.碎晶炮1.frontColor = Color.valueOf("BBDEFB")
FFBullets.碎晶炮1.fragBullets = 8
FFBullets.碎晶炮1.fragBullet = FFBullets.碎晶炮1分裂
FFBullets.碎晶炮1.shootEffect = Fx.shootLiquid
FFBullets.碎晶炮1.smokeEffect = newEffect(25, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("#ffffff"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 4, 45);
        }
    })

    Angles.randLenVectors(e.id, 10, 1 + e.fin() * 25, d);

});

FFBullets.碎晶炮1.hitEffect = newEffect(13, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("BBDEFB"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 4, 45);
        }
    })

    Angles.randLenVectors(e.id, 8, 1 + e.fin() * 25, d);

    Lines.stroke(e.fout() * 3.5 + 0.2);
    Lines.circle(e.x, e.y, e.fin() * 25);

});


FFBullets.碎晶炮2.speed = 6
FFBullets.碎晶炮2.damage = 50
FFBullets.碎晶炮2.knockback = 0.5
FFBullets.碎晶炮2.bulletWidth = 16
FFBullets.碎晶炮2.bulletHeight = 24
FFBullets.碎晶炮2.reloadMultiplier = 0.8
FFBullets.碎晶炮2.ammoMultiplier = 1
FFBullets.碎晶炮2.lifetime = 70
FFBullets.碎晶炮2.pierce = true
FFBullets.碎晶炮2.backColor = Color.valueOf("424242")
FFBullets.碎晶炮2.frontColor = Color.valueOf("B0BEC5")
FFBullets.碎晶炮2.shootEffect = Fx.shootBig2
FFBullets.碎晶炮2.smokeEffect = Fx.blockExplosionSmoke

FFBullets.碎晶炮2.hitEffect = newEffect(18, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("B0BEC5"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 5 + 0.5);

        }
    })

    Draw.color(Color.valueOf("#424242"), Color.valueOf("#B0BEC580"), e.fin());
    Lines.stroke(e.fout() * 3);
    Angles.randLenVectors(e.id, 6, 1 + 70 * e.fin(), e.rotation, 25, d);

    Lines.stroke(e.fout() * 3 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 10);

});

FFBullets.碎晶炮3分裂.speed = 2.5
FFBullets.碎晶炮3分裂.splashDamageRadius = 0
FFBullets.碎晶炮3分裂.splashDamage = 0
FFBullets.碎晶炮3分裂.bulletWidth = 12
FFBullets.碎晶炮3分裂.bulletHeight = 18
FFBullets.碎晶炮3分裂.bulletShrink = 1
FFBullets.碎晶炮3分裂.lifetime = 25
FFBullets.碎晶炮3分裂.pierce = true
FFBullets.碎晶炮3分裂.backColor = Color.valueOf("AED581")
FFBullets.碎晶炮3分裂.frontColor = Color.valueOf("C5E1A5")
FFBullets.碎晶炮3分裂.despawnEffect = Fx.none
FFBullets.碎晶炮3分裂.hitEffect = Fx.none

FFBullets.碎晶炮3.speed = 4
FFBullets.碎晶炮3.damage = 100
FFBullets.碎晶炮3.splashDamageRadius = 40
FFBullets.碎晶炮3.splashDamage = 50
FFBullets.碎晶炮3.knockback = 1
FFBullets.碎晶炮3.bulletWidth = 18
FFBullets.碎晶炮3.bulletHeight = 23
FFBullets.碎晶炮3.reloadMultiplier = 0.8
FFBullets.碎晶炮3.ammoMultiplier = 1
FFBullets.碎晶炮3.lifetime = 60
FFBullets.碎晶炮3.backColor = Color.valueOf("AED581")
FFBullets.碎晶炮3.frontColor = Color.valueOf("C5E1A5")
FFBullets.碎晶炮3.fragBullets = 10
FFBullets.碎晶炮3.fragBullet = FFBullets.碎晶炮3分裂
FFBullets.碎晶炮3.shootEffect = Fx.shootBigSmoke

FFBullets.碎晶炮3.smokeEffect = newEffect(28, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("C5E1A5"), Color.valueOf("AED581"), e.fin());
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2, 45);
        }
    })

    Angles.randLenVectors(e.id, 10, 1 + e.fin() * 28, d);

});

FFBullets.碎晶炮3.hitEffect = newEffect(18, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("C5E1A5"), Color.valueOf("AED581"), e.fin());
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 4, 45);
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 5 + 0.5);

        }
    })

    Lines.stroke(e.fout() * 3.5);
    Angles.randLenVectors(e.id, 6, 1 + e.fin() * 30, d);
    Angles.randLenVectors(e.id, 6, 1 + 40 * e.fin(), e.rotation, 360, d);

    Lines.stroke(e.fout() * 3.5 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 40);

});

FFBullets.碎晶炮4分裂.speed = 4
FFBullets.碎晶炮4分裂.damage = 0
FFBullets.碎晶炮4分裂.bulletWidth = 10
FFBullets.碎晶炮4分裂.bulletHeight = 18
FFBullets.碎晶炮4分裂.bulletShrink = 1
FFBullets.碎晶炮4分裂.lifetime = 18
FFBullets.碎晶炮4分裂.pierce = true
FFBullets.碎晶炮4分裂.backColor = Color.valueOf("FF8A6500")
FFBullets.碎晶炮4分裂.frontColor = Color.valueOf("FF8A6500")
FFBullets.碎晶炮4分裂.status = FFEffects.昏睡
FFBullets.碎晶炮4分裂.statusDuration = 200
FFBullets.碎晶炮4分裂.despawnEffect = Fx.none
FFBullets.碎晶炮4分裂.hitEffect = Fx.none

FFBullets.碎晶炮4.speed = 5
FFBullets.碎晶炮4.damage = 20
FFBullets.碎晶炮4.splashDamageRadius = 60
FFBullets.碎晶炮4.splashDamage = 30
FFBullets.碎晶炮4.knockback = 1
FFBullets.碎晶炮4.bulletWidth = 16
FFBullets.碎晶炮4.bulletHeight = 24
FFBullets.碎晶炮4.reloadMultiplier = 0.5
FFBullets.碎晶炮4.ammoMultiplier = 1
FFBullets.碎晶炮4.lifetime = 70
FFBullets.碎晶炮4.backColor = Color.valueOf("FF5722")
FFBullets.碎晶炮4.frontColor = Color.valueOf("FF8A65")
FFBullets.碎晶炮4.status = FFEffects.昏睡
FFBullets.碎晶炮4.statusDuration = 200
FFBullets.碎晶炮4.fragBullets = 30
FFBullets.碎晶炮4.fragBullet = FFBullets.碎晶炮4分裂
FFBullets.碎晶炮4.shootEffect = Fx.shootBig2
FFBullets.碎晶炮4.smokeEffect = Fx.blockExplosionSmoke
FFBullets.碎晶炮4.hitEffect = newEffect(15, e => {

    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("FF8A65"));
            Fill.square(e.x + x, e.y + y, 1.5 + e.fout() * 3, 45);

        }
    })

    Angles.randLenVectors(e.id, 15, 1 + e.fin() * 50, d);

    Lines.stroke(e.fout() * 3.5 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 60);

});

//阻滞炮1分裂.speed = 4,
//阻滞炮1分裂.damage = 0,
//阻滞炮1分裂.bulletWidth = 0,
//阻滞炮1分裂.bulletHeight = 0,
//阻滞炮1分裂.bulletShrink = 1,
//阻滞炮1分裂.lifetime =18,
//阻滞炮1分裂.pierce = true,
//阻滞炮1分裂.backColor = Color.valueOf("FF8A6500"),
//阻滞炮1分裂.frontColor = Color.valueOf("FF8A6500"),
//阻滞炮1分裂.status =粘稠,
//阻滞炮1分裂.statusDuration = 450,
//阻滞炮1分裂.despawnEffect = Fx.none,
//阻滞炮1分裂.hitEffect = Fx.none

FFBullets.阻滞炮1.speed = 5.5
FFBullets.阻滞炮1.damage = 25
FFBullets.阻滞炮1.splashDamageRadius = 50
FFBullets.阻滞炮1.splashDamage = 25
FFBullets.阻滞炮1.bulletShrink = 0.1
FFBullets.阻滞炮1.knockback = 0
FFBullets.阻滞炮1.bulletSprite = "食品工厂-M3"
FFBullets.阻滞炮1.bulletWidth = 20
FFBullets.阻滞炮1.bulletHeight = 20
FFBullets.阻滞炮1.ammoMultiplier = 3
FFBullets.阻滞炮1.lifetime = 45
FFBullets.阻滞炮1.backColor = Color.valueOf("f3dbb9")
FFBullets.阻滞炮1.frontColor = Color.valueOf("ffefd8")
FFBullets.阻滞炮1.status = FFEffects.粘稠
FFBullets.阻滞炮1.statusDuration = 400
//阻滞炮1.fragBullets = 30
//阻滞炮1.fragBullet = 阻滞炮1分裂
FFBullets.阻滞炮1.shootEffect = Fx.shootBig2
FFBullets.阻滞炮1.smokeEffect = newEffect(30, e => {

    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("ffefd8"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 5, 45);

        }
    })

    Angles.randLenVectors(e.id, 15, 1 + e.fin() * 25, d);

});

FFBullets.阻滞炮1.hitEffect = newEffect(18, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("f3dbb9"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 10 + 1);

        }
    })

    Draw.color(Color.valueOf("ffefd8"), Color.valueOf("f3dbb9"), e.fin());
    Lines.stroke(e.fout() * 3);
    Angles.randLenVectors(e.id, 6, 1 + 50 * e.fin(), e.rotation, 360, d);


    Lines.stroke(e.fout() * 4 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 50);

    Fill.circle(e.x, e.y, e.fout() * 13);
    Fill.circle(e.x, e.y, e.fout() * 7);

});


FFBullets.阻滞炮2.speed = 8
FFBullets.阻滞炮2.damage = 100
FFBullets.阻滞炮2.knockback = 2
FFBullets.阻滞炮2.bulletSprite = "食品工厂-M2"
FFBullets.阻滞炮2.bulletWidth = 20
FFBullets.阻滞炮2.bulletHeight = 26
FFBullets.阻滞炮2.reloadMultiplier = 0.8
FFBullets.阻滞炮2.ammoMultiplier = 1
FFBullets.阻滞炮2.lifetime = 40
FFBullets.阻滞炮2.pierce = true
FFBullets.阻滞炮2.backColor = Color.valueOf("e2aa66")
FFBullets.阻滞炮2.frontColor = Color.valueOf("ffdeb6")
FFBullets.阻滞炮2.shootEffect = Fx.shootBig2
FFBullets.阻滞炮2.smokeEffect = newEffect(23, e => {

    const c = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("e2aa66"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 4);
        }
    })

    Angles.randLenVectors(e.id, 10, 1 + 40 * e.fin(), e.rotation, 160, c);

});

FFBullets.阻滞炮2.hitEffect = newEffect(18, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("e2aa66"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 7 + 0.5);

        }
    })

    Draw.color(Color.valueOf("e2aa66"), Color.valueOf("ffdeb6"), e.fin());
    Lines.stroke(e.fout() * 3);
    Angles.randLenVectors(e.id, 6, 1 + 70 * e.fin(), e.rotation, 25, d);

    Lines.stroke(e.fout() * 3 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 15);

    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("e2aa66"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 5);
        }
    })

    Angles.randLenVectors(e.id, 20, 1 + 30 * e.fin(), e.rotation, 360, c);

});

FFBullets.阻滞炮2.despawnEffect = hitEffect = newEffect(18, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("e2aa66"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 7 + 0.5);

        }
    })

    Draw.color(Color.valueOf("e2aa66"), Color.valueOf("ffdeb6"), e.fin());
    Lines.stroke(e.fout() * 4);
    Angles.randLenVectors(e.id, 6, 1 + 70 * e.fin(), e.rotation, 360, d);

    Lines.stroke(e.fout() * 3 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 15);

    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("e2aa66"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 5);
        }
    })

    Angles.randLenVectors(e.id, 20, 1 + 40 * e.fin(), e.rotation, 60, c);

});

FFBullets.消融炮1.speed = 4
FFBullets.消融炮1.damage = 30
FFBullets.消融炮1.bulletShrink = 0.2
FFBullets.消融炮1.knockback = 0
FFBullets.消融炮1.bulletWidth = 6
FFBullets.消融炮1.bulletHeight = 0
FFBullets.消融炮1.ammoMultiplier = 3
FFBullets.消融炮1.lifetime = 19
FFBullets.消融炮1.pierce = true
FFBullets.消融炮1.backColor = Color.valueOf("d17e47")
FFBullets.消融炮1.frontColor = Color.valueOf("fda960")
FFBullets.消融炮1.shootEffect = newEffect(40, e => {

    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("fac24d"), Color.valueOf("da4a1f"), e.fin());
            Fill.circle(e.x + x, e.y + y, e.fout() * 4);
        }
    })


    Angles.randLenVectors(e.id, 7, 1 + 110 * e.fin(), e.rotation, 15, c);

});

FFBullets.消融炮1.despawnEffect = Fx.none
FFBullets.消融炮1.smokeEffect = Fx.none
FFBullets.消融炮1.hitEffect = Fx.none

FFBullets.增殖炮1.speed = 1.6
FFBullets.增殖炮1.damage = 15
FFBullets.增殖炮1.bulletSprite = "食品工厂-M3"
FFBullets.增殖炮1.bulletWidth = 10
FFBullets.增殖炮1.bulletHeight = 10
FFBullets.增殖炮1.bulletShrink = 0.3
FFBullets.增殖炮1.ammoMultiplier = 1
FFBullets.增殖炮1.lifetime = 150
FFBullets.增殖炮1.backColor = Color.valueOf("7659cf")
FFBullets.增殖炮1.frontColor = Color.valueOf("9d78dc")
FFBullets.增殖炮1.status = FFEffects.侵蚀
FFBullets.增殖炮1.statusDuration = 100
FFBullets.增殖炮1.homingPower = 3
FFBullets.增殖炮1.homingRange = 100
FFBullets.增殖炮1.shootEffect = Fx.shootBig2
FFBullets.增殖炮1.smokeEffect = newEffect(20, e => {

    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9d78dc"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 2);
        }
    })

    Angles.randLenVectors(e.id, 5, 1 + 15 * e.fin(), e.rotation, 15, c);

});

FFBullets.增殖炮1.hitEffect = newEffect(15, e => {

    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9d78dc"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2.2, 45);

        }
    })

    Angles.randLenVectors(e.id, 10, 1 + e.fin() * 20, d);

    Lines.stroke(e.fout() * 1.5 + 0.2);
    Lines.circle(e.x, e.y, e.fin() * 13);

});

FFBullets.增殖炮1.despawnEffect = newEffect(15, e => {

    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9d78dc"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2.2, 45);

        }
    })

    Angles.randLenVectors(e.id, 10, 1 + e.fin() * 20, d);

    Lines.stroke(e.fout() * 1.5 + 0.2);
    Lines.circle(e.x, e.y, e.fin() * 13);

});

FFBullets.突触炮1.speed = 1.3
FFBullets.突触炮1.damage = 15
FFBullets.突触炮1.bulletSprite = "食品工厂-M3"
FFBullets.突触炮1.bulletWidth = 8
FFBullets.突触炮1.bulletHeight = 8
FFBullets.突触炮1.bulletShrink = 0.3
FFBullets.突触炮1.ammoMultiplier = 3
FFBullets.突触炮1.lifetime = 200
FFBullets.突触炮1.backColor = Color.valueOf("7659cf")
FFBullets.突触炮1.frontColor = Color.valueOf("9d78dc")
FFBullets.突触炮1.status = FFEffects.侵蚀
FFBullets.突触炮1.statusDuration = 150
FFBullets.突触炮1.homingPower = 1
FFBullets.突触炮1.homingRange = 120
FFBullets.突触炮1.trailColor = Color.valueOf("7659cf")
FFBullets.突触炮1.shootEffect = Fx.shootBig2
FFBullets.突触炮1.smokeEffect = newEffect(20, e => {

    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9d78dc"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 2);
        }
    })

    Angles.randLenVectors(e.id, 5, 1 + 15 * e.fin(), e.rotation, 15, c);

});

FFBullets.突触炮1.hitEffect = newEffect(15, e => {

    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9d78dc"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2.2, 45);

        }
    })

    Angles.randLenVectors(e.id, 10, 1 + e.fin() * 20, d);

    Lines.stroke(e.fout() * 1.5 + 0.2);
    Lines.circle(e.x, e.y, e.fin() * 13);

});

FFBullets.突触炮1.despawnEffect = newEffect(15, e => {

    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9d78dc"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 2.2, 45);

        }
    })

    Angles.randLenVectors(e.id, 10, 1 + e.fin() * 20, d);

    Lines.stroke(e.fout() * 1.5 + 0.2);
    Lines.circle(e.x, e.y, e.fin() * 13);

});



FFBullets.碎冰刃1.speed = 6
FFBullets.碎冰刃1.damage = 100
FFBullets.碎冰刃1.bulletSprite = "食品工厂-M4"
FFBullets.碎冰刃1.bulletWidth = 13
FFBullets.碎冰刃1.bulletHeight = 18
FFBullets.碎冰刃1.bulletShrink = 0.3
FFBullets.碎冰刃1.ammoMultiplier = 3
FFBullets.碎冰刃1.lifetime = 80
FFBullets.碎冰刃1.backColor = Color.valueOf("9ba7bf")
FFBullets.碎冰刃1.frontColor = Color.valueOf("c2d0e9")
FFBullets.碎冰刃1.status = FFEffects.切割
FFBullets.碎冰刃1.statusDuration = 150
FFBullets.碎冰刃1.trailColor = Color.valueOf("9ba7bf")
FFBullets.碎冰刃1.shootEffect = Fx.shootBig2
FFBullets.碎冰刃1.smokeEffect = newEffect(30, e => {

    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 5);
        }
    })

    Angles.randLenVectors(e.id, 5, 1 + 50 * e.fin(), e.rotation, 90, c);

});

FFBullets.碎冰刃1.hitEffect = newEffect(18, e => {

    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 3, 45);

        }
    })

    Angles.randLenVectors(e.id, 10, 1 + e.fin() * 30, d);

    Lines.stroke(e.fout() * 3 + 0.2);
    Lines.circle(e.x, e.y, e.fin() * 20);

});

FFBullets.碎冰刃1.despawnEffect = newEffect(18, e => {

    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 3, 45);

        }
    })

    Angles.randLenVectors(e.id, 10, 1 + e.fin() * 30, d);

    Lines.stroke(e.fout() * 3 + 0.2);
    Lines.circle(e.x, e.y, e.fin() * 20);

});


FFBullets.碎冰刃2.speed = 8
FFBullets.碎冰刃2.damage = 110
FFBullets.碎冰刃2.bulletSprite = "食品工厂-M4"
FFBullets.碎冰刃2.bulletWidth = 13
FFBullets.碎冰刃2.bulletHeight = 18
FFBullets.碎冰刃2.bulletShrink = 0.3
FFBullets.碎冰刃2.ammoMultiplier = 3
FFBullets.碎冰刃2.lifetime = 90
FFBullets.碎冰刃2.backColor = Color.valueOf("e8d174")
FFBullets.碎冰刃2.frontColor = Color.valueOf("f2e877")
FFBullets.碎冰刃2.pierce = true
FFBullets.碎冰刃2.status = FFEffects.爆甲
FFBullets.碎冰刃2.statusDuration = 150
FFBullets.碎冰刃2.trailColor = Color.valueOf("e8d174")
FFBullets.碎冰刃2.shootEffect = Fx.shootBig2
FFBullets.碎冰刃2.smokeEffect = newEffect(30, e => {

    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("f2e877"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 5);
        }
    })

    Angles.randLenVectors(e.id, 5, 1 + 50 * e.fin(), e.rotation, 90, c);

});

FFBullets.碎冰刃2.hitEffect = newEffect(18, e => {

    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("f2e877"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 3, 45);

        }
    })

    Angles.randLenVectors(e.id, 10, 1 + e.fin() * 30, d);

    Lines.stroke(e.fout() * 3 + 0.2);
    Lines.circle(e.x, e.y, e.fin() * 20);

});

FFBullets.碎冰刃2.despawnEffect = newEffect(18, e => {

    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("f2e877"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 3, 45);

        }
    })

    Angles.randLenVectors(e.id, 10, 1 + e.fin() * 30, d);

    Lines.stroke(e.fout() * 3 + 0.2);
    Lines.circle(e.x, e.y, e.fin() * 20);

});



FFBullets.U5碎冰刃1.speed = 12
FFBullets.U5碎冰刃1.damage = 250
FFBullets.U5碎冰刃1.splashDamageRadius = 50
FFBullets.U5碎冰刃1.splashDamage = 250
FFBullets.U5碎冰刃1.bulletSprite = "食品工厂-M4"
FFBullets.U5碎冰刃1.bulletWidth = 12
FFBullets.U5碎冰刃1.bulletHeight = 25
FFBullets.U5碎冰刃1.bulletShrink = 0.3
FFBullets.U5碎冰刃1.ammoMultiplier = 3
FFBullets.U5碎冰刃1.lifetime = 80
FFBullets.U5碎冰刃1.backColor = Color.valueOf("9ba7bf")
FFBullets.U5碎冰刃1.frontColor = Color.valueOf("c2d0e9")
FFBullets.U5碎冰刃1.pierce = true
FFBullets.U5碎冰刃1.status = FFEffects.切割
FFBullets.U5碎冰刃1.statusDuration = 150
FFBullets.U5碎冰刃1.trailColor = Color.valueOf("9ba7bf")
FFBullets.U5碎冰刃1.shootEffect = Fx.shootBig2
FFBullets.U5碎冰刃1.smokeEffect = newEffect(30, e => {

    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 5);
        }
    })

    Angles.randLenVectors(e.id, 5, 1 + 50 * e.fin(), e.rotation, 90, c);

});

FFBullets.U5碎冰刃1.hitEffect = newEffect(18, e => {

    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 3, 45);

        }
    })

    Angles.randLenVectors(e.id, 13, 1 + e.fin() * 50, d);

    Lines.stroke(e.fout() * 3 + 0.2);
    Lines.circle(e.x, e.y, e.fin() * 40);

});

FFBullets.U5碎冰刃1.despawnEffect = newEffect(18, e => {

    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 3, 45);

        }
    })

    Angles.randLenVectors(e.id, 13, 1 + e.fin() * 50, d);

    Lines.stroke(e.fout() * 3 + 0.2);
    Lines.circle(e.x, e.y, e.fin() * 40);

});


FFBullets.晶束炮1.speed = 20
FFBullets.晶束炮1.damage = 380
FFBullets.晶束炮1.knockback = 0.5
FFBullets.晶束炮1.bulletSprite = "食品工厂-M2"
FFBullets.晶束炮1.bulletWidth = 15
FFBullets.晶束炮1.bulletHeight = 55
FFBullets.晶束炮1.ammoMultiplier = 1
FFBullets.晶束炮1.lifetime = 40
FFBullets.晶束炮1.pierce = true
FFBullets.晶束炮1.backColor = Color.valueOf("9ba7bf")
FFBullets.晶束炮1.frontColor = Color.valueOf("c2d0e9")
FFBullets.晶束炮1.shootEffect = newEffect(40, e => {


    //直线粒子
    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("c2d0e9"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 13 + 0.5);
        }
    })

    //粒子渐变粗细
    Lines.stroke(e.fout() * 4);

    Angles.randLenVectors(e.id, 10, 1 + 250 * e.fin(), e.rotation, 0, d);

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 5);
        }
    })

    Angles.randLenVectors(e.id, 20, 1 + 50 * e.fin(), e.rotation, 60, c);

});

FFBullets.晶束炮1.smokeEffect = newEffect(25, e => {

    Draw.color(Color.valueOf("9ba7bf"));
    Lines.stroke(e.fout() * 2 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 60);
});

FFBullets.晶束炮1.hitEffect = newEffect(18, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("c2d0e9"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 10 + 0.5);

        }
    })

    Lines.stroke(e.fout() * 4);
    Angles.randLenVectors(e.id, 9, 1 + 70 * e.fin(), e.rotation, 360, d);

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 6);
        }
    })

    Angles.randLenVectors(e.id, 18, 1 + 40 * e.fin(), e.rotation, 220, c);

    Lines.stroke(e.fout() * 3 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 30);

});


FFBullets.晶束炮1.despawnEffect = newEffect(18, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("c2d0e9"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 10 + 0.5);

        }
    })

    Lines.stroke(e.fout() * 4);
    Angles.randLenVectors(e.id, 9, 1 + 70 * e.fin(), e.rotation, 200, d);

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 5);
        }
    })

    Angles.randLenVectors(e.id, 18, 1 + 40 * e.fin(), e.rotation, 60, c);

    Lines.stroke(e.fout() * 3 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 30);

});


FFBullets.晶束炮2.speed = 20
FFBullets.晶束炮2.damage = 600
FFBullets.晶束炮2.knockback = 0.5
FFBullets.晶束炮2.bulletSprite = "食品工厂-M2"
FFBullets.晶束炮2.bulletWidth = 15
FFBullets.晶束炮2.bulletHeight = 55
FFBullets.晶束炮2.ammoMultiplier = 1
FFBullets.晶束炮2.reloadMultiplier = 0.8
FFBullets.晶束炮2.lifetime = 40
FFBullets.晶束炮2.pierce = true
FFBullets.晶束炮2.backColor = Color.valueOf("e8d174")
FFBullets.晶束炮2.frontColor = Color.valueOf("f2e877")
FFBullets.晶束炮2.shootEffect = newEffect(40, e => {


    //直线粒子
    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("f2e877"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 13 + 0.5);
        }
    })

    //粒子渐变粗细
    Lines.stroke(e.fout() * 4);

    Angles.randLenVectors(e.id, 10, 1 + 250 * e.fin(), e.rotation, 0, d);

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("f2e877"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 5);
        }
    })

    Angles.randLenVectors(e.id, 20, 1 + 50 * e.fin(), e.rotation, 60, c);

});

FFBullets.晶束炮2.smokeEffect = newEffect(25, e => {

    Draw.color(Color.valueOf("f2e877"))
    Lines.stroke(e.fout() * 2 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 60);
});

FFBullets.晶束炮2.hitEffect = newEffect(18, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("f2e877"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 10 + 0.5);

        }
    })

    Lines.stroke(e.fout() * 4);
    Angles.randLenVectors(e.id, 9, 1 + 70 * e.fin(), e.rotation, 360, d);

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("f2e877"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 6);
        }
    })

    Angles.randLenVectors(e.id, 18, 1 + 40 * e.fin(), e.rotation, 220, c);

    Lines.stroke(e.fout() * 3 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 30);

});


FFBullets.晶束炮2.despawnEffect = newEffect(18, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("f2e877"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 10 + 0.5);

        }
    })

    Lines.stroke(e.fout() * 4);
    Angles.randLenVectors(e.id, 9, 1 + 70 * e.fin(), e.rotation, 200, d);

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("f2e877"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 5);
        }
    })

    Angles.randLenVectors(e.id, 18, 1 + 40 * e.fin(), e.rotation, 60, c);

    Lines.stroke(e.fout() * 3 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 30);

});

FFBullets.U5晶束炮1.speed = 22
FFBullets.U5晶束炮1.damage = 1000
FFBullets.U5晶束炮1.splashDamageRadius = 40
FFBullets.U5晶束炮1.splashDamage = 1000
FFBullets.U5晶束炮1.knockback = 0.5
FFBullets.U5晶束炮1.bulletSprite = "食品工厂-M2"
FFBullets.U5晶束炮1.bulletWidth = 15
FFBullets.U5晶束炮1.bulletHeight = 55
FFBullets.U5晶束炮1.ammoMultiplier = 1
FFBullets.U5晶束炮1.lifetime = 200
FFBullets.U5晶束炮1.pierce = true
FFBullets.U5晶束炮1.backColor = Color.valueOf("9ba7bf")
FFBullets.U5晶束炮1.frontColor = Color.valueOf("c2d0e9")
FFBullets.U5晶束炮1.shootEffect = newEffect(50, e => {


    //直线粒子
    const d = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("c2d0e9"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 13 + 0.5);
        }
    })

    //粒子渐变粗细
    Lines.stroke(e.fout() * 4);

    Angles.randLenVectors(e.id, 15, 1 + 400 * e.fin(), e.rotation, 0, d);

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 5);
        }
    })

    Angles.randLenVectors(e.id, 22, 1 + 80 * e.fin(), e.rotation, 65, c);

});

FFBullets.U5晶束炮1.smokeEffect = newEffect(35, e => {

    Draw.color(Color.valueOf("9ba7bf"));
    Lines.stroke(e.fout() * 2 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 60);
});

FFBullets.U5晶束炮1.hitEffect = newEffect(20, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("c2d0e9"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 12 + 2);

        }
    })

    Lines.stroke(e.fout() * 4);
    Angles.randLenVectors(e.id, 35, 1 + 100 * e.fin(), e.rotation, 360, d);

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 12);
        }
    })

    Angles.randLenVectors(e.id, 20, 1 + 50 * e.fin(), e.rotation, 270, c);

    Lines.stroke(e.fout() * 7 + 0.3);
    Lines.circle(e.x, e.y, e.fin() * 40);

});


FFBullets.U5晶束炮1.despawnEffect = newEffect(20, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("c2d0e9"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 12 + 2);

        }
    })

    Lines.stroke(e.fout() * 4);
    Angles.randLenVectors(e.id, 35, 1 + 100 * e.fin(), e.rotation, 360, d);

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 12);
        }
    })

    Angles.randLenVectors(e.id, 20, 1 + 50 * e.fin(), e.rotation, 270, c);

    Lines.stroke(e.fout() * 7 + 0.3);
    Lines.circle(e.x, e.y, e.fin() * 40);

});


FFBullets.铁幕子弹1.bulletSprite = "食品工厂-M1"
FFBullets.铁幕子弹1.speed = 7
FFBullets.铁幕子弹1.drag = 0.01
FFBullets.铁幕子弹1.homingPower = 0.5
FFBullets.铁幕子弹1.damage = 50
FFBullets.铁幕子弹1.lifetime = 50
FFBullets.铁幕子弹1.knockback = 0.5
FFBullets.铁幕子弹1.splashDamageRadius = 20
FFBullets.铁幕子弹1.splashDamage = 70
FFBullets.铁幕子弹1.bulletShrink = 0
FFBullets.铁幕子弹1.bulletWidth = 12
FFBullets.铁幕子弹1.bulletHeight = 15
FFBullets.铁幕子弹1.shootEffect = Fx.shootBig2
FFBullets.铁幕子弹1.smokeEffect = Fx.blockExplosionSmoke
FFBullets.铁幕子弹1.hitEffect = Fx.blockExplosion
FFBullets.铁幕子弹1.ammoMultiplier = 2
FFBullets.铁幕子弹1.backColor = Color.valueOf("#424242")
FFBullets.铁幕子弹1.frontColor = Color.valueOf("B0BEC5")
FFBullets.铁幕子弹1.trailColor = Color.valueOf("B0BEC5")

FFBullets.铁幕子弹2.bulletSprite = "食品工厂-M1"
FFBullets.铁幕子弹2.speed = 7
FFBullets.铁幕子弹2.drag = 0.03
FFBullets.铁幕子弹2.homingPower = 0
FFBullets.铁幕子弹2.damage = 0
FFBullets.铁幕子弹2.lifetime = 70
FFBullets.铁幕子弹2.knockback = 0.5
FFBullets.铁幕子弹2.splashDamageRadius = 40
FFBullets.铁幕子弹2.splashDamage = 80
FFBullets.铁幕子弹2.bulletShrink = 0
FFBullets.铁幕子弹2.bulletWidth = 12
FFBullets.铁幕子弹2.bulletHeight = 15
FFBullets.铁幕子弹2.shootEffect = Fx.shootBig2
FFBullets.铁幕子弹2.smokeEffect = Fx.blockExplosionSmoke
FFBullets.铁幕子弹2.hitEffect = Fx.blockExplosion
FFBullets.铁幕子弹2.ammoMultiplier = 2
FFBullets.铁幕子弹2.backColor = Color.valueOf("#424242")
FFBullets.铁幕子弹2.frontColor = Color.valueOf("B0BEC5")
FFBullets.铁幕子弹2.trailColor = Color.valueOf("B0BEC5")

FFBullets.铁幕子弹3.bulletSprite = "食品工厂-M1"
FFBullets.铁幕子弹3.speed = 3.5
FFBullets.铁幕子弹3.drag = 0.006
FFBullets.铁幕子弹3.homingPower = 0.2
FFBullets.铁幕子弹3.damage = 0
FFBullets.铁幕子弹3.lifetime = 200
FFBullets.铁幕子弹3.knockback = 0.5
FFBullets.铁幕子弹3.splashDamageRadius = 50
FFBullets.铁幕子弹3.splashDamage = 555
FFBullets.铁幕子弹3.bulletShrink = 0
FFBullets.铁幕子弹3.bulletWidth = 12
FFBullets.铁幕子弹3.bulletHeight = 15
FFBullets.铁幕子弹3.shootEffect = Fx.shootBig2
FFBullets.铁幕子弹3.smokeEffect = Fx.nuclearsmoke
FFBullets.铁幕子弹3.hitEffect = Fx.nuclearcloud
FFBullets.铁幕子弹3.reloadMultiplier = 0.8
FFBullets.铁幕子弹3.ammoMultiplier = 1
FFBullets.铁幕子弹3.homingPower = 9
FFBullets.铁幕子弹3.backColor = Color.valueOf("008300")
FFBullets.铁幕子弹3.frontColor = Color.valueOf("83b783")
FFBullets.铁幕子弹3.trailColor = Color.valueOf("83b783")


FFBullets.爆裂炮1.speed = 4
FFBullets.爆裂炮1.damage = 500
FFBullets.爆裂炮1.splashDamageRadius = 40
FFBullets.爆裂炮1.splashDamage = 250
FFBullets.爆裂炮1.bulletSprite = "食品工厂-M1"
FFBullets.爆裂炮1.bulletWidth = 15
FFBullets.爆裂炮1.bulletHeight = 25
FFBullets.爆裂炮1.bulletShrink = 0.3
FFBullets.爆裂炮1.ammoMultiplier = 1
FFBullets.爆裂炮1.lifetime = 300
FFBullets.爆裂炮1.backColor = Color.valueOf("e77831")
FFBullets.爆裂炮1.frontColor = Color.valueOf("f6be4c")
FFBullets.爆裂炮1.trailColor = Color.valueOf("ff795e")
FFBullets.爆裂炮1.statusDuration = 150
FFBullets.爆裂炮1.incendAmount = 70
FFBullets.爆裂炮1.incendSpread = 30
FFBullets.爆裂炮1.incendChance = 10
FFBullets.爆裂炮1.hitSound = Sounds.explosionbig
FFBullets.爆裂炮1.shootEffect = newEffect(50, e => {

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("f6be4c"), Color.valueOf("e77831"), e.fin());
            Fill.circle(e.x + x, e.y + y, e.fout() * 5);
        }
    })

    Angles.randLenVectors(e.id, 10, 1 + 80 * e.fin(), e.rotation, 65, c);

});


FFBullets.爆裂炮1.smokeEffect = Fx.none

FFBullets.爆裂炮1.hitEffect = newEffect(20, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("f6be4c"), Color.valueOf("e77831"), e.fin());
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 12 + 2);

        }
    })

    Lines.stroke(e.fout() * 4);
    Angles.randLenVectors(e.id, 20, 1 + 100 * e.fin(), e.rotation, 360, d);

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("f6be4c"), Color.valueOf("e77831"), e.fin());
            Fill.circle(e.x + x, e.y + y, e.fout() * 12);
        }
    })

    Angles.randLenVectors(e.id, 18, 1 + 50 * e.fin(), e.rotation, 270, c);

    Lines.stroke(e.fout() * 6 + 0.3);
    Lines.circle(e.x, e.y, e.fin() * 40);

});

FFBullets.爆裂炮1.despawnEffect = newEffect(20, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("f6be4c"), Color.valueOf("e77831"), e.fin());
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 12 + 2);

        }
    })

    Lines.stroke(e.fout() * 4);
    Angles.randLenVectors(e.id, 20, 1 + 100 * e.fin(), e.rotation, 360, d);

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("f6be4c"), Color.valueOf("e77831"), e.fin());
            Fill.circle(e.x + x, e.y + y, e.fout() * 12);
        }
    })

    Angles.randLenVectors(e.id, 18, 1 + 50 * e.fin(), e.rotation, 270, c);

    Lines.stroke(e.fout() * 6 + 0.3);
    Lines.circle(e.x, e.y, e.fin() * 40);

});

FFBullets.炎阳1.speed = 2
FFBullets.炎阳1.damage = 250
FFBullets.炎阳1.splashDamageRadius = 40
FFBullets.炎阳1.splashDamage = 250
FFBullets.炎阳1.bulletSprite = "食品工厂-M1"
FFBullets.炎阳1.bulletWidth = 15
FFBullets.炎阳1.bulletHeight = 25
FFBullets.炎阳1.bulletShrink = 0.3
FFBullets.炎阳1.ammoMultiplier = 1
FFBullets.炎阳1.lifetime = 100
FFBullets.炎阳1.backColor = Color.valueOf("c75c50")
FFBullets.炎阳1.frontColor = Color.valueOf("ff795e")
FFBullets.炎阳1.statusDuration = 150
FFBullets.炎阳1.trailColor = Color.valueOf("ff795e")
FFBullets.炎阳1.homingPower = 0

FFBullets.炎阳1.shootEffect = newEffect(50, e => {

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("ff795e"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 5);
        }
    })

    Angles.randLenVectors(e.id, 15, 1 + 70 * e.fin(), e.rotation, 65, c);

});


FFBullets.炎阳1.smokeEffect = Fx.none

FFBullets.炎阳1.hitEffect = newEffect(20, e => {

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("ff795e"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 12);
        }
    })

    Angles.randLenVectors(e.id, 8, 1 + 35 * e.fin(), e.rotation, 360, c);

});

FFBullets.炎阳1.despawnEffect = newEffect(20, e => {

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("ff795e"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 12);
        }
    })

    Angles.randLenVectors(e.id, 8, 1 + 35 * e.fin(), e.rotation, 360, c);

});



FFBullets.炎阳2.speed = 1.5
FFBullets.炎阳2.damage = 100
FFBullets.炎阳2.splashDamageRadius = 40
FFBullets.炎阳2.splashDamage = 50
FFBullets.炎阳2.bulletSprite = "食品工厂-M1"
FFBullets.炎阳2.bulletWidth = 13
FFBullets.炎阳2.bulletHeight = 23
FFBullets.炎阳2.bulletShrink = 0.3
FFBullets.炎阳2.ammoMultiplier = 1
FFBullets.炎阳2.lifetime = 100
FFBullets.炎阳2.backColor = Color.valueOf("c75c50")
FFBullets.炎阳2.frontColor = Color.valueOf("ff795e")
FFBullets.炎阳2.statusDuration = 150
FFBullets.炎阳2.trailColor = Color.valueOf("ff795e")
FFBullets.炎阳2.homingPower = 0

FFBullets.炎阳2.shootEffect = Fx.none
FFBullets.炎阳2.smokeEffect = Fx.none

FFBullets.炎阳2.hitEffect = newEffect(20, e => {

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("ff795e"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 12);
        }
    })

    Angles.randLenVectors(e.id, 6, 1 + 30 * e.fin(), e.rotation, 360, c);

});

FFBullets.炎阳2.despawnEffect = newEffect(20, e => {

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("ff795e"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 12);
        }
    })

    Angles.randLenVectors(e.id, 6, 1 + 30 * e.fin(), e.rotation, 360, c);

});

FFBullets.炎阳3.speed = 1.5
FFBullets.炎阳3.damage = 250
FFBullets.炎阳3.splashDamageRadius = 40
FFBullets.炎阳3.splashDamage = 250
FFBullets.炎阳3.bulletSprite = "食品工厂-M1"
FFBullets.炎阳3.bulletWidth = 10
FFBullets.炎阳3.bulletHeight = 20
FFBullets.炎阳3.bulletShrink = 0.3
FFBullets.炎阳3.ammoMultiplier = 1
FFBullets.炎阳3.lifetime = 100
FFBullets.炎阳3.backColor = Color.valueOf("c75c50")
FFBullets.炎阳3.frontColor = Color.valueOf("ff795e")
FFBullets.炎阳3.statusDuration = 150
FFBullets.炎阳3.trailColor = Color.valueOf("ff795e")
FFBullets.炎阳3.homingPower = 0
FFBullets.炎阳3.hitSound = Sounds.boom
FFBullets.炎阳3.shootEffect = Fx.none
FFBullets.炎阳3.smokeEffect = Fx.none

FFBullets.炎阳3.hitEffect = newEffect(20, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("ff795e"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 12 + 2);

        }
    })

    Lines.stroke(e.fout() * 4);
    Angles.randLenVectors(e.id, 15, 1 + 45 * e.fin(), e.rotation, 360, d);

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("ff795e"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 9);
        }
    })

    Angles.randLenVectors(e.id, 6, 1 + 35 * e.fin(), e.rotation, 360, c);

    Lines.stroke(e.fout() * 6 + 0.3);
    Lines.circle(e.x, e.y, e.fin() * 40);

});

FFBullets.炎阳3.despawnEffect = newEffect(20, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("ff795e"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 12 + 2);

        }
    })

    Lines.stroke(e.fout() * 4);
    Angles.randLenVectors(e.id, 15, 1 + 45 * e.fin(), e.rotation, 360, d);

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("ff795e"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 9);
        }
    })

    Angles.randLenVectors(e.id, 6, 1 + 35 * e.fin(), e.rotation, 360, c);

    Lines.stroke(e.fout() * 6 + 0.3);
    Lines.circle(e.x, e.y, e.fin() * 40);

});


FFBullets.U5妖火1.speed = 2
FFBullets.U5妖火1.damage = 250
FFBullets.U5妖火1.splashDamageRadius = 40
FFBullets.U5妖火1.splashDamage = 250
FFBullets.U5妖火1.bulletSprite = "食品工厂-M1"
FFBullets.U5妖火1.bulletWidth = 15
FFBullets.U5妖火1.bulletHeight = 25
FFBullets.U5妖火1.bulletShrink = 0.3
FFBullets.U5妖火1.ammoMultiplier = 1
FFBullets.U5妖火1.lifetime = 200
FFBullets.U5妖火1.backColor = Color.valueOf("9ba7bf")
FFBullets.U5妖火1.frontColor = Color.valueOf("c2d0e9")
FFBullets.U5妖火1.statusDuration = 150
FFBullets.U5妖火1.trailColor = Color.valueOf("9ba7bf")
FFBullets.U5妖火1.homingPower = 0.5
FFBullets.U5妖火1.hitSound = Sounds.boom
FFBullets.U5妖火1.shootEffect = newEffect(60, e => {

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 5.5);
        }
    })

    Angles.randLenVectors(e.id, 20, 1 + 80 * e.fin(), e.rotation, 75, c);

});


FFBullets.U5妖火1.smokeEffect = Fx.none

FFBullets.U5妖火1.hitEffect = newEffect(20, e => {

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 12);
        }
    })

    Angles.randLenVectors(e.id, 8, 1 + 35 * e.fin(), e.rotation, 360, c);

});

FFBullets.U5妖火1.despawnEffect = newEffect(20, e => {

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 12);
        }
    })

    Angles.randLenVectors(e.id, 8, 1 + 35 * e.fin(), e.rotation, 360, c);

});

FFBullets.U5妖火2.speed = 1.5
FFBullets.U5妖火2.damage = 100
FFBullets.U5妖火2.splashDamageRadius = 40
FFBullets.U5妖火2.splashDamage = 50
FFBullets.U5妖火2.bulletSprite = "食品工厂-M1"
FFBullets.U5妖火2.bulletWidth = 13
FFBullets.U5妖火2.bulletHeight = 23
FFBullets.U5妖火2.bulletShrink = 0.3
FFBullets.U5妖火2.ammoMultiplier = 1
FFBullets.U5妖火2.lifetime = 300
FFBullets.U5妖火2.backColor = Color.valueOf("9ba7bf")
FFBullets.U5妖火2.frontColor = Color.valueOf("c2d0e9")
FFBullets.U5妖火2.statusDuration = 150
FFBullets.U5妖火2.trailColor = Color.valueOf("9ba7bf")
FFBullets.U5妖火2.homingPower = 0.05
FFBullets.U5妖火2.hitSound = Sounds.boom
FFBullets.U5妖火2.shootEffect = Fx.none
FFBullets.U5妖火2.smokeEffect = Fx.none

FFBullets.U5妖火2.hitEffect = newEffect(20, e => {

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 12);
        }
    })

    Angles.randLenVectors(e.id, 6, 1 + 30 * e.fin(), e.rotation, 360, c);

});

FFBullets.U5妖火2.despawnEffect = newEffect(20, e => {

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 12);
        }
    })

    Angles.randLenVectors(e.id, 6, 1 + 30 * e.fin(), e.rotation, 360, c);

});

FFBullets.U5妖火3.speed = 1.5
FFBullets.U5妖火3.damage = 1000
FFBullets.U5妖火3.splashDamageRadius = 40
FFBullets.U5妖火3.splashDamage = 1000
FFBullets.U5妖火3.bulletSprite = "食品工厂-M1"
FFBullets.U5妖火3.bulletWidth = 10
FFBullets.U5妖火3.bulletHeight = 20
FFBullets.U5妖火3.bulletShrink = 0.3
FFBullets.U5妖火3.ammoMultiplier = 1
FFBullets.U5妖火3.lifetime = 200
FFBullets.U5妖火3.backColor = Color.valueOf("9ba7bf")
FFBullets.U5妖火3.frontColor = Color.valueOf("c2d0e9")
FFBullets.U5妖火3.statusDuration = 150
FFBullets.U5妖火3.trailColor = Color.valueOf("9ba7bf")
FFBullets.U5妖火3.incendAmount = 70
FFBullets.U5妖火3.incendSpread = 30
FFBullets.U5妖火3.incendChance = 10
FFBullets.U5妖火3.homingPower = 0
FFBullets.U5妖火3.hitSound = Sounds.explosionbig
FFBullets.U5妖火3.collidesTeam = true
FFBullets.U5妖火3.shootEffect = Fx.none
FFBullets.U5妖火3.smokeEffect = Fx.none

FFBullets.U5妖火3.hitEffect = newEffect(20, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("9ba7bf"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 12 + 2);

        }
    })

    Lines.stroke(e.fout() * 4);
    Angles.randLenVectors(e.id, 15, 1 + 45 * e.fin(), e.rotation, 360, d);

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 9);
        }
    })

    Angles.randLenVectors(e.id, 6, 1 + 35 * e.fin(), e.rotation, 360, c);

    Lines.stroke(e.fout() * 6 + 0.3);
    Lines.circle(e.x, e.y, e.fin() * 40);

});

FFBullets.U5妖火3.despawnEffect = newEffect(20, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("9ba7bf"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 12 + 2);

        }
    })

    Lines.stroke(e.fout() * 4);
    Angles.randLenVectors(e.id, 15, 1 + 45 * e.fin(), e.rotation, 360, d);

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 9);
        }
    })

    Angles.randLenVectors(e.id, 6, 1 + 35 * e.fin(), e.rotation, 360, c);

    Lines.stroke(e.fout() * 6 + 0.3);
    Lines.circle(e.x, e.y, e.fin() * 40);

});

//融合炮




FFBullets.融合炮1.hitEffect = newEffect(20, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("a7d7fd"), Color.valueOf("a7d7fd"), e.fin());
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 8 + 0.7);
        }
    })

    Lines.stroke(e.fout() * 4);
    Angles.randLenVectors(e.id, 6, 1 + 30 * e.fin(), e.rotation, 360, d);
    Lines.stroke(e.fout() * 1.5 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 10);

});

FFBullets.融合炮1.shootEffect = newEffect(23, e => {
    Draw.color(Color.valueOf("a7d7fd"), Color.valueOf("ffffff"), e.fin());

    Lines.stroke(e.fout() * 6);
    const d = new Floatc2({
        get(x, y) {
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 9 + 0.9);

        }
    })


    Lines.stroke(e.fout() * 4);
    Angles.randLenVectors(e.id, 12, 1 + 70 * e.fin(), e.rotation, 15, d);

    Lines.stroke(e.fout() * 2 + 0.05);
    Lines.circle(e.x, e.y, e.fin() * 15);

});


FFBullets.融合炮1.smokeEffect = newEffect(35, e => {

    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("a7d7fd"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 5);
        }
    })

    Angles.randLenVectors(e.id, 20, 1 + 50 * e.fin(), e.rotation, 90, c);

});

FFBullets.融合炮1.damage = 600
FFBullets.融合炮1.speed = 0.001
FFBullets.融合炮1.despawnEffect = Fx.none
FFBullets.融合炮1.hitSize = 4
FFBullets.融合炮1.lifetime = 23
FFBullets.融合炮1.pierce = true


//融合炮2


FFBullets.融合炮2.hitEffect = Fx.lancerLaserShoot,

FFBullets.融合炮2.shootEffect = newEffect(23, e => {
        Draw.color(Color.valueOf("a7d7fd"), Color.valueOf("ffffff"), e.fin());

        Lines.stroke(e.fout() * 6);
        const d = new Floatc2({
            get(x, y) {
                Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 9 + 0.2);

            }
        })


        Lines.stroke(e.fout() * 4);
        Angles.randLenVectors(e.id, 12, 1 + 70 * e.fin(), e.rotation, 15, d);

        Lines.stroke(e.fout() * 2 + 0.05);
        Lines.circle(e.x, e.y, e.fin() * 15);

});


FFBullets.融合炮2.smokeEffect = newEffect(35, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("a7d7fd"));
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 3, 60);
        }
    })

    Angles.randLenVectors(e.id, 15, 10 + e.fin() * 20, d);


});

FFBullets.融合炮2.damage = 999;
FFBullets.融合炮2.speed = 0.001;
FFBullets.融合炮2.reloadMultiplier = 0.8
FFBullets.融合炮2.despawnEffect = Fx.none
FFBullets.融合炮2.hitSize = 4;
FFBullets.融合炮2.lifetime = 23;
FFBullets.融合炮2.pierce = true

FFBullets.焦点激光.damage = 80;
FFBullets.焦点激光.despawnEffect = Fx.none;
FFBullets.焦点激光.hitSize = 4;
FFBullets.焦点激光.drawSize = 420;
FFBullets.焦点激光.lifetime = 16;
FFBullets.焦点激光.pierce = true;
FFBullets.焦点激光.hitEffect = newEffect(25, e => {
    Draw.color(Color.valueOf("eeeeff"), Color.valueOf("eeeeff"), e.fin());

    Lines.stroke(e.fout() * 3);
    const d = new Floatc2({
        get(x, y) {
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 12 + 1.5);
        }
    })
    Angles.randLenVectors(e.id, 8, 1 + 50 * e.fin(), e.rotation, 200, d);

    Lines.stroke(e.fout() * 3 + 0.5);
    Lines.circle(e.x, e.y, e.fin() * 20);

    Fill.circle(e.x, e.y, e.fout() * 9);
    Fill.circle(e.x, e.y, e.fout() * 6);

});
FFBullets.光棱.damage = 800;
FFBullets.光棱.despawnEffect = Fx.none;
FFBullets.光棱.hitSize = 4;
FFBullets.光棱.drawSize = 420;
FFBullets.光棱.lifetime = 16;
FFBullets.光棱.pierce = true;
FFBullets.光棱.hitEffect = newEffect(25, e => {
    Draw.color(Color.valueOf("eeeeff"), Color.valueOf("eeeeff"), e.fin());

    Lines.stroke(e.fout() * 3);
    const d = new Floatc2({
        get(x, y) {
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 12 + 1.5);
        }
    })
    Angles.randLenVectors(e.id, 8, 1 + 50 * e.fin(), e.rotation, 200, d);

    Lines.stroke(e.fout() * 3 + 0.5);
    Lines.circle(e.x, e.y, e.fin() * 20);

    Fill.circle(e.x, e.y, e.fout() * 9);
    Fill.circle(e.x, e.y, e.fout() * 6);

});

FFBullets.球形闪电.bulletSprite = "食品工厂-M5"
FFBullets.球形闪电.bulletWidth = 50
FFBullets.球形闪电.bulletHeight = 50
FFBullets.球形闪电.frontColor = Pal.lancerLaser
FFBullets.球形闪电.backColor = Pal.lancerLaser
FFBullets.球形闪电.lifetime = 400
FFBullets.球形闪电.despawnEffect = newEffect(50, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("9ba7bf"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 10 + 0.5);

        }
    })

    Lines.stroke(e.fout() * 5);
    Angles.randLenVectors(e.id, 15, 1 + 130 * e.fin(), e.rotation, 360, d);

    //圆圈烟雾
    const c = new Floatc2({
        get(x, y) {

            Draw.color(Color.valueOf("9ba7bf"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 7);
        }
    })

    Angles.randLenVectors(e.id, 18, 1 + 50 * e.fin(), e.rotation, 360, c);

    Lines.stroke(e.fout() * 3 + 0.1);
    Lines.circle(e.x, e.y, e.fin() * 30);

    Draw.color(Color.valueOf("9ba7bf"));
    Fill.circle(e.x, e.y, e.fout() * 15);
    Draw.color();
    Fill.circle(e.x, e.y, e.fout() * 10);
});
FFBullets.糖浆子弹.liquid = 糖浆
FFBullets.可乐子弹.liquid = 可乐
FFBullets.冰镇可乐子弹.liquid = 冰镇可乐
this.global.FFBullets = FFBullets;

