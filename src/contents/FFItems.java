package contents;

import arc.graphics.Color;
import arc.util.Time;
import mindustry.type.Item;

public class FFItems {
    public static Item
        //Building Material
        bauxite, aluminium, crystalAlloy, multiCompound,
        //Food raw resource
        leaf, wheat, flour, fermentedTea,
        iceCube, colaIceCube, teaIceCube, blackTeaIceCube,
        //Food Products
        bread, hardCandy, rainbowCandy, cannedCola, cannedNucola, tinTea, radiantCake,
        //no stop making those things
        rawCopper, copperIngot;

    public static void load() {
        rawCopper = new Item("raw-copper", Color.valueOf("c79274")){{
            explosiveness = 0f;
            flammability = 0f;
            radioactivity = 0f;
            charge = 0f;

            //this actually means drill time multiple 1f
            hardness = 10;
            cost = 0.5f;
        }};

        copperIngot = new Item("copper-ingot", Color.valueOf("dcac78")){{
            explosiveness = 0f;
            flammability = 0f;
            radioactivity = 0f;
            charge = 0f;

            //this actually means drill time multiple 1f
            hardness = 10;
            cost = 0.5f;
        }};

        bauxite = new Item("bauxite", Color.valueOf("9391bb")){{
            explosiveness = 0f;
            flammability = 0f;
            radioactivity = 0f;
            charge = 0f;

            hardness = 1;
            cost = 0.5f;
        }};

        aluminium = new Item("aluminium", Color.valueOf("d8d8d8")){{
            explosiveness = 0f;
            flammability = 0f;
            radioactivity = 0f;
            charge = 0f;

            cost = 0.8f;
        }};

        crystalAlloy = new Item("crystal-alloy", Color.valueOf("c2d0e9")){{
            explosiveness = 0f;
            flammability = 0f;
            radioactivity = 0f;
            charge = 0f;

            cost = 0.8f;
        }};

        multiCompound = new Item("multi-compound", Color.valueOf("968585")){{
            explosiveness = 0f;
            flammability = 0f;
            radioactivity = 0f;
            charge = 0f;

            cost = 1.5f;
        }};

        //todo color

        wheat = new Item("wheat", Color.valueOf("f6ca96")){{
            explosiveness = 0f;
            flammability = 0.8f;
            radioactivity = 0f;
            charge = 0f;

            buildable = false;
        }};

        leaf = new Item("leaf", Color.valueOf("509f3f")){{
            explosiveness = 0f;
            flammability = 0.8f;
            radioactivity = 0f;
            charge = 0f;

            buildable = false;
        }};

        flour = new Item("flour", Color.valueOf("eabb98").shiftHue(Time.time)){{
            explosiveness = 0f;
            flammability = 0f;
            radioactivity = 0f;
            charge = 0f;

            buildable = false;
        }};

        fermentedTea = new Item("fermented-tea", Color.valueOf("4b4032")){{
            explosiveness = 0f;
            flammability = 0.8f;
            radioactivity = 0f;
            charge = 0f;

            buildable = false;
        }};

        iceCube = new Item("ice-cube", Color.valueOf("d2e5ff")){{
            explosiveness = 0f;
            flammability = 0f;
            radioactivity = 0f;
            charge = 0f;

            cost = 1f;
        }};

        teaIceCube = new Item("tea-ice-cube", Color.valueOf("9be08e")){{
            explosiveness = 0f;
            flammability = 0f;
            radioactivity = 0f;
            charge = 0f;

            cost = 1f;
        }};

        blackTeaIceCube = new Item("black-tea-ice-cube", Color.valueOf("cb7b71")){{
            explosiveness = 0f;
            flammability = 0f;
            radioactivity = 0f;
            charge = 0f;

            cost = 1f;
        }};

        colaIceCube = new Item("cola-ice-cube", Color.valueOf("858491")){{
            explosiveness = 0f;
            flammability = 0f;
            radioactivity = 0f;
            charge = 0f;

            cost = 1f;
        }};

        bread = new Item("bread", Color.valueOf("eab678")){{
            explosiveness = 0f;
            flammability = 0f;
            radioactivity = 0f;
            charge = 0f;

            buildable = false;
        }};

        hardCandy = new Item("hard-candy", Color.valueOf("9391bb")){{
            explosiveness = 0f;
            flammability = 0f;
            radioactivity = 0f;
            charge = 0f;

            buildable = false;
        }};

        cannedCola = new Item("canned-cola", Color.valueOf("6b6f78")){{
            explosiveness = 0.4f;
            flammability = 0f;
            radioactivity = 0f;
            charge = 0f;

            buildable = false;
        }};

        cannedNucola = new Item("canned-nucola", Color.valueOf("6b6f78")){{
            explosiveness = 0.4f;
            flammability = 0f;
            radioactivity = 1.6f;
            charge = 0f;

            buildable = false;
        }};

        tinTea = new Item("tin-tea", Color.valueOf("6b6f78")){{
            explosiveness = 0.4f;
            flammability = 0f;
            radioactivity = 1.6f;
            charge = 0f;

            buildable = false;
        }};

        rainbowCandy = new Item("rainbow-candy", Color.valueOf("eabb98").shiftHue(Time.time)){{
            explosiveness = 0.4f;
            flammability = 0f;
            radioactivity = 1.8f;
            charge = 1.5f;

            buildable = false;
        }};

        radiantCake = new Item("radiant-cake", Color.valueOf("d2adda")){{
            explosiveness = 0.4f;
            flammability = 0f;
            radioactivity = 3f;
            charge = 0f;

            buildable = false;
        }};
    }
}
