package contents.blocks;

import HeatBox.BlockF;
import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.draw.*;
import world.block.production.AssemblerBlock;
import world.drawer.DrawArcFurnace;

import static mindustry.type.ItemStack.with;

public class ProductionBlock {
    public static BlockF
        ArcFurnace;

    public static void load(){
        ArcFurnace = new AssemblerBlock("arc-furnace"){{
            requirements(Category.crafting, with(Items.copper, 30, Items.beryllium, 25));

            size = 4;

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault() ,new DrawArcFurnace());


            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipes = Seq.with(
                new Recipe(){{
                    InputItems = with(Items.sand, 4);
                    InputPower = 30f/60f;

                    OutputItems = with(Items.silicon, 2);
                }}
            );
        }};
    }
}
