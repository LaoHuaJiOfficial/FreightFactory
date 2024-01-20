package utilities;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.util.Tmp;
import mindustry.graphics.Pal;

import static arc.math.Mathf.rand;

public class DrawExtend {
    public static void DrawChainLightning(float x1, float y1, float x2, float y2, float mul, float alpha) {


        float dst = Mathf.dst(x1, y1, x2, y2);

        Tmp.v1.set(x2, y2).sub(x1, y1).nor();

        float normx = Tmp.v1.x, normy = Tmp.v1.y;
        float range = 3f;

        int links = Mathf.ceil(dst / range);
        float spacing = dst / links;

        Lines.stroke(1.2f * mul);
        Draw.color(Color.white, Pal.reactorPurple, alpha);

        Lines.beginLine();

        Lines.linePoint(x1, y1);

        rand.setSeed((long) (x1 + y1));

        for (int i = 0; i < links; i++) {
            float nx, ny;
            if (i == links - 1) {
                nx = x2;
                ny = y2;
            } else {
                float len = (i + 1) * spacing;
                Tmp.v1.setToRandomDirection(rand).scl(range / 2f);
                nx = x1 + normx * len + Tmp.v1.x;
                ny = y1 + normy * len + Tmp.v1.y;
            }

            Lines.linePoint(nx, ny);
        }

        Lines.endLine();
    }
}
