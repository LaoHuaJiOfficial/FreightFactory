import arc.Core;
import arc.Events;
import arc.func.Cons;
import arc.input.KeyCode;
import arc.util.Log;
import arc.util.Time;
import contents.*;
import contents.FFBullets;
import contents.Recipes;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.gen.Call;
import mindustry.mod.Mod;
import prototypes.FFContent;
import prototypes.FFVars;
import prototypes.recipe.Recipe;
import utilities.game.FListener;
import utilities.game.FVanillaChange;

public class ModMain extends Mod {

    public ModMain() {
        Log.info("FF Loaded.");


        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            Time.runTask(10f, () -> {
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
        //Highest load priority
        FFSounds.load();
        FFItems.load();
        FFLiquids.load();

        FFVars.load();

        Recipes.init();
        FFBullets.load();

        FFBlock.load();
        FFPlanet.load();

        FListener FListener = new FListener();
        FListener.init();

        FVanillaChange.init();
    }

}
