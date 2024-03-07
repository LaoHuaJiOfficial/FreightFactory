package contents;

import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
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

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;

public class FFFx {

    public static final Rand rand = Fx.rand;
    public static final Vec2 v = Fx.v;
    public static final Effect
        AirBlastHit = new Effect(42f, 160f, e -> {
            color(e.color);
            stroke(e.fout() * 5f);
            rand.setSeed(e.id);

            float circleRad = 6f + e.finpow() * 12f;
            Lines.circle(e.x, e.y, circleRad);

            int num = rand.random(4 , 6);
            for(int i = 0; i < num; i++){
                float angle = rand.random(360f);
                float lenRand = rand.random(0.5f, 1f);
                Tmp.v1.trns(angle, circleRad);

                for(int s : Mathf.signs){
                    Drawf.tri(e.x + Tmp.v1.x, e.y + Tmp.v1.y, e.foutpow() * 12f, e.fout() * 12f * lenRand + 3f, angle + 90f + s * 90f);
                }
            }
        }),
        CrystaHit = new Effect(50f, 100f, e -> {
            color(Color.sky);
            stroke(e.fout() * 2f);
            float circleRad = 4f + e.finpow() * 60f;
            Lines.circle(e.x, e.y, circleRad);

            color(Color.sky);
            for(int i = 0; i < 4; i++){
                Drawf.tri(e.x, e.y, 6f, 80f * e.fout(), i*90 + 45);
            }

            color();
            for(int i = 0; i < 4; i++){
                Drawf.tri(e.x, e.y, 3f, 30f * e.fout(), i*90 + 45);
            }

            Drawf.light(e.x, e.y, circleRad * 1.6f, Color.sky, e.fout());
        }),
        CathodeLaserEffect = new Effect(20f, e -> {

    });
}
