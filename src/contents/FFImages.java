package contents;

import arc.Core;
import arc.graphics.g2d.TextureRegion;

public class FFImages {
    public static TextureRegion time;
    public static void load(){
        time = Core.atlas.find("FF-time-icon");
    }
}
