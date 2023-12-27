package extra;

import arc.ApplicationListener;
import arc.Events;
import arc.util.Log;
import mindustry.game.EventType;
import mindustry.ui.fragments.HudFragment;
import ui.PlacementFragmentF;

import java.lang.reflect.Field;

import static world.FRules.*;

public class FListener implements ApplicationListener {


    @Override
    public void init() {
        ApplicationListener.super.init();

        Events.run(EventType.Trigger.newGame, () ->{
            Credit = 0;
            CreditPerMinute = 0;

            pads.clear();
        });
    }
}
