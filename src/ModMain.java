import arc.Core;
import arc.Events;
import arc.util.Log;
import arc.util.Time;
import contents.FFBlock;
import contents.FFItems;
import contents.FFPlanet;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.mod.Mod;
import mindustry.ui.dialogs.BaseDialog;

public class ModMain extends Mod {

    public ModMain() {
        Log.info("Loaded ExampleJavaMod constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("example-java-mod-frog")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent() {
        FFItems.load();
        FFBlock.load();
        FFPlanet.load();
        Log.info("Loading some example content.");
    }

}
