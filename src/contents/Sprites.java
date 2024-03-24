package contents;

import arc.Core;
import arc.graphics.g2d.TextureRegion;

public class Sprites {
    public static TextureRegion Module;
    public static void load(){
        Module = Core.atlas.find("ff-module");
    }
}
