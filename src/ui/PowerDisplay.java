package ui;

import arc.graphics.Color;
import arc.scene.ui.Image;
import arc.scene.ui.layout.Stack;
import arc.scene.ui.layout.Table;
import arc.util.Scaling;
import arc.util.Strings;
import mindustry.core.UI;
import mindustry.gen.Icon;
import mindustry.gen.Iconc;
import mindustry.graphics.Pal;
import mindustry.ui.ItemImage;
import mindustry.ui.Styles;
import mindustry.world.meta.StatCat;

import static mindustry.Vars.iconMed;

public class PowerDisplay extends Table {
    public float amount;

    public PowerDisplay(float amount){
        add(new Stack(){{
            var image = new Image(Icon.power).setScaling(Scaling.fit);
            image.setColor(Pal.accent);
            add(image);

            add(new Table(t -> {
                t.left().bottom();
                t.add(amount >= 1000 ? UI.formatAmount((int)(amount * 60)) : (int)(amount * 60) + "").style(Styles.outlineLabel);
                t.pack();
            }));

        }}).size(iconMed).padRight(3  + (amount != 0 && Strings.autoFixed(amount, 2).length() > 2 ? 8 : 0));
    }

    public PowerDisplay(float amount, boolean isInput){
        add(new Stack(){{
            var image = new Image(Icon.power).setScaling(Scaling.fit);
            if (isInput){
                image.setColor(Pal.accent);
            }else {
                image.setColor(Pal.remove);
            }
            add(image);

            add(new Table(t -> {
                t.left().bottom();
                t.add(amount >= 1000 ? UI.formatAmount((int)(amount * 60)) : (int)(amount * 60) + "").style(Styles.outlineLabel);
                t.pack();
            }));

        }}).size(iconMed).padRight(3  + (amount != 0 && Strings.autoFixed(amount, 2).length() > 2 ? 8 : 0));
    }
}
