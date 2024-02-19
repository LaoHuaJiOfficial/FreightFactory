package contents;

import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Vec2;
import arc.util.Log;
import arc.util.Tmp;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;

public class FFFx {

    public static final Rand rand = Fx.rand;
    public static final Vec2 v = Fx.v;
    public static final Effect
        CrystaTrail_0 = new Effect(30f, e -> {
        Draw.color(Color.sky);

        Drawf.tri(
            e.x + v.trns(e.rotation + 120, 4 * e.fout()).x, e.y + v.trns(e.rotation + 120, 4 * e.fout()).y,
            3 + 8 * e.fout(),  10 + 45 * e.fout(), e.rotation + 135
        );

        Drawf.tri(
            e.x + v.trns(e.rotation - 120, 4 * e.fout()).x, e.y + v.trns(e.rotation - 120, 4 * e.fout()).y,
            3 + 8 * e.fout(), 10 + 45 * e.fout(), e.rotation - 135
        );

        Fill.poly(e.x, e.y ,6, 3 + 7 * e.fout(), e.rotation);

        Draw.color(Color.black);

        Drawf.tri(
            e.x + v.trns(e.rotation + 120, 4 * e.fout()).x, e.y + v.trns(e.rotation + 120, 4 * e.fout()).y,
            2 + 3 * e.fout(),  5 + 30 * e.fout(), e.rotation + 135
        );

        Drawf.tri(
            e.x + v.trns(e.rotation - 120, 4 * e.fout()).x, e.y + v.trns(e.rotation - 120, 4 * e.fout()).y,
            2 + 3 * e.fout(),  5 + 30 * e.fout(), e.rotation - 135
        );

        Fill.poly(e.x, e.y ,6, 2 + 4 * e.fout(), e.rotation);
        Draw.reset();
    });
}
