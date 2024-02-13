package contents.blocks;

import arc.struct.Seq;
import contents.FFItems;
import contents.recipes.recipes;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.BufferedItemBridge;
import mindustry.world.draw.*;
import prototypes.block.production.AreaDrill;
import prototypes.block.production.AssemblerBlock;

import static mindustry.type.ItemStack.with;

public class ProductionBlock {
    public static Block
        //Production
        ResonanceDrill, Greenhouse,
        //Factories
        AluminiumFoundry, GraphiteCompressor, SiliconSmelter, MetaglassKiln, ChemicalPlant,
        Freezer;

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
                recipes.graphite_0,
                recipes.graphite_1
            );
        }};

        SiliconSmelter = new AssemblerBlock("silicon-smelter"){{
            requirements(Category.crafting, with(Items.copper, 30, Items.graphite, 25));

            size = 3;

            drawer = new DrawMulti(new DrawRegion("-base"), new DrawArcSmelt(), new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                recipes.silicon_0,
                recipes.silicon_1
            );
        }};

        MetaglassKiln = new AssemblerBlock("metaglass-kiln"){{
            requirements(Category.crafting, with(Items.copper, 30, Items.graphite, 25));

            size = 3;

            drawer = new DrawMulti(new DrawRegion("-base"), new DrawCrucibleFlame(), new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                recipes.metaglass_0,
                recipes.metaglass_1
            );
        }};

        ChemicalPlant = new AssemblerBlock("chemical-plant"){{
            requirements(Category.crafting, with(Items.lead, 20, Items.titanium, 30, Items.graphite, 25, Items.silicon, 20));

            size = 3;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                recipes.plastanium_0
            );
        }};

        AluminiumFoundry = new AssemblerBlock("aluminium-foundry"){{
            requirements(Category.crafting, with(Items.copper, 30, FFItems.bauxite, 25));

            size = 3;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                recipes.aluminium_0,
                recipes.aluminium_1
            );
        }};

        Freezer = new AssemblerBlock("freezer"){{
            requirements(Category.crafting, with(Items.copper, 30));

            size = 2;

            drawer = new DrawMulti(new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            recipeSeq = Seq.with(
                recipes.iceCube_0
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
