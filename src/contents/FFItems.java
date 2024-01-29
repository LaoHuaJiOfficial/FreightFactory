package contents;

import arc.graphics.Color;
import mindustry.type.Item;

public class FFItems {
    public static Item
        bauxite, leaf,
        aluminium;

    public static void load() {
        bauxite = new Item("bauxite", Color.valueOf("9391bb")){{
            hardness = 1;
            cost = 0.5f;
        }};

        leaf = new Item("leaf", Color.valueOf("509f3f")){{
            flammability = 0.8f;
        }};
    }
}
