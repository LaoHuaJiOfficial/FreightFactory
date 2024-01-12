package ui;

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

public class ArrowTempDisplay extends Table {

    public ArrowTempDisplay(float amount, boolean isInput){
        add(new Stack(){{
            var image = new Image(Icon.right).setScaling(Scaling.fit);
            image.setWidth(height * 2.5f);
            add(image);

            if(amount > 0){
                add(new Table(t -> {
                    t.left().bottom();
                    t.add((isInput?"[accent]": "[remove]") + amount + "K").style(Styles.outlineLabel);
                    t.pack();
                }));
            }

        }}).size(iconMed).padRight(3  + (amount != 0 && Strings.autoFixed(amount, 2).length() > 2 ? 8 : 0));
    }
}
