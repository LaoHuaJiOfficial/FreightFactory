package prototypes.recipe;

import arc.graphics.Color;
import arc.util.Nullable;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.type.PayloadStack;

public class Recipe {

    public String name = "recipe-name";
    public boolean unlocked = true;

    public @Nullable ItemStack[] inputItems;
    public @Nullable ItemStack[] outputItems;
    public @Nullable LiquidStack[] inputLiquids;
    public @Nullable LiquidStack[] outputLiquids;
    public float inputHeatAmount = 0f;
    public float inputTempThreshold = 300f;
    public float outputHeatAmount = 0f;
    public float outputTempThreshold = 300f;
    public boolean isCoolant = false;
    public @Nullable PayloadStack[] inputPayloads;
    public @Nullable PayloadStack[] outputPayloads;
    public @Nullable float inputPower;
    public @Nullable float outputPower;
    public float craftTime = 60f;

    public int[] liquidOutputDirections = {-1};
    public boolean dumpExtraLiquid = true;
    public boolean ignoreLiquidFullness = false;

    public Effect craftEffect = Fx.none;
    public Effect updateEffect = Fx.none;
    public float updateEffectChance = 0.04f;
    public @Nullable Color TintColor;

    public @Nullable String recipeName;
    public @Nullable String recipeDescription;

    public boolean HasHeat() {
        return inputHeatAmount > 0 || outputHeatAmount > 0;
    }

}
