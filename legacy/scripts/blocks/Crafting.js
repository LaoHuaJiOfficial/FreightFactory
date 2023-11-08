
//===========================================================================================
//方块 工厂

// const 彩虹糖加工厂效果 = newEffect(20, e => {
//     //var color = [Color.red,Color.yellow,Color.blue]
//     Draw.color(Color.red, Color.lightGray, e.fin());
//     // Draw.color(color[Math.floor((Math.random()*3)+1)], Color.lightGray, e.fin());
//     Lines.stroke(e.fout() * 3);
//     Lines.circle(e.x, e.y, e.fin() * 100);
// });
const 彩虹糖加工厂 = extendContent(GenericSmelter, "彩虹糖加工厂", {
    draw(tile) {
        Draw.rect(Core.atlas.find(this.name + "-底座"), tile.drawx(), tile.drawy());
        Draw.color(tile.entity.liquids.current().color);
        Draw.alpha(tile.entity.liquids.total() / this.liquidCapacity);
        Draw.rect(Core.atlas.find(this.name + "-liquid"), tile.drawx(), tile.drawy());
        Draw.color();
        Draw.rect(Core.atlas.find(this.name + "-rotator"), tile.drawx(), tile.drawy(), 90 + tile.ent().totalProgress * 3)//以后改成entity.warmup 
        Draw.rect(this.region, tile.drawx(), tile.drawy())

    },
    generateIcons() {
        return [
            Core.atlas.find(this.name + "-底座"),
            Core.atlas.find(this.name),
        ];
    }
});
彩虹糖加工厂.outputItem = ItemStack(彩虹糖果, 1);
// 彩虹糖加工厂.updateEffect = 彩虹糖加工厂效果;


const 可乐调配机 = extendContent(GenericSmelter, "可乐调配机", {

    draw(tile) {
        Draw.rect(Core.atlas.find(this.name + "-底座"), tile.drawx(), tile.drawy());
        Draw.color(Color.valueOf("484539"));
        Draw.alpha(tile.entity.liquids.get(可乐) / this.liquidCapacity);
        Draw.rect(Core.atlas.find(this.name + "-liquid"), tile.drawx(), tile.drawy());
        Draw.color();
        Draw.rect(Core.atlas.find(this.name + "-rotator"), tile.drawx(), tile.drawy(), 0 + tile.ent().totalProgress * 2)//以后改成entity.warmup 
        Draw.rect(this.region, tile.drawx(), tile.drawy())
    },
    generateIcons() {
        return [
            Core.atlas.find(this.name + "-底座"),
            Core.atlas.find(this.name),
        ];
    }
});

const 可乐冷冻机 = extendContent(GenericCrafter, "可乐冷冻机", {
    draw(tile) {
        Draw.rect(Core.atlas.find(this.name), tile.drawx(), tile.drawy())
        Draw.color(tile.entity.liquids.current().color)
        Draw.alpha(tile.entity.liquids.total() / this.liquidCapacity);
        Draw.rect(Core.atlas.find(this.name + "-liquid"), tile.drawx(), tile.drawy());
        Draw.color();
        Draw.rect(Core.atlas.find(this.name + "-top"), tile.drawx(), tile.drawy())
    },
    generateIcons() {
        return [
            Core.atlas.find(this.name),
            Core.atlas.find(this.name + "-top")
        ];
    }
});
const 茶水冷冻机 = extendContent(GenericCrafter, "茶水冷冻机", {
    draw(tile) {
        Draw.rect(Core.atlas.find(this.name), tile.drawx(), tile.drawy())
        Draw.color(tile.entity.liquids.current().color)
        Draw.alpha(tile.entity.liquids.total() / this.liquidCapacity);
        Draw.rect(Core.atlas.find(this.name + "-liquid"), tile.drawx(), tile.drawy());
        Draw.color();
        Draw.rect(Core.atlas.find(this.name + "-top"), tile.drawx(), tile.drawy())
    },
    generateIcons() {
        return [
            Core.atlas.find(this.name),
            Core.atlas.find(this.name + "-top")
        ];
    }
});

const 红茶调制机 = extendContent(GenericCrafter, "红茶调制机", {

    draw(tile) {
        Draw.rect(this.region, tile.drawx(), tile.drawy())
        Draw.color(Color.valueOf("eb8777"));//以后有需要时改成输出液体.color
        Draw.alpha(tile.entity.liquids.get(红茶) / this.liquidCapacity);
        Draw.rect(Core.atlas.find(this.name + "-liquid"), tile.drawx(), tile.drawy());
        Draw.color();
        Draw.rect(Core.atlas.find(this.name + "-rotator"), tile.drawx(), tile.drawy(), 0 + tile.ent().totalProgress * 3)
        Draw.rect(Core.atlas.find(this.name + "-top"), tile.drawx(), tile.drawy())
    },
    generateIcons() {
        return [
            Core.atlas.find(this.name),
            Core.atlas.find(this.name + "-rotator"),
            Core.atlas.find(this.name + "-top"),
        ];
    }
});

const 面粉调和机 = extendContent(GenericCrafter, "面粉调和机", {
    draw(tile) {
        Draw.rect(Core.atlas.find(this.name + "-底座"), tile.drawx(), tile.drawy());
        Draw.color(tile.entity.liquids.current().color);
        Draw.alpha(tile.entity.liquids.total() / this.liquidCapacity);
        Draw.rect(Core.atlas.find(this.name + "-liquid"), tile.drawx(), tile.drawy());
        Draw.color();
        Draw.rect(Core.atlas.find(this.name + "-rotator"), tile.drawx(), tile.drawy(), 90 + tile.ent().totalProgress * 2)//以后改成entity.warmup 
        Draw.rect(this.region, tile.drawx(), tile.drawy())

    },
    generateIcons() {
        return [
            Core.atlas.find(this.name + "-底座"),
            Core.atlas.find(this.name),
        ];
    }
});

const 小麦研磨机 = extendContent(GenericCrafter, "小麦研磨机", {
    draw(tile) {
        Draw.rect(Core.atlas.find(this.name + "-底座"), tile.drawx(), tile.drawy());
        Draw.color();
        Draw.rect(Core.atlas.find(this.name + "-rotator"), tile.drawx(), tile.drawy(), 90 + tile.ent().totalProgress * 2)//以后改成entity.warmup 
        Draw.rect(this.region, tile.drawx(), tile.drawy())

    },
    generateIcons() {
        return [
            Core.atlas.find(this.name + "-底座"),
            Core.atlas.find(this.name),
        ];
    }
});

const 红茶冷冻机 = extendContent(GenericCrafter, "红茶冷冻机", {
    draw(tile) {
        Draw.rect(this.region, tile.drawx(), tile.drawy())
        Draw.color(tile.entity.liquids.current().color)
        Draw.alpha(tile.entity.liquids.total() / this.liquidCapacity);
        Draw.rect(Core.atlas.find(this.name + "-liquid"), tile.drawx(), tile.drawy());
        Draw.color();
        Draw.rect(Core.atlas.find(this.name + "-top"), tile.drawx(), tile.drawy())
    },
    generateIcons() {
        return [
            Core.atlas.find(this.name),
            Core.atlas.find(this.name + "-top")
        ];
    }
});
const 制冰机 = extendContent(GenericCrafter, "制冰机", {
    draw(tile) {
        Draw.rect(this.region, tile.drawx(), tile.drawy())
        Draw.color(tile.entity.liquids.current().color)
        Draw.alpha(tile.entity.liquids.total() / this.liquidCapacity);
        Draw.rect(Core.atlas.find(this.name + "-liquid"), tile.drawx(), tile.drawy());
        Draw.color();
        Draw.rect(Core.atlas.find(this.name + "-top"), tile.drawx(), tile.drawy())
    },
    generateIcons() {
        return [
            Core.atlas.find(this.name),
            Core.atlas.find(this.name + "-top")
        ];
    }
});


const 摇罐器 = extendContent(GenericCrafter, "摇罐器", {
    draw(tile) {
        Draw.rect(this.region, tile.drawx(), tile.drawy())
        Draw.color();
        Draw.rect(Core.atlas.find(this.name + "-rotator"), tile.drawx(), tile.drawy(), 0 + tile.ent().totalProgress * 4)
    },
    generateIcons() {
        return [
            Core.atlas.find(this.name),
            Core.atlas.find(this.name + "-rotator"),
        ];
    }
});


const 核子可乐压缩机 = extendContent(GenericSmelter, "核子可乐压缩机", {
    draw(tile) {
        var frameRegions = new Array();
        for (var i = 0; i < 4; i++) {
            frameRegions[i] = "食品工厂-核子可乐压缩机-F" + i;
        }
        Draw.rect(this.region, tile.drawx(), tile.drawy())
        Draw.color(Color.valueOf("9f5f9f"));
        Draw.alpha(tile.entity.liquids.total() / this.liquidCapacity);
        Draw.rect(Core.atlas.find(this.name + "-top"), tile.drawx(), tile.drawy());
        Draw.color()
        Draw.rect(Core.atlas.find(frameRegions[Math.floor(Mathf.absin(tile.ent().totalProgress, 5.2, 3.999))]), tile.drawx(), tile.drawy());
    },
    generateIcons() {
        return [
            Core.atlas.find(this.name),
        ];
    }
});


const 糖果压缩机 = extendContent(GenericSmelter, "糖果压缩机", {

    draw(tile) {
        var frameRegions = new Array();
        for (var i = 0; i < 3; i++) {
            frameRegions[i] = "食品工厂-糖果压缩机-F" + i;
        }
        Draw.rect(this.region, tile.drawx(), tile.drawy())
        Draw.color(Color.valueOf("dfd7e6"));
        Draw.alpha(tile.entity.liquids.total() / this.liquidCapacity);
        Draw.rect(Core.atlas.find(this.name + "-liquid"), tile.drawx(), tile.drawy());
        Draw.color()
        Draw.rect(Core.atlas.find(frameRegions[Math.floor(Mathf.absin(tile.ent().totalProgress, 7, 2.999))]), tile.drawx(), tile.drawy());
        Draw.rect(Core.atlas.find(this.name + "-top"), tile.drawx(), tile.drawy());
    },
    generateIcons() {
        return [
            Core.atlas.find(this.name),
            Core.atlas.find(this.name + "-F0"),
            Core.atlas.find(this.name + "-top"),
        ];
    }
});


const 冰镇可乐调配机 = extendContent(GenericSmelter, "冰镇可乐调配机", {

    draw(tile) {
        Draw.rect(Core.atlas.find(this.name + "-底座"), tile.drawx(), tile.drawy());
        Draw.color(tile.entity.liquids.current().color);
        Draw.alpha(tile.entity.liquids.get(冰镇可乐) / this.liquidCapacity);
        Draw.rect(Core.atlas.find(this.name + "-liquid"), tile.drawx(), tile.drawy());
        Draw.color();
        Draw.rect(Core.atlas.find(this.name + "-rotator"), tile.drawx(), tile.drawy(), 45 + tile.ent().totalProgress * 3.5)
        Draw.rect(Core.atlas.find(this.name + "-rotator2"), tile.drawx(), tile.drawy(), -tile.ent().totalProgress * 0.8)
        Draw.rect(this.region, tile.drawx(), tile.drawy())


        Draw.color()
    },
    generateIcons() {
        return [
            Core.atlas.find(this.name + "-底座"),
            Core.atlas.find(this.name)
        ];
    }
});

// const 烟雾效果 = newEffect(20, e => {
//     Angles.randLenVectors(e.id, 8, 5 + e.fin() * 10, (x, y) => {
//         Draw.color(e.color, Color.lightGray, e.fin());
//         Fill.square(e.x + x, e.y + y, e.fout() * 2 + 0.5, 45);
//     });
// });
//可乐装罐机.updateEffect = 烟雾效果;
//不能转换 js箭头函数=> 到 这个形参
//org.mozilla.javascript.EvaluatorException: Cannot convert org.mozilla.javascript.ArrowFunction@294bdeb4 to arc.func.Floatc2 (JS.js#281)

const 糖分提取机 = extendContent(GenericCrafter, "糖分提取机", {
    draw(tile) {
        Draw.rect(this.region, tile.drawx(), tile.drawy());
        Draw.color(Color.valueOf("7457ce"));
        Draw.alpha(tile.ent().warmup);
        Draw.rect(Core.atlas.find(this.name + "-liquid"), tile.drawx(), tile.drawy())
        Draw.color();
        Draw.rect(Core.atlas.find(this.name + "-top"), tile.drawx(), tile.drawy())
    },
    generateIcons() {
        return [
            Core.atlas.find(this.name),
            Core.atlas.find(this.name + "-top"),
        ];
    }
})
const 量子传送器 = extendContent(Unloader, "量子传送器", {

    acceptItem(item, tile, source) {
        const entity = tile.ent();
        return entity.linkedCore != null ? entity.linkedCore.block().acceptItem(item, entity.linkedCore, source) : tile.entity.items.get(item) < this.getMaximumAccepted(tile, item);
    },
    update(tile) {

        const entity = tile.ent()
        const core = Vars.state.teams.closestCore(tile.drawx(), tile.drawy(), tile.getTeam())

        if (tile.entity.timer.get(this.timerUnload, this.speed / entity.timeScale)) {
            if (entity.sortItem !== null && tile.entity.items.has(entity.sortItem) && entity.cons.valid() &&
                core.tile.block().acceptItem(entity.sortItem, core.tile, Edges.getFacingEdge(tile, core.tile))) {
                Calls.transferItemTo(entity.sortItem, 1, tile.drawx(), tile.drawy(), core.tile)
                entity.items.remove(entity.sortItem, 1)
            }
        }
    }
})
// 量子传送器.hasItem = true
量子传送器.itemCapacity = 1
量子传送器.solid = true;
量子传送器.update = false;
量子传送器.destructible = true;
量子传送器.consumes.power(4)
