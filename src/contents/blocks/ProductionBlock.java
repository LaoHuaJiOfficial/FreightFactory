package contents.blocks;

import arc.struct.Seq;
import contents.FFItems;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.draw.*;
import prototypes.block.production.AreaDrill;
import prototypes.block.production.AssemblerBlock;
import prototypes.drawer.DrawArcFurnace;

import static mindustry.type.ItemStack.with;

public class ProductionBlock {
    public static Block
        //Production
        ResonanceDrill, Greenhouse,
        //Factories
        FoundryT1, CompressorT1, SmelterT1, KilnT1, ChemicalPlantT1,
        ArcFurnace;

    public static void load() {
        ResonanceDrill = new AreaDrill("resonance-drill"){{
            requirements(Category.production, with(Items.copper, 35, Items.lead, 20));

            mineOffset = -2;
            drillTime = 280;
            size = 3;
            hasPower = true;
            tier = 4;
            updateEffect = Fx.pulverizeMedium;
            drillEffect = Fx.mineBig;

            consumePower(1.10f);
        }};

        Greenhouse = new AssemblerBlock("greenhouse"){{
            requirements(Category.production, with(Items.copper, 15, Items.lead, 20));

            size = 3;

            drawer = new DrawMulti(
                new DrawRegion("-base"),
                new DrawLiquidRegion(Liquids.water),
                new DrawCultivator(),
                new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            hasLiquids = true;

            recipes = Seq.with(
                new Recipe() {{
                    inputLiquids = LiquidStack.with(Liquids.water, 12f / 60f);
                    inputPower = 40f / 60f;

                    outputItems = with(FFItems.leaf, 2);

                    craftTime = 120f;

                    craftEffect = Fx.smeltsmoke;

                }},

                new Recipe() {{
                    inputLiquids = LiquidStack.with(Liquids.water, 10f / 60f);
                    inputPower = 30f / 60f;

                    outputItems = with(FFItems.wheat, 3);

                    craftTime = 180f;

                    craftEffect = Fx.smeltsmoke;
                }}
            );
        }};

        CompressorT1 = new AssemblerBlock("compressor-t1"){{
            requirements(Category.crafting, with(Items.copper, 30, FFItems.bauxite, 25));

            size = 3;

            drawer = new DrawMulti(new DrawDefault(), new DrawArcFurnace());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipes = Seq.with(
                new Recipe() {{
                    inputItems = with(Items.coal, 4);
                    inputPower = 30f / 60f;

                    outputItems = with(Items.graphite, 2);

                    craftTime = 120f;

                    updateEffect = craftEffect = Fx.smeltsmoke;
                }},

                new Recipe() {{
                    inputItems = with(Items.coal, 4);
                    inputLiquids = LiquidStack.with(Liquids.water, 4f / 60f);
                    inputPower = 80f / 60f;

                    outputItems = with(Items.graphite, 3);

                    craftTime = 90f;

                    updateEffect = craftEffect = Fx.smeltsmoke;

                }}

            );
        }};

        SmelterT1 = new AssemblerBlock("smelter-t1"){{
            requirements(Category.crafting, with(Items.copper, 30, Items.graphite, 25));

            size = 3;

            drawer = new DrawMulti(new DrawRegion("-base"), new DrawArcFurnace(), new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipes = Seq.with(
                new Recipe() {{
                    inputItems = with(Items.sand, 6, Items.coal, 3);
                    inputPower = 40f / 60f;

                    outputItems = with(Items.silicon, 3);

                    craftTime = 120f;

                    updateEffect = craftEffect = Fx.smeltsmoke;

                    recipeName = "Basic Silicon Smelt";
                    recipeDescription = """
                        Uses coal to refine silicon from sand.
                        Doesn't need much power.""";
                }},

                new Recipe() {{
                    inputItems = with(Items.sand, 6);
                    inputLiquids = LiquidStack.with(Liquids.water, 6f/ 60f);
                    inputPower = 240f / 60f;

                    outputItems = with(Items.silicon, 4);
                    outputLiquids = LiquidStack.with(Liquids.ozone, 4f/ 60f);

                    craftTime = 150f;
                    ignoreLiquidFullness = true;

                    updateEffect = craftEffect = Fx.smeltsmoke;

                    recipeName = "Advance Silicon Smelt";
                    recipeDescription = """
                        Electrolyzing water to produce hydrogen to process sand.
                        Ozone as byproduct.
                        Need significant larger amount of power but more productive.""";
                }}
            );
        }};

        KilnT1 = new AssemblerBlock("kiln-t1"){{
            requirements(Category.crafting, with(Items.copper, 30, Items.graphite, 25));

            size = 3;

            drawer = new DrawMulti(new DrawRegion("-base"), new DrawCrucibleFlame(), new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipes = Seq.with(
                new Recipe() {{
                    inputItems = with(Items.sand, 3, Items.lead, 3);
                    inputPower = 40f / 60f;

                    outputItems = with(Items.metaglass, 2);

                    craftTime = 120f;

                    updateEffect = craftEffect = Fx.smeltsmoke;

                    recipeName = "Basic Metaglass Produce";
                    recipeDescription = """
                        Smelt sand and lead into metaglass.""";
                }},

                new Recipe() {{
                    inputItems = with(Items.silicon, 3, Items.lead, 3);
                    inputPower = 120f / 60f;

                    outputItems = with(Items.metaglass, 4);

                    craftTime = 180f;

                    updateEffect = craftEffect = Fx.smeltsmoke;

                    recipeName = "Sil-Metaglass Process";
                    recipeDescription = """
                        Use Refined Silicon to produce metaglass.
                        Much more efficient than normal recipe.""";
                }}
            );
        }};

        ChemicalPlantT1 = new AssemblerBlock("chemical-plant-t1"){{
            requirements(Category.crafting, with(Items.lead, 20, Items.titanium, 30, Items.graphite, 25, Items.silicon, 20));

            size = 3;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipes = Seq.with(
                new Recipe() {{
                    inputItems = with(Items.titanium, 4);
                    inputLiquids = LiquidStack.with(Liquids.oil, 15f / craftTime);
                    inputPower = 180f / 60f;

                    outputItems = with(Items.plastanium, 2);

                    craftTime = 120f;

                    updateEffect = craftEffect = Fx.smeltsmoke;

                    recipeName = "Basic Plastanium Produce";
                    recipeDescription = """
                        Plastanium.""";
                }}
            );
        }};

        /*

         FoundryT1 = new AssemblerBlock("foundry-t1"){{
            requirements(Category.crafting, with(Items.copper, 30, FFItems.bauxite, 25));

            size = 3;

            drawer = new DrawMulti(new DrawDefault(), new DrawArcFurnace());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipes = Seq.with(
                new Recipe() {{
                    InputItems = with(Items.sand, 4);
                    InputPower = 30f / 60f;

                    OutputItems = with(Items.silicon, 2);
                }}
            );
        }};

        ArcFurnace = new AssemblerBlock("arc-furnace") {{
            requirements(Category.crafting, with(Items.copper, 30, Items.beryllium, 25));

            size = 4;

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault(), new DrawArcFurnace());


            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipes = Seq.with(
                new Recipe() {{
                    InputItems = with(Items.sand, 4);
                    InputPower = 30f / 60f;

                    OutputItems = with(Items.silicon, 2);
                }}
            );
        }};

         */
    }
}
