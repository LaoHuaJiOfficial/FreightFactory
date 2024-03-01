package contents;

import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.LiquidStack;
import prototypes.recipe.Recipe;

import static mindustry.type.ItemStack.with;

public class Recipes {
    public static Recipe
        graphite_0, graphite_1, graphite_2,
        silicon_0, silicon_1, silicon_2, silicon_3,
        metaglass_0, metaglass_1, metaglass_2,
        plastanium_0,
        aluminium_0, aluminium_1,
        iceCube_0,
        wheatCultivate_0, teaCultivate_0;

    public static void init(){
        graphite_0 = new Recipe("graphite-0", true) {{
            craftTime = 120f;

            inputItems = with(Items.coal, 2);
            inputPower = 0f;

            outputItems = with(Items.graphite, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        graphite_1 = new Recipe("graphite-1", false) {{
            craftTime = 120f;

            unlockCost = with(Items.graphite, 200, Items.metaglass, 200, Items.silicon, 300, Items.titanium, 100);

            inputItems = with(Items.coal, 2);
            inputLiquids = LiquidStack.with(Liquids.water, 3f / 60f);
            inputPower = 120f / 60f;

            outputItems = with(Items.graphite, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        graphite_2 = new Recipe("graphite-2", false) {{
            craftTime = 240f;

            unlockCost = with(Items.graphite, 500, Items.metaglass, 300, Items.silicon, 300, Items.titanium, 300, Items.thorium, 200, Items.plastanium, 200);

            inputItems = with(Items.coal, 2);
            inputLiquids = LiquidStack.with(Liquids.water, 3f / 60f);
            inputPower = 90f / 60f;

            outputItems = with(Items.graphite, 3);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        silicon_0 = new Recipe("silicon-0", true) {{
            craftTime = 120f;

            inputItems = with(Items.sand, 4, Items.coal, 2);
            inputPower = 30f / 60f;

            outputItems = with(Items.silicon, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        silicon_1 = new Recipe("silicon-1", false) {{
            craftTime = 240f;

            unlockCost = with(Items.graphite, 500, Items.metaglass, 400, Items.silicon, 400, Items.titanium, 200);


            inputItems = with(Items.sand, 4, Items.graphite, 1);
            inputPower = 300f / 60f;

            outputItems = with(Items.silicon, 4);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        silicon_2 = new Recipe("silicon-2", false) {{
            craftTime = 240f;

            unlockCost = with(Items.graphite, 300, Items.silicon, 800, Items.titanium, 500, FFItems.aluminium, 800, FFItems.crystalAlloy, 500);

            inputItems = with(Items.sand, 4, Items.pyratite, 1);
            inputPower = 480f / 60f;

            outputItems = with(Items.silicon, 6);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        silicon_3 = new Recipe("silicon-3", false) {{
            craftTime = 240f;

            unlockCost = with(Items.silicon, 600, Items.titanium, 800, Items.thorium, 800, Items.plastanium, 500, FFItems.aluminium, 600, FFItems.crystalAlloy, 500);

            inputItems = with(Items.sand, 4);
            inputLiquids = LiquidStack.with(Liquids.water, 12f/ 60f);
            inputPower = 720f / 60f;

            outputItems = with(Items.silicon, 6);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        metaglass_0 = new Recipe("metaglass-0", true) {{
            craftTime = 120f;

            unlockCost = with(Items.graphite, 500, Items.metaglass, 400, Items.silicon, 400, Items.titanium, 200);

            inputItems = with(Items.sand, 2, Items.lead, 2);
            inputPower = 45f / 60f;

            outputItems = with(Items.metaglass, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        metaglass_1 = new Recipe("metaglass-1", false) {{
            craftTime = 180f;

            inputItems = with(Items.silicon, 3, Items.lead, 3, Items.coal, 1);
            inputPower = 300f / 60f;

            outputItems = with(Items.metaglass, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        metaglass_2 = new Recipe("metaglass-2", false) {{
            craftTime = 180f;

            unlockCost = with(Items.graphite, 300, Items.silicon, 800, Items.titanium, 500, FFItems.aluminium, 800, FFItems.crystalAlloy, 500);

            inputItems = with(Items.sand, 3);
            inputLiquids = LiquidStack.with(Liquids.nitrogen, 6f / 60f);
            inputPower = 300f / 60f;

            outputItems = with(Items.metaglass, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        plastanium_0 = new Recipe("recipe-name", true) {{
            name = "plastanium-0";

            craftTime = 120f;

            inputItems = with(Items.titanium, 4);
            inputLiquids = LiquidStack.with(Liquids.oil, 15f / craftTime);
            inputPower = 180f / 60f;

            outputItems = with(Items.plastanium, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        aluminium_0 = new Recipe("recipe-name", true) {{
            name = "aluminium-0";

            craftTime = 120f;

            inputItems = with(FFItems.bauxite, 3);
            inputPower = 120f / 60f;

            outputItems = with(FFItems.aluminium, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        aluminium_1 = new Recipe("recipe-name", true) {{
            name = "aluminium-1";

            craftTime = 180f;

            inputItems = with(FFItems.bauxite, 3, Items.copper, 1);
            inputPower = 120f / 60f;

            outputItems = with(FFItems.aluminium, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};

        iceCube_0 = new Recipe("recipe-name", true) {{
            name = "ice-cube-0";

            craftTime = 300f;

            inputLiquids = LiquidStack.with(Liquids.water, 12f / craftTime);
            inputPower = 200f / 60f;

            outputItems = with(FFItems.iceCube, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        wheatCultivate_0 = new Recipe("recipe-name", true) {{
            craftTime = 120f;

            inputLiquids = LiquidStack.with(Liquids.water, 12f / 60f);
            inputPower = 40f / 60f;

            outputItems = with(FFItems.leaf, 2);

            craftEffect = Fx.smeltsmoke;

        }};
        teaCultivate_0 = new Recipe("recipe-name", true) {{

            craftTime = 180f;

            inputLiquids = LiquidStack.with(Liquids.water, 10f / 60f);
            inputPower = 30f / 60f;

            outputItems = with(FFItems.wheat, 3);

            craftEffect = Fx.smeltsmoke;
        }};
    }
}
