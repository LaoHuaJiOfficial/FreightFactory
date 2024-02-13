package contents.recipes;

import contents.FFItems;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.LiquidStack;
import prototypes.recipe.Recipe;

import static mindustry.type.ItemStack.with;

public class recipes {
    public static Recipe
        graphite_0, graphite_1, silicon_0, silicon_1, metaglass_0, metaglass_1,
        plastanium_0, aluminium_0, aluminium_1, iceCube_0,

        wheatCultivate_0, teaCultivate_0;

    public static void load(){
        wheatCultivate_0 = new Recipe() {{
            craftTime = 120f;

            inputLiquids = LiquidStack.with(Liquids.water, 12f / 60f);
            inputPower = 40f / 60f;

            outputItems = with(FFItems.leaf, 2);

            craftEffect = Fx.smeltsmoke;

        }};

        teaCultivate_0 = new Recipe() {{

            craftTime = 180f;

            inputLiquids = LiquidStack.with(Liquids.water, 10f / 60f);
            inputPower = 30f / 60f;

            outputItems = with(FFItems.wheat, 3);

            craftEffect = Fx.smeltsmoke;
        }};

        graphite_0 = new Recipe() {{
            name = "graphite-0";

            craftTime = 120f;

            inputItems = with(Items.coal, 4);
            inputPower = 30f / 60f;

            outputItems = with(Items.graphite, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        graphite_1 = new Recipe() {{
            name = "graphite-1";

            craftTime = 180f;

            inputItems = with(Items.coal, 4);
            inputLiquids = LiquidStack.with(Liquids.water, 4f / 60f);
            inputPower = 90f / 60f;

            outputItems = with(Items.graphite, 4);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        silicon_0 = new Recipe() {{
            name = "silicon-0";

            craftTime = 120f;

            inputItems = with(Items.sand, 4, Items.coal, 1);
            inputPower = 40f / 60f;

            outputItems = with(Items.silicon, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        silicon_1 = new Recipe() {{
            name = "silicon-1";

            craftTime = 120f;

            inputItems = with(Items.sand, 4, Items.graphite, 1);
            inputPower = 300f / 60f;

            outputItems = with(Items.silicon, 4);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        metaglass_0 = new Recipe() {{
            name = "metaglass-0";

            craftTime = 120f;

            inputItems = with(Items.sand, 2, Items.lead, 2);
            inputPower = 40f / 60f;

            outputItems = with(Items.metaglass, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        metaglass_1 = new Recipe() {{
            name = "metaglass-1";

            craftTime = 300f;

            inputItems = with(Items.silicon, 2, Items.lead, 1);
            inputPower = 300f / 60f;

            outputItems = with(Items.metaglass, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        plastanium_0 = new Recipe() {{
            name = "plastanium-0";

            craftTime = 120f;

            inputItems = with(Items.titanium, 4);
            inputLiquids = LiquidStack.with(Liquids.oil, 15f / craftTime);
            inputPower = 180f / 60f;

            outputItems = with(Items.plastanium, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        aluminium_0 = new Recipe() {{
            name = "aluminium-0";

            craftTime = 120f;

            inputItems = with(FFItems.bauxite, 3);
            inputPower = 120f / 60f;

            outputItems = with(FFItems.aluminium, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        aluminium_1 = new Recipe() {{
            name = "aluminium-1";

            craftTime = 180f;

            inputItems = with(FFItems.bauxite, 3, Items.copper, 1);
            inputPower = 120f / 60f;

            outputItems = with(FFItems.aluminium, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        iceCube_0 = new Recipe() {{
            name = "ice-cube-0";

            craftTime = 300f;

            inputLiquids = LiquidStack.with(Liquids.water, 12f / craftTime);
            inputPower = 200f / 60f;

            outputItems = with(FFItems.iceCube, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
    }
}
