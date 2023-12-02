package contents;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import mindustry.entities.Effect;
import mindustry.graphics.Pal;

public class FFFx {
    public static final Effect
        ScanMinerScanEffect = new Effect(120f, e -> {
        float size = 0;

        Draw.color(Pal.accent);

        Lines.stroke(2f * e.fslope());
        Lines.square(e.x + Mathf.cosDeg(e.rotation * 90) * 32, e.y + Mathf.sinDeg(e.rotation * 90) * 32, 15, 45);
        //Lines.circle(e.x + Mathf.cosDeg(e.rotation * 90) * 32, e.y + Mathf.sinDeg(e.rotation * 90) * 32, 5 * e.fslope());

        Draw.reset();
    });
}
