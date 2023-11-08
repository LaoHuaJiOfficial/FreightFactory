
const 维修站 = extendContent(RepairPoint,"维修站",{});

const 触发器 = extendContent(BurnerGenerator,"触发器",{})
触发器.requirements(Category.power, BuildVisibility.sandboxOnly, ItemStack.with());

const 计时器30s = extendContent(Cultivator,"计时器30s",{})
计时器30s.requirements(Category.power, BuildVisibility.sandboxOnly, ItemStack.with());

const 小型舰载装甲 = extendContent(DeflectorWall,"小型舰载装甲",{})
小型舰载装甲.requirements(Category.power, BuildVisibility.sandboxOnly, ItemStack.with());

const 标准舰载装甲 = extendContent(DeflectorWall,"标准舰载装甲",{})
标准舰载装甲.requirements(Category.power, BuildVisibility.sandboxOnly, ItemStack.with());
