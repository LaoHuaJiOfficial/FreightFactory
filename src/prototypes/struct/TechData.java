package prototypes.struct;

import arc.graphics.g2d.TextureRegion;
import arc.struct.Seq;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;

public class TechData {
    public String name;
    public TextureRegion icon;

    public Seq<ItemStack> requireItems;
    public Seq<LiquidStack> requireLiquids;
    public Seq<TechDataStack> requireTechs;
}
