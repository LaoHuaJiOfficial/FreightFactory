package prototypes.recipe;

import arc.graphics.Color;
import arc.util.Nullable;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.entities.Effect;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.type.PayloadStack;
import prototypes.FFContent;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Recipe {

    public Recipe(String name, Boolean unlocked){
        this.name = name;

        this.unlocked = unlocked;
        this.needUnlock = !unlocked;
        FFContent.recipeAll.add(this);
    }

    public String name;
    public String[] tags;
    public boolean unlocked;
    public boolean needUnlock;
    public ItemStack[] unlockCost = ItemStack.with(Items.copper, 10);

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
    public @Nullable String recipeDetail;

    public boolean HasHeat() {
        return inputHeatAmount > 0 || outputHeatAmount > 0;
    }

    public void resetUnlock(){
        unlocked = !needUnlock;
    }

    public void write(DataOutput stream) throws IOException{
        stream.writeBoolean(unlocked);
    }

    public void read(DataInput stream, short version) throws IOException{
        if (version > 0){
            unlocked = stream.readBoolean();
        }
    }

}
