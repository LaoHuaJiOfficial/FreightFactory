package contents;

import HeatBox.HeatBoxBlock;
import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.environment.OreBlock;
import world.block.distribution.ItemDiode;
import world.block.distribution.LiquidDiode;
import world.block.liquid.CoolantConduit;
import world.block.production.AssemblerBlock;
import world.block.storage.RemoteCoreBlock;
import world.block.tech.Nexus;
import world.test;

import static mindustry.type.ItemStack.with;

public class FFBlock {
    public static Block
        oreAluminium,

        nexus,
        RemoteCoreInterface, test, conduit, assembler, ItemDiode, LiquidDiode, HeatConduit;

    public static void load() {
        oreAluminium = new OreBlock(FFItems.aluminium) {{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};

        RemoteCoreInterface = new RemoteCoreBlock("remote-core-interface") {{
            requirements(Category.effect, ItemStack.with(Items.copper, 20));
            size = 3;
        }};

        //TODO rework
        test = new test("test") {{
            requirements(Category.production, ItemStack.with(Items.copper, 20));
            size = 2;
        }};

        conduit = new CoolantConduit("conduit"){{
            requirements(Category.liquid, with(Items.metaglass, 1));
            health = 45;
        }};

        assembler = new AssemblerBlock("assembler"){{
            requirements(Category.production, with(Items.copper, 1));

            size = 3;
            recipes = Seq.with(
                new Recipe(){{
                    InputItems = with(Items.copper, 2, Items.coal, 3);
                    InputPower = 20f/60f;

                    OutputItems = with(Items.metaglass, 2);
                }},
                new Recipe(){{
                    InputLiquids = LiquidStack.with(Liquids.arkycite, 2f, Liquids.oil, 3f);
                    InputPower = 20f/60f;

                    OutputItems = with(Items.blastCompound, 2);
                }},
                new Recipe(){{
                    InputItems = new ItemStack[]{new ItemStack(Items.lead, 2)};
                    InputLiquids = LiquidStack.with(Liquids.cryofluid, 2f, Liquids.oil, 3f);
                    InputPower = 30f/60f;

                    OutputItems = with(Items.beryllium, 2);
                }}


            );
            health = 45;
        }};

        nexus = new Nexus("nexus"){{
            requirements(Category.effect, ItemStack.with(Items.copper, 20));

            size = 4;
        }};

        ItemDiode = new ItemDiode("ItemDiode"){{
            requirements(Category.distribution, ItemStack.with(Items.copper, 20));

        }};

        LiquidDiode = new LiquidDiode("LiquidDiode"){{
            requirements(Category.distribution, ItemStack.with(Items.copper, 20));

        }};

        HeatConduit = new HeatBoxBlock("heat-conduit"){{
            requirements(Category.power, ItemStack.with(Items.copper, 20));

        }};
    }
}
