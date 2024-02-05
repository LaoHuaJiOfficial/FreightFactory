package prototypes.recipe;

import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.type.PayloadStack;

public class Recipe {
    public ItemStack[] inputItems;
    public ItemStack[] outputItems;
    public LiquidStack[] inputLiquids;
    public LiquidStack[] outputLiquids;
    public PayloadStack[] inputPayloads;
    public PayloadStack[] outputPayloads;
    public float inputPower;
    public float outputPower;
    public float craftTime = 60f;
    public String recipeName;
    public String recipeDescription;
}
