package contents.blocks;

import arc.struct.Seq;
import contents.FFItems;
import contents.Recipes;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.draw.*;
import prototypes.FFContent;
import prototypes.block.production.AreaDrill;
import prototypes.block.production.AssemblerBlock;

import static mindustry.type.ItemStack.with;

public class ProductionBlock {
    public static Block
        //Production
        ResonanceDrill, Greenhouse,
        //Factories
        GraphiteCompressor, SiliconSmelter, MetaglassKiln, ChemicalPlant,
        AluminiumFoundry, SurgeAlloyFurnace, PhaseWeaver, BiomassExtractor, CrystallizationFacility,
        BakingStation, Freezer, FermentChamber, CanningFacility, FluidMixer, Grinder, FryingMachine, FoodCompressor,
        test;

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

            recipeSeq = Seq.with(

            );
        }};

        GraphiteCompressor = new AssemblerBlock("graphite-compressor"){{
            requirements(Category.crafting, with(Items.copper, 30, FFItems.bauxite, 25));

            size = 3;

            //drawer = new DrawMulti(new DrawDefault(), new DrawArcFurnace());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.graphite_0,
                Recipes.graphite_1,
                Recipes.graphite_2
            );
        }};

        SiliconSmelter = new AssemblerBlock("silicon-smelter"){{
            requirements(Category.crafting, with(Items.copper, 30, Items.graphite, 25));

            size = 3;

            drawer = new DrawMulti(new DrawRegion("-base"), new DrawArcSmelt(), new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.silicon_0,
                Recipes.silicon_1,
                Recipes.silicon_2,
                Recipes.silicon_3
            );
        }};

        MetaglassKiln = new AssemblerBlock("metaglass-kiln"){{
            requirements(Category.crafting, with(Items.copper, 30, Items.graphite, 25));

            size = 3;

            drawer = new DrawMulti(new DrawRegion("-base"), new DrawCrucibleFlame(), new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.metaglass_0,
                Recipes.metaglass_1,
                Recipes.metaglass_2
            );
        }};

        ChemicalPlant = new AssemblerBlock("chemical-plant"){{
            requirements(Category.crafting, with(Items.lead, 20, Items.titanium, 30, Items.graphite, 25, Items.silicon, 20));

            size = 3;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.plastanium_0,
                Recipes.plastanium_1,
                Recipes.plastanium_2,
                Recipes.plastanium_3
            );
        }};

        AluminiumFoundry = new AssemblerBlock("aluminium-foundry"){{
            requirements(Category.crafting, with(Items.copper, 30, FFItems.bauxite, 25));

            size = 3;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.aluminium_0,
                Recipes.aluminium_1
            );
        }};
        SurgeAlloyFurnace = new AssemblerBlock("surge-alloy-furnace"){{
            requirements(Category.crafting, with(Items.copper, 30, FFItems.bauxite, 25));

            size = 4;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.surgeAlloy_0,
                Recipes.surgeAlloy_1,
                Recipes.surgeAlloy_2,
                Recipes.surgeAlloy_3
            );
        }};
        PhaseWeaver = new AssemblerBlock("phase-weaver"){{
            requirements(Category.crafting, with(Items.copper, 30, FFItems.bauxite, 25));

            size = 3;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.phaseWeaver_0,
                Recipes.phaseWeaver_1,
                Recipes.phaseWeaver_2
            );
        }};
        BiomassExtractor = new AssemblerBlock("biomass-extractor"){{
            requirements(Category.crafting, with(Items.copper, 30, FFItems.bauxite, 25));

            size = 3;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.biomass_0,
                Recipes.biomass_1,
                Recipes.biomass_2,
                Recipes.biomass_3
            );
        }};
        CrystallizationFacility = new AssemblerBlock("crystallization-facility"){{
            requirements(Category.crafting, with(Items.copper, 30, FFItems.bauxite, 25));

            size = 3;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.crystalAlloy_0,
                Recipes.crystalAlloy_1
            );
        }};
        BakingStation = new AssemblerBlock("baking-station"){{
            requirements(Category.crafting, with(Items.copper, 30));

            size = 2;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.iceCube_0
            );
        }};
        Freezer = new AssemblerBlock("freezer"){{
            requirements(Category.crafting, with(Items.copper, 30));

            size = 2;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.iceCube_0,
                Recipes.iceCube_1,
                Recipes.iceCube_2,
                Recipes.iceCube_3
            );
        }};
        FermentChamber = new AssemblerBlock("ferment-chamber"){{
            requirements(Category.crafting, with(Items.copper, 30));

            size = 2;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.ferment_0,
                Recipes.ferment_1
            );
        }};
        CanningFacility = new AssemblerBlock("canning-facility"){{
            requirements(Category.crafting, with(Items.copper, 30));

            size = 2;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.canning_0,
                Recipes.canning_1,
                Recipes.canning_2
            );
        }};
        FluidMixer = new AssemblerBlock("fluid-mixer"){{
            requirements(Category.crafting, with(Items.copper, 30));

            size = 3;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.fluidMix_0,
                Recipes.teaMix_0,
                Recipes.teaMix_1
            );
        }};
        Grinder = new AssemblerBlock("grinder"){{
            requirements(Category.crafting, with(Items.copper, 30));

            size = 2;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.grind_0,
                Recipes.grind_1
            );
        }};
        FryingMachine = new AssemblerBlock("frying-machine"){{
            requirements(Category.crafting, with(Items.copper, 30));

            size = 3;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.frying_0
            );
        }};
        FoodCompressor = new AssemblerBlock("food-compressor"){{
            requirements(Category.crafting, with(Items.copper, 30));

            size = 2;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                Recipes.compression_0,
                Recipes.compression_1
            );
        }};
        test = new AssemblerBlock("test"){{
            requirements(Category.crafting, with(Items.copper, 30));

            size = 2;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                FFContent.recipeAll
            );
        }};
        /*



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
