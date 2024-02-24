package prototypes;

import arc.struct.Seq;
import prototypes.recipe.Recipe;

public class FFVars {
    public static FFGameData gameData;

    public static void load(){
        gameData = new FFGameData();

        FFContent.load();
    }
    public static void init(){
    }
}
