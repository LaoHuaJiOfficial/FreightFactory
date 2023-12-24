package ui;

import arc.scene.ui.Image;
import arc.scene.ui.layout.Table;
import arc.util.Scaling;
import mindustry.core.UI;
import mindustry.gen.Icon;
import mindustry.gen.Iconc;
import mindustry.ui.ItemImage;
import mindustry.ui.Styles;
import mindustry.world.meta.StatCat;

public class PowerDisplay extends Table {
    public float amount;

    public PowerDisplay(float amount){

        add(new Table(t ->{
            t.left();
            t.add("[accent]" + Iconc.power + "[]").size(32f).scaling(Scaling.fit);
        }));

        add(new Table(t -> {
            t.left().bottom();
            t.add(amount >= 1000 ? UI.formatAmount((int)(amount * 60)) : (int)(amount * 60) + "").style(Styles.outlineLabel);
            t.pack();
        }));

        this.amount = amount;
    }
}
