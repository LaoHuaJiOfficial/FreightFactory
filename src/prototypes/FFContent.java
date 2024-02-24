package prototypes;

import arc.struct.Seq;
import contents.recipes.recipes;
import prototypes.recipe.Recipe;

public class FFContent {
    public static Seq<Recipe> recipeAll;

    public static void load(){
        recipeAll = new Seq<>();
    }
}
