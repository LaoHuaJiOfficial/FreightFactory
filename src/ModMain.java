import arc.Core;
import arc.Events;
import arc.util.Log;
import arc.util.Time;
import contents.*;
import extra.FListener;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.mod.Mod;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.ui.fragments.HudFragment;
import ui.PlacementFragmentF;

import java.lang.reflect.Field;

public class ModMain extends Mod {

    public ModMain() {
        Log.info("Loaded ExampleJavaMod constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("EmperorXi").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("FoodFactory-XI")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });

        });


        Events.on(ClientLoadEvent.class, e -> {
            Core.app.post(FReflect::init);
        });
    }

    @Override
    public void loadContent() {

        CoolantLiquid.load();

        //Highest load priority
        FFSounds.load();
        FFItems.load();
        FFBlock.load();
        FFPlanet.load();
        Turrets.load();
        FFUnitTypes.load();
        //Log.info("Loading some example content.");

        FListener FListener = new FListener();
        FListener.init();
    }

}
