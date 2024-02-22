import arc.Events;
import arc.util.Log;
import arc.util.Time;
import contents.*;
import contents.FFBullets;
import contents.recipes.recipes;
import mindustry.game.EventType;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.mod.Mod;
import utilities.game.FListener;
import utilities.game.FVanillaChange;
import utilities.ui.dialog.RecipePlannerDialog;

public class ModMain extends Mod {

    public ModMain() {
        Log.info("FF Loaded.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //Vars.renderer.minZoom = 1f;
            //Vars.renderer.maxZoom = 20f;

            //show dialog upon startup


            Events.on(EventType.WorldLoadEvent.class, a -> {

            });

            Time.runTask(10f, () -> {
                //RecipePlannerDialog dialog = new RecipePlannerDialog();
                //dialog.show();
            });
        });

    }

    @Override
    public void loadContent() {

        //Highest load priority
        FFSounds.load();
        FFItems.load();
        //FFLiquids

        recipes.load();
        FFBullets.load();

        FFBlock.load();
        FFPlanet.load();

        FListener FListener = new FListener();
        FListener.init();

        FVanillaChange.init();

    }

}
