package contents;

import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.environment.OreBlock;
import world.block.production.ScanMiner;
import world.block.storage.RemoteCoreBlock;

public class FFBlock {
    public static Block
        oreAluminium,
        RemoteCoreInterface, test;

    public static void load() {
        oreAluminium = new OreBlock(FFItems.aluminium){{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};
        RemoteCoreInterface = new RemoteCoreBlock("remote-core-interface") {{
            requirements(Category.effect, ItemStack.with(Items.copper, 20));
            size = 3;
        }};

        test = new ScanMiner("test"){{
            requirements(Category.production, ItemStack.with(Items.copper, 20));
            size = 4;
        }};
    }
}
