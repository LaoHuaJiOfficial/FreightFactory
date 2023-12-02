package contents;

import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.type.Item;

public class FFItems {
    public static final Seq<Item> NauvisItems = new Seq<>();
    public static Item
        aluminium;

    public static void load() {
        aluminium = new Item("aluminium", Color.valueOf("c8c8c8")) {{
            hardness = 1;
            cost = 0.5f;
            alwaysUnlocked = true;
        }};
    }
}
