package contents.blocks;

import contents.FFItems;
import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;

public class DefenseBlock {
    public static Block
        AluminiumWall, AluminiumWallLarge, CrystalAlloyWall, CrystalAlloyWallLarge,
        HeavySurgeWall, HeavySurgeWallLarge, MultiCompoundWall, MultiCompoundWallLarge;

    public static void load(){
        AluminiumWall = new Wall("aluminium-wall"){{
            requirements(Category.defense, ItemStack.with(Items.copper, 2, FFItems.aluminium, 8));
            size = 1;
            health = 1000;
            absorbLasers = true;
        }};

        AluminiumWallLarge = new Wall("aluminium-wall-large"){{
            requirements(Category.defense, ItemStack.with(Items.copper, 10, FFItems.aluminium, 30));
            size = 2;
            health = 4000;
            absorbLasers = true;
        }};

        CrystalAlloyWall = new Wall("crystal-alloy-wall"){{
            requirements(Category.defense, ItemStack.with(Items.titanium, 2, FFItems.crystalAlloy, 8));
            size = 1;
            health = 1500;
            absorbLasers = true;
        }};

        CrystalAlloyWallLarge = new Wall("crystal-alloy-wall-large"){{
            requirements(Category.defense, ItemStack.with(Items.titanium, 10, FFItems.crystalAlloy, 30));
            size = 2;
            health = 6000;
            absorbLasers = true;
        }};

        HeavySurgeWall = new Wall("heavy-surge-wall"){{
            requirements(Category.defense, ItemStack.with(Items.thorium, 2, Items.surgeAlloy, 8));
            size = 1;
            health = 2000;
            absorbLasers = true;
        }};

        HeavySurgeWallLarge = new Wall("heavy-surge-wall-large"){{
            requirements(Category.defense, ItemStack.with(Items.thorium, 10, Items.surgeAlloy, 30));
            size = 2;
            health = 8000;
            absorbLasers = true;
        }};

        MultiCompoundWall = new Wall("multi-compound-wall"){{
            requirements(Category.defense, ItemStack.with(Items.phaseFabric, 2, FFItems.multiCompound, 8));
            size = 1;
            health = 2500;
            absorbLasers = true;
        }};

        MultiCompoundWallLarge = new Wall("multi-compound-wall-large"){{
            requirements(Category.defense, ItemStack.with(Items.phaseFabric, 10, FFItems.multiCompound, 30));
            size = 2;
            health = 10000;
            absorbLasers = true;
        }};
    }
}
