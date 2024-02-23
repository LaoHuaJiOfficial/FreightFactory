package contents.blocks;

import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;

public class DefenseBlock {
    public static Block
        HeavyTitaniumWall, HeavyTitaniumWallLarge, CrystalAlloyWall, CrystalAlloyWallLarge;

    public static void load(){
        HeavyTitaniumWall = new Wall("heavy-titanium-wall"){{
            requirements(Category.defense, ItemStack.with(Items.titanium, 8, Items.graphite, 2));
            size = 1;
            health = 1000;
        }};

        HeavyTitaniumWallLarge = new Wall("heavy-titanium-wall-large"){{
            requirements(Category.defense, ItemStack.with(Items.titanium, 25, Items.graphite, 10));
            size = 2;
            health = 4000;
        }};

        CrystalAlloyWall = new Wall("crystal-alloy-wall"){{
            requirements(Category.defense, ItemStack.with(Items.titanium, 8, Items.graphite, 2));
            size = 1;
            health = 1500;
        }};

        CrystalAlloyWallLarge = new Wall("crystal-alloy-wall-large"){{
            requirements(Category.defense, ItemStack.with(Items.titanium, 25, Items.graphite, 10));
            size = 2;
            health = 6000;
        }};
    }
}
