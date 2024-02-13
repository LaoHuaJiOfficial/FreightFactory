package contents;

import arc.graphics.Color;
import mindustry.type.Item;

public class FFItems {
    public static Item
        hardCandy, rainbowCandy, cannedCola, cannedNucola, tinTea, radiantCake, bread,
        bauxite, aluminium, crystalAlloy, multiCompound,
        leaf, wheat, sugar, flour, dough, fermentedTea,
        iceCube, colaIceCube, teaIceCube, redTeaIceCube;

    public static void load() {
        //todo color
        hardCandy = new Item("hard-candy", Color.valueOf("9391bb")){{
            explosiveness = 0f;
            flammability = 0f;
            radioactivity = 0f;
            charge = 0f;

            buildable = false;
        }};

        //todo color
        hardCandy = new Item("rainbow-candy", Color.valueOf("9391bb")){{
            explosiveness = 40f;
            flammability = 0f;
            radioactivity = 180f;
            charge = 150f;

            buildable = false;
        }};

        bauxite = new Item("bauxite", Color.valueOf("9391bb")){{
            hardness = 1;
            cost = 0.5f;
        }};

        leaf = new Item("leaf", Color.valueOf("509f3f")){{
            flammability = 0.8f;
        }};

        wheat = new Item("wheat", Color.valueOf("509f3f")){{
            flammability = 0.8f;
        }};

        aluminium = new Item("aluminium", Color.valueOf("d8d8d8")){{
            cost = 0.8f;
        }};

        iceCube = new Item("ice-cube", Color.valueOf("d2e5ff")){{
            cost = 1f;
        }};
    }
}
