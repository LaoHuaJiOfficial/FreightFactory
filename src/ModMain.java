import arc.Core;
import arc.Events;
import arc.util.Log;
import arc.util.Time;
import contents.*;
import extra.FListener;
import extra.FVanillaChange;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.content.UnitTypes;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.mod.Mod;
import mindustry.type.UnitType;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.ui.fragments.HudFragment;
import ui.HudFragmentF;
import ui.PlacementFragmentF;

import java.lang.reflect.Field;

public class ModMain extends Mod {

    public ModMain() {
        Log.info("FF Loaded.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //Vars.renderer.minZoom = 1f;
            //Vars.renderer.maxZoom = 20f;

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

    }

    @Override
    public void loadContent() {



        CoolantLiquid.load();

        //Highest load priority
        FFSounds.load();
        FFItems.load();
        FFBlock.load();
        FFPlanet.load();
        FFUnitTypes.load();
        //Log.info("Loading some example content.");

        FListener FListener = new FListener();
        FListener.init();

        FVanillaChange.init();

    }

}
