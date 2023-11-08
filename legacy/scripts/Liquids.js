
const 红茶 = extendContent(Liquid,"红茶",{})
红茶.heatCapacity = 0.3;
红茶.temperature = 0.6;
红茶.viscosity = 0.6;
红茶.color = Color.valueOf("eb8777")

const 可乐 = extendContent(Liquid,"可乐",{})
可乐.heatCapacity = 0.5;
可乐.temperature = 0.4;
可乐.viscosity = 0.5;
可乐.color = Color.valueOf("484539")

const 冰镇可乐 = extendContent(Liquid,"冰镇可乐",{})
冰镇可乐.heatCapacity = 1.2;
冰镇可乐.color = Color.valueOf("615f59")
