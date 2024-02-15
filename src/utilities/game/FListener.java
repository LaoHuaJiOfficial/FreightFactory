package utilities.game;

import arc.ApplicationListener;
import arc.Core;
import arc.Events;
import mindustry.game.EventType;

import static prototypes.FRules.*;

public class FListener implements ApplicationListener {


    @Override
    public void init() {
        ApplicationListener.super.init();

        Core.app.post(() -> Events.run(EventType.Trigger.newGame, () -> {
            Credit = 0;
            CreditPerMinute = 0;

            pads.clear();
        }));

    }
}
