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
        FoundryT1, CompressorT1,
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
                    InputLiquids = LiquidStack.with(Liquids.water, 12f / 60f);
                    InputPower = 40f / 60f;

                    OutputItems = with(FFItems.leaf, 2);

                    CraftTime = 120f;

                    craftEffect = Fx.smeltsmoke;
                }},

                new Recipe() {{
                    InputLiquids = LiquidStack.with(Liquids.water, 10f / 60f);
                    InputPower = 30f / 60f;

                    OutputItems = with(FFItems.wheat, 3);

                    CraftTime = 180f;

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
                    InputItems = with(Items.coal, 4);
                    InputPower = 30f / 60f;

                    OutputItems = with(Items.graphite, 2);

                    CraftTime = 120f;

                    updateEffect = craftEffect = Fx.smeltsmoke;
                }},

                new Recipe() {{
                    InputItems = with(Items.coal, 6);
                    InputLiquids = LiquidStack.with(Liquids.water, 4f / 60f);
                    InputPower = 50f / 60f;

                    OutputItems = with(Items.graphite, 4);

                    CraftTime = 90f;

                    updateEffect = craftEffect = Fx.smeltsmoke;

                }}

            );
        }};

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
