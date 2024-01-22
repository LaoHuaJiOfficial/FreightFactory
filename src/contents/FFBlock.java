package contents;

import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.world.Block;
import prototypes.block.HeatBox.HeatPipe;
import prototypes.block.distribution.BeltConveyor;
import prototypes.block.distribution.BeltRouter;
import utilities.FVars;

import static mindustry.type.ItemStack.with;

public class FFBlock {
    public static Block
        oreAluminium,

    nexus,
        RemoteCoreInterface, test, conduit, assembler, ItemDiode, LiquidDiode,
        HeatConduit, HeatProducer, HeatCond, HeatCrafter, RocketSilo,

    //Actually new stuffs here

    //Production,Factory
    ArcFurnace,
        conveyorT1, routerT1;


    public static void load() {
        conveyorT1 = new BeltConveyor("conveyor-t1") {{
            requirements(Category.distribution, with(Items.beryllium, 1));
            health = 100;
            itemPerSecond = 10f;
        }};

        routerT1 = new BeltRouter("router-t1") {{
            requirements(Category.distribution, with(Items.beryllium, 1));
            health = 100;
            itemPerSecond = 10f;
        }};

        HeatConduit = new HeatPipe("heat-pipe") {{
            requirements(Category.power, with(Items.copper, 20));
            HasHeat = true;

            MaxTemp = FVars.BaseLine + 1000f;
            MinTemp = FVars.BaseLine - 100f;
        }};
        /*
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

        RocketSilo = new Nexus("rocket-silo"){{
            requirements(Category.effect, ItemStack.with(Items.copper, 20));

            size = 16;
        }};

        ItemDiode = new ItemDiode("ItemDiode"){{
            requirements(Category.distribution, ItemStack.with(Items.copper, 20));

        }};

        LiquidDiode = new LiquidDiode("LiquidDiode"){{
            requirements(Category.distribution, ItemStack.with(Items.copper, 20));

        }};



        HeatCond = new BlockF("heat-cond"){{
            requirements(Category.power, ItemStack.with(Items.copper, 20));

            HeatCapacity = 50f;

            MaxTemp = FVars.BaseLine + 1000f;
            MinTemp = FVars.BaseLine - 100f;

        }};

        HeatProducer = new BlockF("heat-producer"){{
            requirements(Category.power, ItemStack.with(Items.copper, 20));


            OutputHeatAmount = 1000f;
            OutputTempThreshold = 800f;

            InputHeatAmount = 1000f;
            InputTempThreshold = 200f;
        }};

        HeatCrafter = new AssemblerBlock("heat-crafter"){{
            requirements(Category.production, with(Items.copper, 1));
            HasHeat = true;

            recipes = Seq.with(
                new Recipe(){{
                    InputItems = with(Items.coal, 3);
                    InputPower = 20f/60f;

                    OutputHeatAmount = 10f;
                    OutputTempThreshold = FVars.BaseLine + 500f;
                    OutputItems = with(Items.copper, 1);

                }},

                new Recipe(){{
                    InputLiquids = LiquidStack.with(Liquids.cryofluid, 5f);
                    InputPower = 30f/60f;

                    IsCoolant = true;
                    InputHeatAmount = 10f;
                    InputTempThreshold = FVars.BaseLine - 100f;

                }}
            );
        }};
         */
    }
}
