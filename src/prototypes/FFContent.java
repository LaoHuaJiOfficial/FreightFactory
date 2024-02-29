package prototypes;

import arc.struct.Seq;
import prototypes.recipe.Recipe;

public class FFContent {
    public static Seq<Recipe> recipeAll;

    public static void load(){
        recipeAll = new Seq<>();
    }
}
