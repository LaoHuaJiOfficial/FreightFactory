import arc.Events;
import arc.util.Log;
import arc.util.Time;
import contents.*;
import contents.FFBullets;
import contents.recipes.recipes;
import mindustry.game.EventType;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.mod.Mod;
import prototypes.FFContent;
import prototypes.FFVars;
import prototypes.recipe.Recipe;
import prototypes.ui.dialog.RecipePlannerDialog;
import prototypes.ui.dialog.RecipeResearchDialog;
import utilities.game.FListener;
import utilities.game.FVanillaChange;

public class ModMain extends Mod {

    public ModMain() {
        Log.info("FF Loaded.");


        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            Time.runTask(10f, () -> {
                //RecipeResearchDialog dialog = new RecipeResearchDialog();
                //dialog.show();
            });
        });

    }
    @Override
    public void init(){
        Events.on(EventType.WorldLoadEvent.class, e -> {
            for (Recipe recipe: FFContent.recipeAll){
                recipe.resetUnlock();
            }
            Log.info("reset");
        });
    }

    @Override
    public void loadContent() {
        FFVars.load();

        //Highest load priority
        FFSounds.load();
        FFItems.load();
        //FFLiquids

        recipes.init();
        FFBullets.load();

        FFBlock.load();
        FFPlanet.load();

        FListener FListener = new FListener();
        FListener.init();

        FVanillaChange.init();
    }

}
