package utilities.game;

import arc.ApplicationListener;
import arc.assets.Loadable;
import mindustry.core.UI;
import prototypes.ui.fragment.HudFragmentF;

public class FUI implements ApplicationListener, Loadable {
    public HudFragmentF hudFragF;
    public UI ui;

    @Override
    public void init() {
        ui = new UI();
        hudFragF = new HudFragmentF();

        hudFragF.build(ui.hudGroup);
        ui.hudfrag.shown = false;
    }

}
