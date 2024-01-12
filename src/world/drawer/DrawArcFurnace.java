package world.drawer;

import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.geom.Position;
import arc.math.geom.Vec2;
import arc.util.Time;
import extra.DrawExtend;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.draw.DrawBlock;

public class DrawArcFurnace extends DrawBlock {
    public Color flameColor = Color.valueOf("f58349"), midColor = Color.valueOf("f2d585");


    @Override
    public void draw(Building build){
        float CircleStroke = 1.5f;
        float OuterCircleRad = 12f;
        float InnerCircleRad = 4f;
        float CenterBall = 1.5f;

        if(build.warmup() > 0f && flameColor.a > 0.001f){

            float Alpha = build.warmup();
            float StrokeMulti = build.warmup();
            float RadSin = Mathf.sin(Time.time * 0.03f);
            rand.setSeed(build.id);


            //******************************************************************************************
            Lines.stroke(CircleStroke * StrokeMulti);

            Draw.color(Pal.reactorPurple, Alpha);

            Fill.circle(build.x, build.y, CenterBall + RadSin * 0.3f);

            Lines.circle(build.x, build.y, InnerCircleRad + RadSin * 0.3f);

            Draw.color(Pal.reactorPurple2, Alpha);
            Lines.circle(build.x, build.y, OuterCircleRad + RadSin * 0.3f);


            Draw.color(Pal.reactorPurple, Alpha);

            float base = (Time.time / 40f);
            rand.setSeed(build.id);
            for(int i = 0; i < 25; i++){
                float fin = (rand.random(1f) + base) % 1f, fout = 1f - fin;
                float angle = rand.random(360f);
                float len = 20 * Interp.pow2Out.apply(fin);

                //Lines.lineAngle(build.x + Angles.trnsx(360f - angle, len), build.y + Angles.trnsy(360f - angle, len), 360f - angle, 5 * fout * build.warmup());

                Fill.poly(build.x + Angles.trnsx(360f - angle, len), build.y + Angles.trnsy(360f - angle, len), 3, 3 * fout * build.warmup(), rand.random(360f));
            }
            //******************************************************************************************


            Draw.blend();
            Draw.reset();
        }
    }
}
