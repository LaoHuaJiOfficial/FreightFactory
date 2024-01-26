package utilities.ui;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Font;
import arc.graphics.g2d.GlyphLayout;
import arc.graphics.g2d.Lines;
import arc.scene.ui.layout.Scl;
import arc.util.Align;
import arc.util.pooling.Pools;
import mindustry.graphics.Pal;
import mindustry.ui.Fonts;

import static mindustry.Vars.renderer;
import static mindustry.Vars.tilesize;

public class TextUtil {

    public static void ShowText(String text, int x, int y) {
        if (renderer.pixelator.enabled()) return;

        Font font = Fonts.outline;
        GlyphLayout layout = Pools.obtain(GlyphLayout.class, GlyphLayout::new);
        boolean ints = font.usesIntegerPositions();
        font.setUseIntegerPositions(false);
        font.getData().setScale(1f / 4f / Scl.scl(1f));
        layout.setText(font, text);

        float dx = x * tilesize, dy = y * tilesize;
        font.draw(text, dx, dy + layout.height + 1, Align.center);
        dy -= 1f;

        font.setUseIntegerPositions(ints);
        font.setColor(Color.white);
        font.getData().setScale(1f);
        Draw.reset();
        Pools.free(layout);
    }
}
