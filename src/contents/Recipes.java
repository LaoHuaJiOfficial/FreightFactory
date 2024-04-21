package contents;

import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.LiquidStack;
import prototypes.recipe.Recipe;

import static mindustry.type.ItemStack.with;

public class Recipes {
    public static Recipe
        copperOreSmelt_0,
        graphite_0, graphite_1, graphite_2,
        silicon_0, silicon_1, silicon_2, silicon_3,
        metaglass_0, metaglass_1, metaglass_2, metaglass_3,
        plastanium_0, plastanium_1, plastanium_2, plastanium_3,
        aluminium_0, aluminium_1,
        surgeAlloy_0, surgeAlloy_1, surgeAlloy_2, surgeAlloy_3,
        phaseWeaver_0, phaseWeaver_1, phaseWeaver_2,
        biomass_0, biomass_1, biomass_2, biomass_3,
        crystalAlloy_0, crystalAlloy_1,
        baking_0,
        iceCube_0, iceCube_1, iceCube_2, iceCube_3,
        ferment_0, ferment_1,
        canning_0, canning_1, canning_2,
        fluidMix_0,
        grind_0, grind_1,
        teaMix_0, teaMix_1,
        frying_0,
        compression_0, compression_1,
        wheatCultivate_0, teaCultivate_0;

    public static void init(){
        copperOreSmelt_0 = new Recipe("copper-ore-smelt_0", true){{
            tags = new String[]{"basic-smelt"};

            craftTime = 120f;

            inputItems = with(FFItems.rawCopper, 1);
            inputPower = 0f;

            outputItems = with(FFItems.copperIngot, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
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
            inputLiquids = LiquidStack.with(Liquids.nitrogen, 3f / 60f);
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

            inputItems = with(Items.sand, 4, Items.coal, 1, Items.pyratite, 1);
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
        metaglass_3 = new Recipe("metaglass-3", false) {{
            craftTime = 300f;

            unlockCost = with(Items.silicon, 800, Items.titanium, 500, FFItems.aluminium, 800, FFItems.crystalAlloy, 500);

            inputItems = with(Items.silicon, 2);
            inputLiquids = LiquidStack.with(Liquids.ozone, 8f / 60f);
            inputPower = 1200f / 60f;

            outputItems = with(Items.metaglass, 3);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        plastanium_0 = new Recipe("plastanium-0", true) {{
            craftTime = 120f;

            inputItems = with(Items.titanium, 4);
            inputLiquids = LiquidStack.with(Liquids.oil, 15f / craftTime);
            inputPower = 180f / 60f;

            outputItems = with(Items.plastanium, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        plastanium_1 = new Recipe("plastanium-1", false) {{
            craftTime = 120f;

            inputItems = with(Items.titanium, 4);
            inputLiquids = LiquidStack.with(Liquids.arkycite, 10f / craftTime);
            inputPower = 180f / 60f;

            outputItems = with(Items.plastanium, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        plastanium_2 = new Recipe("plastanium-2", false) {{
            craftTime = 240f;

            inputItems = with(Items.titanium, 2);
            inputLiquids = LiquidStack.with(Liquids.oil, 8f / craftTime, Liquids.hydrogen, 2 / craftTime);
            inputPower = 240f / 60f;

            outputItems = with(Items.plastanium, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        plastanium_3 = new Recipe("plastanium-3", false) {{
            craftTime = 600f;

            inputItems = with(FFItems.crystalAlloy, 1);
            inputLiquids = LiquidStack.with(Liquids.oil, 8f / craftTime, Liquids.hydrogen, 8f / craftTime);
            inputPower = 900f / 60f;

            outputItems = with(Items.plastanium, 2, FFItems.aluminium, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        aluminium_0 = new Recipe("aluminium-0", true) {{
            craftTime = 120f;

            inputItems = with(FFItems.bauxite, 3);
            inputPower = 120f / 60f;

            outputItems = with(FFItems.aluminium, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        aluminium_1 = new Recipe("aluminium-1", false) {{
            craftTime = 180f;

            unlockCost = with(Items.silicon, 800, Items.titanium, 500, FFItems.aluminium, 800, FFItems.crystalAlloy, 500);

            inputItems = with(FFItems.bauxite, 3, Items.copper, 1);
            inputPower = 120f / 60f;

            outputItems = with(FFItems.aluminium, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        surgeAlloy_0 = new Recipe("surge-alloy-0", true) {{
            craftTime = 180f;

            inputItems = with(Items.copper, 3, Items.lead, 3, Items.silicon, 3, Items.titanium, 3);
            inputPower = 120f / 60f;

            outputItems = with(Items.surgeAlloy, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        surgeAlloy_1 = new Recipe("surge-alloy-1", false) {{
            craftTime = 180f;

            unlockCost = with(Items.silicon, 800, Items.titanium, 500, FFItems.aluminium, 800, FFItems.crystalAlloy, 500);

            inputItems = with(Items.titanium, 1, Items.silicon, 2);
            inputLiquids = LiquidStack.with(FFLiquids.mineralFluid, 10f / craftTime);
            inputPower = 120f / 60f;

            outputItems = with(Items.surgeAlloy, 1, Items.scrap, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        surgeAlloy_2 = new Recipe("surge-alloy-2", false) {{
            craftTime = 180f;

            unlockCost = with(Items.silicon, 800, Items.titanium, 500, FFItems.aluminium, 800, FFItems.crystalAlloy, 500);

            inputItems = with(Items.silicon, 2, FFItems.crystalAlloy, 2);
            inputPower = 300f / 60f;

            outputItems = with(Items.surgeAlloy, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        surgeAlloy_3 = new Recipe("surge-alloy-3", false) {{
            craftTime = 180f;

            unlockCost = with(Items.silicon, 800, Items.titanium, 500, FFItems.aluminium, 800, FFItems.crystalAlloy, 500);

            inputItems = with(Items.titanium, 2, Items.silicon, 2);
            inputLiquids = LiquidStack.with(FFLiquids.mineralFluid, 10f / craftTime);
            inputPower = 120f / 60f;

            outputItems = with(Items.surgeAlloy, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        phaseWeaver_0 = new Recipe("phase-fabric-0", true) {{
            craftTime = 300f;

            inputItems = with(Items.sand, 6, Items.thorium, 4);
            inputLiquids = LiquidStack.with(Liquids.water, 12f / craftTime);
            inputPower = 300f / 60f;

            outputItems = with(Items.phaseFabric, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        phaseWeaver_1 = new Recipe("phase-fabric-1", false) {{
            craftTime = 300f;

            unlockCost = with(Items.silicon, 800, Items.titanium, 500, FFItems.aluminium, 800, FFItems.crystalAlloy, 500);

            inputItems = with(Items.silicon, 4, Items.thorium, 6);
            inputLiquids = LiquidStack.with(Liquids.water, 12f / craftTime);
            inputPower = 300f / 60f;

            outputItems = with(Items.phaseFabric, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        phaseWeaver_2 = new Recipe("phase-fabric-2", false) {{
            craftTime = 300f;

            unlockCost = with(Items.silicon, 800, Items.titanium, 500, FFItems.aluminium, 800, FFItems.crystalAlloy, 500);

            inputItems = with(Items.silicon, 3, FFItems.cannedNucola, 3);
            inputLiquids = LiquidStack.with(Liquids.water, 12f / craftTime);
            inputPower = 300f / 60f;

            outputItems = with(Items.phaseFabric, 3);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        biomass_0 = new Recipe("biomass-0", true) {{
            craftTime = 300f;

            inputItems = with(FFItems.wheat, 2);
            inputLiquids = LiquidStack.with(Liquids.water, 12f / craftTime);
            inputPower = 300f / 60f;

            outputLiquids = LiquidStack.with(FFLiquids.syrup, 6 / craftTime);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        biomass_1 = new Recipe("biomass-1", true) {{
            craftTime = 300f;

            inputItems = with(Items.sporePod, 2);
            inputPower = 300f / 60f;

            outputLiquids = LiquidStack.with(Liquids.oil, 6 / craftTime);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        biomass_2 = new Recipe("biomass-2", false) {{
            craftTime = 300f;

            unlockCost = with(Items.silicon, 800, Items.titanium, 500, FFItems.aluminium, 800, FFItems.crystalAlloy, 500);

            inputItems = with(Items.graphite, 1);
            inputLiquids = LiquidStack.with(Liquids.arkycite, 12f / craftTime);
            inputPower = 300f / 60f;

            outputLiquids = LiquidStack.with(Liquids.oil, 15 / craftTime);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        biomass_3 = new Recipe("biomass-3", false) {{
            craftTime = 300f;

            unlockCost = with(Items.silicon, 800, Items.titanium, 500, FFItems.aluminium, 800, FFItems.crystalAlloy, 500);

            inputLiquids = LiquidStack.with(Liquids.water, 12 / craftTime, Liquids.neoplasm, 8 / craftTime, FFLiquids.syrup, 6 / craftTime);
            inputPower = 300f / 60f;

            outputItems = with(Items.dormantCyst, 2);
            outputLiquids = LiquidStack.with(Liquids.arkycite, 60 / craftTime);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        crystalAlloy_0 = new Recipe("crystal-alloy-0", true) {{
            craftTime = 180f;

            inputItems = with(Items.titanium, 2, FFItems.aluminium, 2);
            inputPower = 120f / 60f;

            outputItems = with(FFItems.crystalAlloy, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        crystalAlloy_1 = new Recipe("crystal-alloy-1", false) {{
            craftTime = 240f;

            inputItems = with(Items.titanium, 3, FFItems.aluminium, 3, FFItems.iceCube, 2);
            inputPower = 120f / 60f;

            outputItems = with(FFItems.crystalAlloy, 2);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        baking_0 = new Recipe("baking-0", true) {{
            craftTime = 10f;

            inputItems = with(FFItems.flour, 1);
            inputLiquids = LiquidStack.with(FFLiquids.syrup, 4 / craftTime);
            inputPower = 120f / 60f;

            outputItems = with(FFItems.bread, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        ferment_0 = new Recipe("ferment-0", true) {{
            craftTime = 120f;

            inputItems = with(FFItems.leaf, 1);
            inputLiquids = LiquidStack.with(Liquids.ozone, 4 / craftTime);
            inputPower = 120f / 60f;

            outputItems = with(FFItems.fermentedTea, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        ferment_1 = new Recipe("ferment-1", true) {{
            craftTime = 120f;

            inputItems = with(FFItems.wheat, 2);
            inputLiquids = LiquidStack.with(Liquids.ozone, 6 / craftTime);
            inputPower = 120f / 60f;

            outputLiquids = LiquidStack.with(Liquids.arkycite, 12 / craftTime);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        iceCube_0 = new Recipe("ice-cube-0", true) {{
            craftTime = 120f;

            inputLiquids = LiquidStack.with(Liquids.water, 12f / craftTime);
            inputPower = 200f / 60f;

            outputItems = with(FFItems.iceCube, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        iceCube_1 = new Recipe("ice-cube-1", true) {{
            craftTime = 120f;

            inputLiquids = LiquidStack.with(FFLiquids.greenTea, 12f / craftTime);
            inputPower = 200f / 60f;

            outputItems = with(FFItems.teaIceCube, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        iceCube_2 = new Recipe("ice-cube-2", true) {{
            craftTime = 120f;

            inputLiquids = LiquidStack.with(FFLiquids.blackTea, 12f / craftTime);
            inputPower = 200f / 60f;

            outputItems = with(FFItems.blackTeaIceCube, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        iceCube_3 = new Recipe("ice-cube-3", true) {{
            craftTime = 120f;

            inputLiquids = LiquidStack.with(FFLiquids.cola, 12f / craftTime);
            inputPower = 200f / 60f;

            outputItems = with(FFItems.colaIceCube, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        canning_0 = new Recipe("canning-0", true) {{
            craftTime = 120f;

            inputItems = with(FFItems.leaf, 3, FFItems.aluminium, 1);
            inputPower = 200f / 60f;

            outputItems = with(FFItems.tinTea, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        canning_1 = new Recipe("canning-1", true) {{
            craftTime = 120f;

            inputItems = with(FFItems.aluminium, 1);
            inputLiquids = LiquidStack.with(FFLiquids.cola, 12f / craftTime);
            inputPower = 200f / 60f;

            outputItems = with(FFItems.cannedCola, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        canning_2 = new Recipe("canning-2", true) {{
            craftTime = 120f;

            inputItems = with(Items.thorium, 1, FFItems.aluminium, 1);
            inputLiquids = LiquidStack.with(FFLiquids.cola, 12f / craftTime);
            inputPower = 200f / 60f;

            outputItems = with(FFItems.cannedNucola, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        fluidMix_0 = new Recipe("fluid-mix-0", true) {{
            craftTime = 120f;

            inputLiquids = LiquidStack.with(Liquids.water, 6f / craftTime, FFLiquids.syrup, 4f / craftTime);
            inputPower = 200f / 60f;

            outputLiquids = LiquidStack.with(FFLiquids.cola, 6f / craftTime);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        grind_0 = new Recipe("grind-0", true) {{
            craftTime = 120f;

            inputItems = with(Items.scrap, 1);
            inputPower = 200f / 60f;

            outputItems = with(Items.sand, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        grind_1 = new Recipe("grind-1", true) {{
            craftTime = 120f;

            inputItems = with(FFItems.wheat, 1);
            inputPower = 200f / 60f;

            outputItems = with(FFItems.flour, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        teaMix_0 = new Recipe("tea-mix-0", true) {{
            craftTime = 120f;

            inputItems = with(FFItems.leaf, 2);
            inputLiquids = LiquidStack.with(Liquids.water, 10f / craftTime);
            inputPower = 200f / 60f;

            outputLiquids = LiquidStack.with(FFLiquids.greenTea, 12f / craftTime);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        teaMix_1 = new Recipe("tea-mix-1", true) {{
            craftTime = 120f;

            inputItems = with(FFItems.fermentedTea, 2);
            inputLiquids = LiquidStack.with(Liquids.water, 10f / craftTime);
            inputPower = 200f / 60f;

            outputLiquids = LiquidStack.with(FFLiquids.blackTea, 12f / craftTime);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        frying_0 = new Recipe("frying-0", true) {{
            craftTime = 120f;

            inputItems = with(Items.thorium, 2, FFItems.flour, 2);
            inputLiquids = LiquidStack.with(FFLiquids.syrup, 8f / craftTime);
            inputPower = 200f / 60f;

            outputItems = with(FFItems.radiantCake, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        compression_0 = new Recipe("compression-0", true) {{
            craftTime = 120f;

            inputLiquids = LiquidStack.with(FFLiquids.syrup, 8f / craftTime);
            inputPower = 200f / 60f;

            outputItems = with(FFItems.hardCandy, 1);

            updateEffect = craftEffect = Fx.smeltsmoke;
        }};
        compression_1 = new Recipe("compression-1", true) {{
            craftTime = 120f;

            inputItems = with(Items.thorium, 2, FFItems.multiCompound, 2, FFItems.blackTeaIceCube, 4);
            inputLiquids = LiquidStack.with(FFLiquids.syrup, 12f / craftTime);
            inputPower = 200f / 60f;

            outputItems = with(FFItems.rainbowCandy, 1);

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
