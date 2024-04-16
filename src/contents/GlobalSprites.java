package contents;

import arc.Core;
import arc.graphics.g2d.TextureRegion;

import static utilities.FFGlobalVars.ModNamePrefix;

public class GlobalSprites {
    public static TextureRegion Module;
    public static TextureRegion ArrowInput, ArrowOutput, ArrowDouble;
    public static TextureRegion WeaponIcon;
    public static TextureRegion GridOutline;

    public static TextureRegion time;

    public static void init(){
        Module = Core.atlas.find(ModNamePrefix + "module");
        ArrowInput = Core.atlas.find(ModNamePrefix + "arrow-input");
        ArrowOutput = Core.atlas.find(ModNamePrefix + "arrow-output");
        ArrowDouble = Core.atlas.find(ModNamePrefix + "arrow-double");

        WeaponIcon = Core.atlas.find(ModNamePrefix + "mark-pin");
        GridOutline = Core.atlas.find(ModNamePrefix + "grid-outline");

        time = Core.atlas.find(ModNamePrefix + "time-icon");

    }
}
