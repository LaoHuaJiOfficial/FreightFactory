package contents.blocks;

import contents.FFItems;
import mindustry.world.Block;
import mindustry.world.blocks.environment.OreBlock;

public class EnvironmentBlock {
    public static Block oreAluminium;

    public static void load(){
        oreAluminium = new OreBlock(FFItems.bauxite) {{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};
    }
}
