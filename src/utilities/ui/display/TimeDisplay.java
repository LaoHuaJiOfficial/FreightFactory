package utilities.ui.display;

import arc.Core;
import arc.graphics.Color;
import arc.scene.ui.Image;
import arc.scene.ui.layout.Stack;
import arc.scene.ui.layout.Table;
import arc.util.Scaling;
import arc.util.Strings;
import contents.FFImages;
import mindustry.gen.Icon;
import mindustry.type.Liquid;
import mindustry.ui.Styles;
import mindustry.world.meta.StatUnit;

import static mindustry.Vars.iconMed;

public class TimeDisplay extends Table {
    public final float amount;

    public TimeDisplay(float amount) {
        this.amount = amount;

        add(new Stack() {{
            add(new Image(Core.atlas.find("FF-time-icon")).setScaling(Scaling.fit));

            if (amount != 0) {
                Table t = new Table().left().bottom();
                t.add(Strings.autoFixed(amount / 60f , 1)+ "s").style(Styles.outlineLabel);
                add(t);
            }
        }}).size(iconMed);
    }
}
