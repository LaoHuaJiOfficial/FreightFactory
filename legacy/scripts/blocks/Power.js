
const 超级无限电 = extendContent(PowerSource,"超级无限电",{})
超级无限电.requirements(Category.power, BuildVisibility.sandboxOnly, ItemStack.with());


const 放射性临界反应堆 = extendContent(ImpactReactor, "放射性临界反应堆", {
    update(tile) {
        const entity = tile.ent();

        if (entity.cons.valid() && entity.power.status >= 0.99) {
            var prevOut = this.getPowerProduction(tile) <= this.consumes.getPower().requestedPower(entity);

            entity.warmup = Mathf.lerpDelta(entity.warmup, 1, this.warmupSpeed);
            if (Mathf.equal(entity.warmup, 1, 0.001)) {
                entity.warmup = 1;
            }

            if (!prevOut && (this.getPowerProduction(tile) > this.consumes.getPower().requestedPower(entity))) {
                //Events.fire(Trigger.impactPower);后面在试
            }

            if (entity.timer.get(this.timerUse, this.itemDuration / entity.timeScale)) {
                entity.cons.trigger();
                Effects.effect(newEffect(25, e => {
                    const d = new Floatc2({
                        get(x, y) {
                            Draw.color(e.color, Color.valueOf("#d2adda"), e.fin());
                            Fill.square(e.x + x, e.y + y, 0.1 + e.fout() * 2 + 1, 45);
                        }
                    })
                    Angles.randLenVectors(e.id, 20, 20 + e.fin() * 20, d);

                }), tile)
                Effects.shake(5, 5, tile)
                Sounds.explosionbig.at(tile);
                for (var i = 0; i < 7; i++) {
                    Lightning.create(Team.derelict, Color.valueOf("d9c0c0"), 5, tile.drawx(), tile.drawy(), Mathf.random(360), 25);
                }

            }
            if (Mathf.chance(Time.delta() * 0.07)) {
                Effects.shake(1, 1, tile)
                Lightning.create(Team.derelict, Color.valueOf("d9c0c0"), 5, tile.drawx(), tile.drawy(), Mathf.random(360), 25);
            }
        } else {
            entity.warmup = Mathf.lerpDelta(entity.warmup, 0, 0.01);
        }


        entity.productionEfficiency = Mathf.pow(entity.warmup, 5);
    },
    // const 烟雾效果 = newEffect(20, e => {
    //     Angles.randLenVectors(e.id, 8, 5 + e.fin() * 10, (x, y) => {
    //         Draw.color(e.color, Color.lightGray, e.fin());
    //         Fill.square(e.x + x, e.y + y, e.fout() * 2 + 0.5, 45);
    //     });
    // });
    draw(tile) {
        const entity = tile.ent();
        const plasmas = 4;
        var plasmaRegions = new Array();
        for (var i = 0; i < 4; i++) {
            plasmaRegions[i] = "食品工厂-放射性临界反应堆-plasma-" + i;
        }
        Draw.rect(Core.atlas.find(this.name + "-bottom"), tile.drawx(), tile.drawy());

        for (var i = 0; i < 4; i++) {
            var r = 29 + Mathf.absin(Time.time(), 2 + i * 1, 5 - i * 0.5);

            Draw.color(Color.valueOf("d9c0c0"), Color.valueOf("968585"), i / 4);
            Draw.alpha((0.3 + Mathf.absin(Time.time(), 2 + i * 2, 0.3 + i * 0.05)) * entity.warmup);
            //Draw.blend();
            Draw.rect(Core.atlas.find(plasmaRegions[i]), tile.drawx(), tile.drawy(),/* r, r,*/ Time.time() * (12 + i * 6) * entity.warmup);
            //Draw.blend();
        }

        Draw.color();

        Draw.rect(Core.atlas.find(this.name), tile.drawx(), tile.drawy());

        Draw.color();
    },
    generateIcons() {
        return [
            Core.atlas.find(this.name + "-bottom"),
            Core.atlas.find(this.name),
        ];
    }
})

