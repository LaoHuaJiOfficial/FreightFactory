package contents;

import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import world.block.RemoteCoreBlock;

public class FFBlock {
    public static Block
        RemoteCoreInterface;

    public static void load() {
        RemoteCoreInterface = new RemoteCoreBlock("remote-core-interface") {{
            requirements(Category.effect, ItemStack.with(Items.copper, 20));
            size = 3;
        }};
    }
}
