package prototypes.ui.dialog;

import arc.Core;
import arc.graphics.Color;
import arc.scene.ui.Dialog;
import mindustry.gen.Icon;
import mindustry.gen.Tex;
import mindustry.graphics.Pal;

import static mindustry.Vars.discordURL;
import static mindustry.Vars.ui;

public class RecipeResearchDialog extends Dialog {
    public RecipeResearchDialog(){
        super("");

        float h = 70f;

        cont.margin(12f);

        Color color = Color.valueOf("7289da");

        cont.table(t -> {
            t.background(Tex.button).margin(0);

            t.table(img -> {
                img.image().height(h - 5).width(40f).color(color);
                img.row();
                img.image().height(5).width(40f).color(color.cpy().mul(0.8f, 0.8f, 0.8f, 1f));
            }).expandY();

            t.table(i -> {
                i.image(Icon.discord);
            }).size(h).left();

            t.add("@discord").color(Pal.accent).growX().padLeft(10f);
        }).size(520f, h).pad(10f);

        buttons.defaults().size(170f, 50);
        buttons.button("@back", Icon.left, this::hide);
    }
}
