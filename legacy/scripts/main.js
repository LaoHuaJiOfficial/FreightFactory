
const 硬糖 = extendContent(Item,"硬糖",{});
硬糖.color = Color.valueOf("b0a4b7");

const 彩虹糖果 = extendContent(Item,"彩虹糖果",{})
彩虹糖果.explosiveness = 0.3;
彩虹糖果.radioactivity = 1.8;
彩虹糖果.color = Color.valueOf("e9a098")

const 铝罐 = extendContent(Item,"铝罐",{})
铝罐.color = Color.valueOf("#d5d4d4")

const 罐装可乐 = extendContent(Item,"罐装可乐",{})
罐装可乐.color = Color.valueOf("#55585f")
彩虹糖果.explosiveness = 0.4;

const 摇过的可乐 = extendContent(Item,"摇过的可乐",{})
摇过的可乐.color = Color.valueOf("#36373d")
摇过的可乐.explosiveness = 0.8;

const 核子可乐 = extendContent(Item,"核子可乐",{})
核子可乐.color = Color.valueOf("#c48cb9")
核子可乐.radioactivity = 1.6

const 冰块 = extendContent(Item,"冰块",{});
冰块.color = Color.valueOf("dae4fd")

const 可乐冰块 = extendContent(Item,"可乐冰块",{});
可乐冰块.color = Color.valueOf("848490")

const 绿茶冰块 = extendContent(Item,"绿茶冰块",{});
绿茶冰块.color = Color.valueOf("9be08f")

const 红茶冰块 = extendContent(Item,"红茶冰块",{});
红茶冰块.color = Color.valueOf("c97a6f")

const 面团 = extendContent(Item,"面团",{});
面团.color = Color.valueOf("fff6ea")

const 面包 = extendContent(Item,"面包",{});
面包.color = Color.valueOf("dda159")

const 晶状合金 = extendContent(Item,"晶状合金",{});
晶状合金.color = Color.valueOf("cedbe3")
晶状合金.type = ItemType.material;


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

const 糖浆 = extendContent(Liquid,"糖浆",{})
糖浆.description = "用糖熬制而成的糖浆.",
糖浆.heatCapacity =  0.1,
糖浆.temperature = 0.8,
糖浆.viscosity = 0.8,
糖浆.color = Color.valueOf("dfd7e6")


// require("Items")
// require("Liquids")
require("Effects")
require("Bullets")
require("Weapons")
require("Mechs")
require("blocks/Turret")
require("blocks/Power")
require("blocks/Crafting")
require("blocks/Block")

