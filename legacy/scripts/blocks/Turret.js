
//塔
const FFBullets = this.global.FFBullets
const 光棱 = extendContent(LaserTurret, "光棱", {})

const 彩虹重炮 = extendContent(DoubleTurret, "彩虹重炮", {
    shoot(tile,ammo){
        const entity = tile.ent();
        entity.shots++;

        const i = Mathf.signs[entity.shots % 2];
        this.tr.trns(entity.rotation - 90, this.shotWidth * i, this.size * this.tilesize / 2);
        Bullet.create(rb(Math.floor(Math.random() * (7 - 0)) + 0), tile.entity, tile.getTeam(), tile.drawx(), tile.drawy(),entity.rotation + Mathf.range(this.inaccuracy));
        this.effects(tile);
        this.useAmmo(tile);

    }
});
彩虹重炮.ammo(彩虹糖果,FFBullets.chzpb);
彩虹重炮.maxAmmo = 20;

const 海啸 = extendContent(LiquidTurret,"海啸",{})
海啸.ammo(
    Liquids.water, Bullets.waterShot,
    Liquids.slag, Bullets.slagShot,
    Liquids.cryofluid, Bullets.cryoShot,
    Liquids.oil, Bullets.oilShot,
    糖浆,FFBullets.糖浆子弹,
    可乐,FFBullets.可乐子弹,
    冰镇可乐, FFBullets.冰镇可乐子弹
)

const 余晖 = extendContent(ArtilleryTurret, "余晖", {})
余晖.ammo(Items.copper, FFBullets.余晖1)

const 铝罐炮 = extendContent(ArtilleryTurret, "铝罐炮", {})
铝罐炮.ammo(铝罐, FFBullets.铝罐炮1, 罐装可乐, FFBullets.铝罐炮2, 摇过的可乐, FFBullets.铝罐炮3, 核子可乐, FFBullets.铝罐炮4)

const 硬糖炮 = extendContent(ArtilleryTurret, "硬糖炮", {})
硬糖炮.ammo(硬糖, FFBullets.硬糖炮1)

const 碎糖炮 = extendContent(BurstTurret, "碎糖炮", {})
碎糖炮.ammo(硬糖, FFBullets.碎糖炮1)

const 链式炮 = extendContent(BurstTurret, "链式炮", {})
链式炮.ammo(Items.titanium, FFBullets.链式炮1, Items.plastanium, FFBullets.链式炮2)

const 阻滞炮 = extendContent(BurstTurret, "阻滞炮", {})
阻滞炮.ammo(面团, FFBullets.阻滞炮1, 面包, FFBullets.阻滞炮2)

const 碎晶炮 = extendContent(BurstTurret, "碎晶炮", {})
碎晶炮.ammo(冰块, FFBullets.碎晶炮1, 可乐冰块, FFBullets.碎晶炮2, 绿茶冰块, FFBullets.碎晶炮3, 红茶冰块, FFBullets.碎晶炮4)

const 消融炮 = extendContent(BurstTurret, "消融炮", {})
消融炮.ammo(Items.pyratite, FFBullets.消融炮1)

const 融合炮 = extendContent(BurstTurret, "融合炮", {})
融合炮.ammo(Items.graphite, FFBullets.融合炮1, 晶状合金, FFBullets.融合炮2)

const 增殖炮 = extendContent(BurstTurret, "增殖炮", {})
增殖炮.ammo(Items.sporePod, FFBullets.增殖炮1)

const 突触炮 = extendContent(BurstTurret, "突触炮", {})
突触炮.ammo(Items.sporePod, FFBullets.突触炮1)

const 碎冰刃 = extendContent(BurstTurret, "碎冰刃", {})
碎冰刃.ammo(晶状合金, FFBullets.碎冰刃1, Items.surgealloy, FFBullets.碎冰刃2)

const U5碎冰刃 = extendContent(BurstTurret, "U5碎冰刃", {})
U5碎冰刃.ammo(晶状合金, FFBullets.U5碎冰刃1)
U5碎冰刃.requirements(Category.power, BuildVisibility.sandboxOnly, ItemStack.with());

const 晶束炮 = extendContent(BurstTurret, "晶束炮", {})
晶束炮.ammo(晶状合金, FFBullets.晶束炮1, Items.surgealloy, FFBullets.晶束炮2)

const U5晶束炮 = extendContent(BurstTurret, "U5晶束炮", {})
U5晶束炮.ammo(晶状合金, FFBullets.U5晶束炮1)
U5晶束炮.requirements(Category.power, BuildVisibility.sandboxOnly, ItemStack.with());

const 爆裂炮 = extendContent(BurstTurret, "爆裂炮", {})
爆裂炮.ammo(Items.pyratite, FFBullets.爆裂炮1)

const 炎阳 = extendContent(BurstTurret, "炎阳", {})
炎阳.ammo(Items.blastCompound, FFBullets.炎阳1)

const U5妖火 = extendContent(BurstTurret, "U5妖火", {})
U5妖火.ammo(Items.pyratite, FFBullets.U5妖火1)
U5妖火.requirements(Category.power, BuildVisibility.sandboxOnly, ItemStack.with());

const 铁幕 = extendContent(BurstTurret, "铁幕", {})
铁幕.ammo(罐装可乐, FFBullets.铁幕子弹1, 摇过的可乐, FFBullets.铁幕子弹2, 核子可乐, FFBullets.铁幕子弹3)

晶束炮.ammoUseEffect = newEffect(45, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("9ba7bf"), Color.valueOf("c2d0e9"), e.fin());
            Fill.square(e.x + x, e.y + y, 0.1 + e.fout() * 3.5, 45);
        }
    })
    Angles.randLenVectors(e.id, 6, 1 + e.fin() * 50, d);

});

U5晶束炮.ammoUseEffect = newEffect(100, e => {
    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("9ba7bf"), Color.valueOf("c2d0e9"), e.fin());
            Fill.square(e.x + x, e.y + y, 0.1 + e.fout() * 2, 45);
        }
    })
    Angles.randLenVectors(e.id, 10, 1 + e.fin() * 80, d);
});

炎阳.ammoUseEffect = newEffect(130, e => {
    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("ff795e"), Color.valueOf("ff795e"), e.fin());
            Fill.square(e.x + x, e.y + y, 0.1 + e.fout() * 2, 45);
        }
    })
    Angles.randLenVectors(e.id, 10, 1 + e.fin() * 80, d);
});

U5妖火.ammoUseEffect = newEffect(130, e => {
    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("9ba7bf"), Color.valueOf("c2d0e9"), e.fin());
            Fill.square(e.x + x, e.y + y, 0.1 + e.fout() * 2, 45);
        }
    })
    Angles.randLenVectors(e.id, 8, 1 + e.fin() * 80, d);
});
const 魂链 = extendContent(ChargeTurret, "魂链", {
})
魂链.shootType = FFBullets.球形闪电

const U5焦点激光 = extendContent(LaserTurret, "U5焦点激光", {
    turnToTarget(tile, targetRot) {
        const entity = tile.ent();
        entity.rotation = Mathf.slerpDelta(entity.rotation, entity.angleTo(entity.target), 0.5);
    }
})
U5焦点激光.requirements(Category.power, BuildVisibility.sandboxOnly, ItemStack.with());
U5焦点激光.shootEffect = Fx.shootBigSmoke2;
U5焦点激光.shootCone = 40;
U5焦点激光.recoil = 4;
U5焦点激光.size = 4;
U5焦点激光.shootShake = 2
U5焦点激光.range = 190
U5焦点激光.reload = 8
U5焦点激光.firingMoveFract = 99
U5焦点激光.shootDuration = 400
U5焦点激光.powerUse = 1
U5焦点激光.shootSound = Sounds.laserbig;
U5焦点激光.activeSound = Sounds.beam;
U5焦点激光.activeSoundVolume = 2
U5焦点激光.shootType = FFBullets.焦点激光;

光棱.requirements(Category.power, BuildVisibility.sandboxOnly, ItemStack.with());
光棱.shootEffect = Fx.shootBigSmoke2;
光棱.shootCone = 40;
光棱.recoil = 4;
光棱.size = 4;
光棱.shootShake = 2
光棱.range = 230
光棱.reload = 8
光棱.shootDuration = 500
光棱.powerUse = 45
光棱.shootSound = Sounds.laserbig;
光棱.activeSound = Sounds.beam;
光棱.activeSoundVolume = 2
光棱.shootType = FFBullets.光棱;

function rb(a) {
    switch (a) {
        case 0:
            return FFBullets.chzpb
        case 1:
            return FFBullets.chzpb1
        case 2:
            return FFBullets.chzpb2
        case 3:
            return FFBullets.chzpb3
        case 4:
            return FFBullets.chzpb4
        case 5:
            return FFBullets.chzpb5
        case 6:
            return FFBullets.chzpb6
    }
}
//==============================================================
//----阳极姬射器的子弹
// var a = global.硬糖
// print(a)
const 阳colors = [Color.valueOf("ec745855"), Color.valueOf("ff9c5a"), Color.white];
const 阳tscales = [1, 0.7, 0.5, 0.2];
const 阳lenscales = [1, 1.1, 1.13, 1.14];
const 阳length = 310;

const 阳极激光 = extend(BasicBulletType, {
    range() {
        return 阳length;
    },
    // init(b){
    // },
    update(b) {
        if (b.time() < 0.001) {
            Damage.collideLine(b, b.getTeam(), this.hitEffect, b.x, b.y, b.rot(), 阳length);
        }
    },
    draw(b) {
        const f = Mathf.curve(b.fin(), 0, 0.2);
        const baseLen = 阳length * f;

        Lines.lineAngle(b.x, b.y, b.rot(), baseLen);
        for (var s = 0; s < 3; s++) {
            Draw.color(阳colors[s]);
            for (var i = 0; i < 阳tscales.length; i++) {
                Lines.stroke(7 * 2 * b.fout() * (s == 0 ? 1.5 : s == 1 ? 1 : 0.3) * 阳tscales[i]);
                Lines.lineAngle(b.x, b.y, b.rot(), baseLen * 阳lenscales[i]);
            }
        }
        Draw.reset();
    }
})
阳极激光.hitEffect = newEffect(20, e => {
    Draw.color(Color.valueOf("ffa264"), Color.valueOf("ffa264"), e.fin());

    Lines.stroke(e.fout() * 4);
    const d = new Floatc2({
        get(x, y) {
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 12 + 1.5);
        }
    })
    Angles.randLenVectors(e.id, 8, 1 + 80 * e.fin(), e.rotation, 50, d);

    Lines.stroke(e.fout() * 3 + 0.5);
    Lines.circle(e.x, e.y, e.fin() * 30);

    Fill.circle(e.x, e.y, e.fout() * 20);
    Fill.circle(e.x, e.y, e.fout() * 8);

});
阳极激光.shootEffect = newEffect(30, e => {
    Draw.color(Color.valueOf("ffa264"), Color.valueOf("ffa264"), e.fin());

    Fill.circle(e.x, e.y, e.fout() * 15);
    Draw.color();
    Fill.circle(e.x, e.y, e.fout() * 10);

});
阳极激光.smokeEffect = newEffect(55, e => {

    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("ffa264"), Color.valueOf("ffa264"), e.fin());
            Fill.square(e.x + x, e.y + y, 0.2 + e.fout() * 3, 60);
        }
    })

    Angles.randLenVectors(e.id, 15, 10 + e.fin() * 20, d);


});

阳极激光.damage = 800;
阳极激光.speed = 0.001;
阳极激光.despawnEffect = Fx.none;
阳极激光.hitSize = 4;
阳极激光.lifetime = 30;
阳极激光.pierce = true

const 阳极激射器 = extendContent(ChargeTurret, "阳极激射器", {
})
阳极激射器.shootType = 阳极激光
阳极激射器.chargeBeginEffect = newEffect(45, e => {
    //蓄力效果                   颜色
    Draw.color(Color.valueOf("ffa264"));
    Fill.circle(e.x, e.y, e.fin() * 15);
    Draw.color();
    Fill.circle(e.x, e.y, e.fin() * 10);

    Draw.color(Color.valueOf("ffa264"));
    Lines.stroke(e.fin() * 5 + 0.01);
    Lines.circle(e.x, e.y, e.fout() * 60);

})
阳极激射器.chargeEffect = newEffect(40, e => {
    Draw.color(Color.valueOf("ffa264"), Color.valueOf("ffa264"), e.fin());

    //用于用于控制所有效果粗细
    Lines.stroke(e.fin() * 2.3);

    const d = new Floatc2({
        get(x, y) {
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 10 + 1);
        }
    })
    Angles.randLenVectors(e.id, 13, 1 + 120 * e.fout(), e.rotation, 110, d);

});

//==============================================================
//----阴极姬射器的子弹
const 阴tmpColor = Color();
//阴级激光的颜色    最外层(RGB最后两个是透明度)          中间层                    中间层             中心
const 阴colors = [Color.valueOf("676bd455"), Color.valueOf("a3a5d4"), Color.valueOf("eeeeff"), Color.valueOf("ffffff")];
const 阴tscales = [1, 0.7, 0.5, 0.2];
const 阴strokes = [2, 1, 0.3];
const 阴lenscales = [1, 1.12, 1.15, 1.17];
const 阴length = 290;
//tscales.all * width
const 阴极激光 = extend(BasicBulletType, {
    update(b) {
        if (b.timer.get(1, 9)) {
            Damage.collideLine(b, b.getTeam(), this.hitEffect, b.x, b.y, b.rot(), 阴length, true);
        }
        Effects.shake(1, 1, b.x, b.y);
    },
    hit(b, hitx, hity) {
        Effects.effect(this.hitEffect, 阴colors[2], hitx, hity);
        if (Mathf.chance(0.4)) {
            Fire.create(Vars.world.tileWorld(hitx + Mathf.range(5), hity + Mathf.range(5)));
        }
    },
    draw(b) {
        const baseLen = (阴length) * b.fout();

        Lines.lineAngle(b.x, b.y, b.rot(), baseLen);
        for (var s = 0; s < 阴colors.length; s++) {
            Draw.color(阴tmpColor.set(阴colors[s]).mul(1 + Mathf.absin(Time.time(), 1, 0.1)));
            for (var i = 0; i < 阴tscales.length; i++) {
                //Tmp.v1.trns(b.rot() + 180, (lenscales[i] - 1) * 35);
                Lines.stroke((9 + Mathf.absin(Time.time(), 0.8, 1.5)) * b.fout() * 阴strokes[s] * 阴tscales[i]);
                Lines.lineAngle(b.x, b.y, b.rot(), baseLen * 阴lenscales[i]);
            }
        }
        Draw.reset();
    }
})
阴极激光.damage = 300;
阴极激光.hitSize = 4;
阴极激光.drawSize = 420;
阴极激光.lifetime = 16;
阴极激光.pierce = true;

阴极激光.despawnEffect = Fx.none;
阴极激光.hitEffect = newEffect(25, e => {
    Draw.color(Color.valueOf("eeeeff"), Color.valueOf("a3a5d4"), e.fin());

    Lines.stroke(e.fout() * 3);
    const d = new Floatc2({
        get(x, y) {
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 12 + 1.5);
        }
    })
    Angles.randLenVectors(e.id, 8, 1 + 80 * e.fin(), e.rotation, 200, d);

    Lines.stroke(e.fout() * 3 + 0.5);
    Lines.circle(e.x, e.y, e.fin() * 40);

    Fill.circle(e.x, e.y, e.fout() * 12);
    Fill.circle(e.x, e.y, e.fout() * 8);

});

const 阴极激射器 = extendContent(LaserTurret, "阴极激射器", {

})

阴极激射器.shootCone = 40;
阴极激射器.recoil = 4;
阴极激射器.size = 4;
阴极激射器.shootShake = 2
阴极激射器.range = 210
阴极激射器.reload = 8
阴极激射器.firingMoveFract = 0.06
阴极激射器.shootDuration = 350
阴极激射器.powerUse = 1
阴极激射器.shootSound = Sounds.laserbig;
阴极激射器.activeSound = Sounds.beam;
阴极激射器.activeSoundVolume = 2
阴极激射器.shootType = 阴极激光

阴极激射器.shootEffect = Fx.shootBigSmoke2;