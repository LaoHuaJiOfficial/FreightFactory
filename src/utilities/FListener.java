package utilities;

import arc.ApplicationListener;
import arc.Events;
import mindustry.game.EventType;

import static prototypes.FRules.*;

public class FListener implements ApplicationListener {


    @Override
    public void init() {
        ApplicationListener.super.init();

        Events.run(EventType.Trigger.newGame, () -> {
            Credit = 0;
            CreditPerMinute = 0;

            pads.clear();
        });
    }
}
