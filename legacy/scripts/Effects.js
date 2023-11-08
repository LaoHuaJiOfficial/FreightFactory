const FFEffects = {
    昏睡 : extendContent(StatusEffect, "昏睡", {}),
    粘稠 : extendContent(StatusEffect, "粘稠", {}),
    侵蚀 : extendContent(StatusEffect, "侵蚀", {}),
    爆甲 : extendContent(StatusEffect, "爆甲", {}),
    切割 : extendContent(StatusEffect, "切割", {}),
    灼烧 : extendContent(StatusEffect, "灼烧", {}),
    冰冻 : extendContent(StatusEffect, "冰冻", {}),
    尾迹 : newEffect(30, e => {
        const d = new Floatc2({
            get(x, y) {
                Draw.color(Color.valueOf("c2d0e9"), Color.valueOf("9ba7bf90"), e.fin());
                Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 12 + 5);
            }
        })
        Lines.stroke(e.fout() * 3);
        Angles.randLenVectors(e.id, 1, 1 + 0 * e.fin(), e.rotation, 0, d);
    }),
    尾迹2 : newEffect(25, e => {
        const d = new Floatc2({
            get(x, y) {
                Draw.color(Color.valueOf("c2d0e9"));
                Fill.square(e.x + x, e.y + y, 0.1 + e.fout() * 1.5, 45);
            }
        })
        Angles.randLenVectors(e.id, 3, 1 + e.fin() * 30, d);
    }),
    尾迹3 : newEffect(25, e => {
        const d = new Floatc2({
            get(x, y) {
                Draw.color(Color.valueOf("f2e877"));
                Fill.square(e.x + x, e.y + y, 0.1 + e.fout() * 1.5, 45);
            }
        })
        Angles.randLenVectors(e.id, 3, 1 + e.fin() * 30, d);
    }),
    尾迹4 : newEffect(30, e => {
        const d = new Floatc2({
            get(x, y) {
                Draw.color(Color.valueOf("f2e877"), Color.valueOf("e8d17490"), e.fin());
                Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 12 + 5);
            }
        })
        Lines.stroke(e.fout() * 5);
        Angles.randLenVectors(e.id, 1, 1 + 0 * e.fin(), e.rotation, 0, d);
    }),
    尾迹5 : newEffect(30, e => {
        const d = new Floatc2({
            get(x, y) {
                Draw.color(Color.valueOf("f6be4c"), Color.valueOf("e77831"), e.fin());
                Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 12 + 5);
            }
        })
        Lines.stroke(e.fout() * 5);
        Angles.randLenVectors(e.id, 1, 1 + 0 * e.fin(), e.rotation, 0, d);
    })
}

FFEffects.昏睡.damage = 0;
FFEffects.昏睡.damageMultiplier = 0.8;
FFEffects.昏睡.armorMultiplier = 1;
FFEffects.昏睡.speedMultiplier = 0.6;
FFEffects.昏睡.effect = newEffect(25, e => {
    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("FF8A65"));
            Fill.square(e.x + x, e.y + y, 0.1 + e.fout() * 1, 45);
        }
    })
    Angles.randLenVectors(e.id, 3, 1 + e.fin() * 1, d);
});
FFEffects.昏睡.color = Color.valueOf("#B4E6FF");
FFEffects.灼烧.damage = 2;
FFEffects.灼烧.damageMultiplier = 0.8;
FFEffects.灼烧.armorMultiplier = 1;
FFEffects.灼烧.speedMultiplier = 0.6;
FFEffects.灼烧.effect = newEffect(25, e => {
    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("e6e0ff"));
            Fill.square(e.x + x, e.y + y, 0.1 + e.fout() * 1, 45);
        }
    })
    Angles.randLenVectors(e.id, 3, 1 + e.fin() * 1, d);
});
FFEffects.灼烧.color = Color.valueOf("e6e0ff");

FFEffects.粘稠.damage = 0;
FFEffects.粘稠.damageMultiplier = 1;
FFEffects.粘稠.armorMultiplier = 0.8;
FFEffects.粘稠.speedMultiplier = 0.3;
FFEffects.粘稠.effect = newEffect(40, e => {
    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("ffefd8"));
            Fill.square(e.x + x, e.y + y, 0.1 + e.fout() * 1, 45);
        }
    })
    Angles.randLenVectors(e.id, 3, 1 + e.fin() * 1, d);
});
FFEffects.粘稠.color = Color.valueOf("ffefd8");
FFEffects.侵蚀.damage = 6;
FFEffects.侵蚀.damageMultiplier = 1;
FFEffects.侵蚀.armorMultiplier = 0.7;
FFEffects.侵蚀.speedMultiplier = 1;
FFEffects.侵蚀.effect = newEffect(50, e => {
    const c = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("9d78dc"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 2);
        }
    })
    Angles.randLenVectors(e.id, 1, 1 + 10 * e.fin(), e.rotation, 360, c);
});
FFEffects.侵蚀.color = Color.valueOf("9d78dc");
FFEffects.冰冻.damage = 6;
FFEffects.冰冻.damageMultiplier = 1;
FFEffects.冰冻.armorMultiplier = 0.7;
FFEffects.冰冻.speedMultiplier = 1;
FFEffects.冰冻.effect = newEffect(50, e => {
    const c = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("605a4a"));
            Fill.circle(e.x + x, e.y + y, e.fout() * 2);
        }
    })
    Angles.randLenVectors(e.id, 1, 1 + 10 * e.fin(), e.rotation, 360, c);
});
FFEffects.冰冻.color = Color.valueOf("605a4a");
FFEffects.爆甲.damage = 0;
FFEffects.爆甲.damageMultiplier = 1;
FFEffects.爆甲.armorMultiplier = 0;
FFEffects.爆甲.speedMultiplier = 1;
FFEffects.爆甲.effect = newEffect(30, e => {
    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("f2e877"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 5 + 0.5);
        }
    })
    Lines.stroke(e.fout() * 3);
    Angles.randLenVectors(e.id, 1, 1 + 15 * e.fin(), e.rotation, 360, d);
});
FFEffects.爆甲.color = Color.valueOf("f2e877");
FFEffects.切割.damage = 0;
FFEffects.切割.damageMultiplier = 1;
FFEffects.切割.armorMultiplier = 0.3;
FFEffects.切割.speedMultiplier = 1;
FFEffects.切割.effect = newEffect(30, e => {
    const d = new Floatc2({
        get(x, y) {
            Draw.color(Color.valueOf("c2d0e9"));
            Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 5 + 0.5);
        }
    })
    Lines.stroke(e.fout() * 3);
    Angles.randLenVectors(e.id, 1, 1 + 15 * e.fin(), e.rotation, 360, d);
});
FFEffects.切割.color = Color.valueOf("9d78dc");


this.global.FFEffects = FFEffects;