import arc.Core;
import arc.Events;
import arc.util.Log;
import arc.util.Time;
import contents.*;
import contents.recipes.recipes;
import mindustry.content.Blocks;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.mod.Mod;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.BufferedItemBridge;
import mindustry.world.blocks.distribution.ItemBridge;
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
            Time.runTask(10f, () -> {
                //RecipePlannerDialog recipeDialog = new RecipePlannerDialog();
                //recipeDialog.show();
            });

        });

    }

    @Override
    public void loadContent() {

        //Highest load priority
        FFSounds.load();
        FFItems.load();

        recipes.load();

        FFBlock.load();
        FFPlanet.load();

        FListener FListener = new FListener();
        FListener.init();

        FVanillaChange.init();

    }

}
