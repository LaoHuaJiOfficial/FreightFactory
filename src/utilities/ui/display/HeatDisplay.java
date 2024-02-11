package utilities.ui.display;

import arc.scene.ui.Image;
import arc.scene.ui.layout.Stack;
import arc.scene.ui.layout.Table;
import arc.util.Scaling;
import arc.util.Strings;
import mindustry.core.UI;
import mindustry.gen.Icon;
import mindustry.graphics.Pal;
import mindustry.ui.Styles;

import static mindustry.Vars.iconMed;

public class HeatDisplay extends Table {
    public float amount;

    public HeatDisplay(float amount) {

        add(new Stack() {{
            var image = new Image(Icon.waves).setScaling(Scaling.fit);
            image.setColor(Pal.accent);
            add(image);

            add(new Table(t -> {
                t.left().bottom();
                t.add(amount >= 1000 ? UI.formatAmount((int) (amount)) : (int) (amount) + "").style(Styles.outlineLabel);
                t.pack();
            }));

        }}).size(iconMed).padRight(3 + (amount != 0 && Strings.autoFixed(amount, 2).length() > 2 ? 8 : 0));
    }

    public HeatDisplay(float amount, boolean isInput) {

        add(new Stack() {{
            var image = new Image(Icon.waves).setScaling(Scaling.bounded);
            if (isInput) {
                image.setColor(Pal.remove);
            } else {
                image.setColor(Pal.techBlue);
            }
            add(image);

            add(new Table(t -> {
                t.left().bottom();
                t.add(amount >= 1000 ? UI.formatAmount((int) (amount)) : (int) (amount) + "").style(Styles.outlineLabel);
                t.pack();
            }));

        }}).size(iconMed).padRight(3 + (amount != 0 && Strings.autoFixed(amount, 2).length() > 2 ? 8 : 0));
    }
}
