package contents;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.Liquid;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.environment.OreBlock;
import world.block.distribution.ItemDiode;
import world.block.distribution.LiquidDiode;
import world.block.liquid.CoolantConduit;
import world.block.production.Assembler;
import world.block.storage.RemoteCoreBlock;
import world.block.tech.ResearchCenter;

import static mindustry.type.ItemStack.with;

public class FFBlock {
    public static Block
        oreAluminium,
        RemoteCoreInterface, test, conduit, assembler, ItemDiode, LiquidDiode;

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
        test = new ResearchCenter("test") {{
            requirements(Category.production, ItemStack.with(Items.copper, 20));
            size = 4;
        }};

        conduit = new CoolantConduit("conduit"){{
            requirements(Category.liquid, with(Items.metaglass, 1));
            health = 45;
        }};

        assembler = new Assembler("assembler"){{
            requirements(Category.production, with(Items.metaglass, 1));
            plans = Seq.with(
                new AssemblerPlan(30f, with(Items.copper, 2), LiquidStack.with(Liquids.oil, 1f), new ItemStack(Items.lead, 2), Liquids.slag,20f),
                new AssemblerPlan(20f, with(Items.metaglass, 2), LiquidStack.with(Liquids.cryofluid, 1.5f), new ItemStack(Items.copper, 2), Liquids.water, 20f)
            );
            health = 45;
        }};

        ItemDiode = new ItemDiode("ItemDiode"){{
            requirements(Category.distribution, ItemStack.with(Items.copper, 20));

        }};

        LiquidDiode = new LiquidDiode("LiquidDiode"){{
            requirements(Category.distribution, ItemStack.with(Items.copper, 20));

        }};
    }
}
