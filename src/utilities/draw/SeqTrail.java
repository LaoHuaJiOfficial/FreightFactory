package utilities.draw;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import arc.struct.Seq;
import arc.util.Log;
import arc.util.Time;

public class SeqTrail {
    public Seq<Vec2> points;
    public Seq<Float> widths;

    public void addNode(Vec2 point, float width, int length){
        points.add(point);
        if (points.size == length){
            points.remove(0);
        }
        if (widths.size == length){
            widths.remove(0);
        }
        widths.add(width);
    }

    public void addNode(float x, float y, float width, int length){
        if (points == null)points = new Seq<>(length);
        if (widths == null)widths = new Seq<>(length);

        points.add(new Vec2(x, y));
        if (points.size == length){
            points.remove(0);
        }
        if (widths.size == length){
            widths.remove(0);
        }
        widths.add(width);

        Log.info(x + " " + y + " " + points.size);
    }

    public void draw(Color color, float width) {
        Draw.color(color);
        //float lastAngle = this.lastAngle;

        if (points != null && widths != null){
            for (int i = 0; i < points.size; i++) {
                float x1 = points.get(i).x, y1 = points.get(i).y, w1 = widths.get(i);
                float x2, y2, w2;

                float size = width / points.size;

                //last position is always lastX/Y/W
                if (i < points.size - 1) {
                    x2 = points.get(i + 1).x;
                    y2 = points.get(i + 1).y;
                    w2 = widths.get(i + 1);
                } else {
                    x2 = x1;
                    y2 = y1;
                    w2 = w1;
                }

                float z = -Angles.angleRad(x1, y1, x2, y2);
                if (w1 <= 0.001f || w2 <= 0.001f) continue;

                float
                    cx = Mathf.sin(z) * size * w1,
                    cy = Mathf.cos(z) * size * w1,
                    nx = Mathf.sin(z) * size * w2,
                    ny = Mathf.cos(z) * size * w2;

                Fill.quad(
                    x1 - cx, y1 - cy,
                    x1 + cx, y1 + cy,
                    x2 + nx, y2 + ny,
                    x2 - nx, y2 - ny
                );
            }
        }
    }
}
