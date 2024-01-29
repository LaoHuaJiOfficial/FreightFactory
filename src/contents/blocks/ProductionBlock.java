package contents.blocks;

import arc.struct.Seq;
import contents.FFItems;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.Liquid;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawLiquidRegion;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawRegion;
import prototypes.block.HeatBox.BlockF;
import prototypes.block.production.AreaDrill;
import prototypes.block.production.AssemblerBlock;
import prototypes.drawer.DrawArcFurnace;

import static mindustry.type.ItemStack.with;

public class ProductionBlock {
    public static Block
        //Production
        ResonanceDrill, Greenhouse,
        //Factories
        AluminiumFoundry,
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

            drawer = new DrawMulti(new DrawRegion("-base"), new DrawLiquidRegion(), new DrawDefault());

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.09f;

            hasLiquids = true;

            recipes = Seq.with(
                new Recipe() {{
                    InputLiquids = LiquidStack.with(Liquids.water, 12f / 60f);
                    InputPower = 80f / 60f;

                    OutputItems = with(FFItems.leaf, 2);

                    CraftTime = 120f;

                    craftEffect = Fx.smeltsmoke;
                }}
            );
        }};

        AluminiumFoundry = new AssemblerBlock("aluminium-foundry"){{
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
