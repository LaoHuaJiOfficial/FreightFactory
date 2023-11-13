package contents;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.Vars;
import mindustry.entities.Effect;
import mindustry.graphics.Pal;

import static arc.math.Angles.randLenVectors;

public class FFFx {
    public static final Effect
    ScanMinerScanEffect = new Effect(120f, e -> {
        float size = 0;

        Draw.color(Pal.accent);

        Lines.stroke(2f * e.fslope());
        Lines.square(e.x + Mathf.sin(e.rotation) * e.fin(), e.y + Mathf.cos(e.rotation) * e.fin(), 15 * (Time.time), 45);
        //Lines.circle(e.x + Mathf.sin(e.rotation) * e.fin(), e.y + Mathf.cos(e.rotation) * e.fin(), 5 * e.fslope());

        Draw.reset();
    });
}
