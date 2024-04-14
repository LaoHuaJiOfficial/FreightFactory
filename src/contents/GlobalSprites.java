package contents;

import arc.Core;
import arc.graphics.g2d.TextureRegion;

import static utilities.FFGlobalVars.ModNamePrefix;

public class GlobalSprites {
    public static TextureRegion Module;
    public static TextureRegion ArrowInput, ArrowOutput, ArrowDouble;
    public static void init(){
        Module = Core.atlas.find(ModNamePrefix + "module");
        ArrowInput = Core.atlas.find(ModNamePrefix + "arrow-input");
        ArrowOutput = Core.atlas.find(ModNamePrefix + "arrow-output");
        ArrowDouble = Core.atlas.find(ModNamePrefix + "arrow-double");
    }
}
