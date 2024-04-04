package contents;

import arc.Core;
import arc.graphics.g2d.TextureRegion;

public class FFSprites {
    public static TextureRegion WeaponIcon;

    public static void init(){
        WeaponIcon = Core.atlas.find("ff-mark-pin");
    }
}
