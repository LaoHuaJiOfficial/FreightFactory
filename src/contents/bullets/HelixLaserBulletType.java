package contents.bullets;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Geometry;
import arc.math.geom.Vec2;
import arc.struct.Seq;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.content.Fx;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Bullet;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.graphics.Trail;

import static mindustry.Vars.headless;

public class HelixLaserBulletType extends BulletType {

    public String sprite = "point-laser";
    public TextureRegion laser, laserEnd;

    public Color color = Color.white;

    public Effect beamEffect = Fx.colorTrail;
    public float beamEffectInterval = 3f, beamEffectSize = 3.5f;

    public float oscScl = 2f, oscMag = 0.3f;
    public float damageInterval = 5f;

    public float shake = 0f;

    public HelixLaserBulletType(){
        removeAfterPierce = false;
        speed = 0f;
        despawnEffect = Fx.none;
        lifetime = 20f;
        impact = true;
        keepVelocity = false;
        collides = false;
        pierce = true;
        hittable = false;
        absorbable = false;
        optimalLifeFract = 0.5f;
        shootEffect = smokeEffect = Fx.none;

        //just make it massive, users of this bullet can adjust as necessary
        drawSize = 1000f;
    }

    @Override
    public float continuousDamage(){
        return damage / damageInterval * 60f;
    }

    @Override
    public float estimateDPS(){
        return damage * 100f / damageInterval * 3f;
    }

    @Override
    public void init(Bullet b) {
        super.init(b);

        b.data = new Seq<Vec2>();
    }

    @Override
    public void load(){
        super.load();

        laser = Core.atlas.find(sprite);
        laserEnd = Core.atlas.find(sprite + "-end");
    }

    @Override
    public void draw(Bullet b){
        super.draw(b);

        Draw.color(color);
        float scale = b.fslope() * (1f - oscMag + Mathf.absin(Time.time, oscScl, oscMag));
        Drawf.laser(laser, laserEnd, b.x, b.y, b.aimX, b.aimY, scale);


        if (b.data instanceof Seq<?> data){
            @SuppressWarnings("unchecked")
            Seq<Vec2> points = (Seq<Vec2>) data;
            if (points.any()){
                Draw.color(Color.sky);
                Lines.stroke(5f * scale);
                Lines.beginLine();
                for (int i = 0; i < points.size; i +=2){
                    Lines.linePoint(points.get(i));
                }
                Lines.endLine();

                Draw.color(Pal.reactorPurple);
                Lines.stroke(3f * scale);
                Lines.beginLine();
                for (int i = 0; i < points.size; i +=2){
                    Lines.linePoint(points.get(i + 1));
                }
                Lines.endLine();
            }
        }
        Draw.reset();
    }

    @Override
    public void update(Bullet b){
        updateTrail(b);
        updateTrailEffects(b);
        updateBulletInterval(b);

        if(b.timer.get(0, damageInterval)){
            Damage.collidePoint(b, b.team, hitEffect, b.aimX, b.aimY);
        }

        if(b.timer.get(1, beamEffectInterval)){
            beamEffect.at(b.aimX, b.aimY, beamEffectSize * b.fslope(), hitColor);
        }

        if (b.data instanceof Seq<?> data){
            @SuppressWarnings("unchecked")
            Seq<Vec2> points = (Seq<Vec2>) data;
            if(b.timer.get(2, Time.delta)){
                points.clear();

                float len = Mathf.dst(b.x, b.y, b.aimX, b.aimY);
                int steps = (int)(len / 2);
                float step = 1f / steps;
                Rand rand = new Rand(b.id);
                float scale = Time.time + rand.random(360);

                Tmp.v2.set(b.aimX, b.aimY);
                for(int i = 0; i < steps; i++){
                    float s = step * i;
                    Tmp.v1.set(b.x, b.y);
                    Tmp.v1.lerp(Tmp.v2, s);
                    points.add(new Vec2(Tmp.v1.x + Mathf.sinDeg(scale * 10 + 5 * i) * 15f, Tmp.v1.y + Mathf.cosDeg(scale * 10 + 4 * i) * 15f));
                    points.add(new Vec2(Tmp.v1.x - Mathf.sinDeg(scale * 6 + 3 * i) * 25f, Tmp.v1.y - Mathf.cosDeg(scale * 6 + 3 * i) * 25f));
                }
                b.data = points;
            }
        }

    }

    @Override
    public void updateTrailEffects(Bullet b){
        if(trailChance > 0){
            if(Mathf.chanceDelta(trailChance)){
                trailEffect.at(b.aimX, b.aimY, trailRotation ? b.angleTo(b.aimX, b.aimY) : (trailParam * b.fslope()), trailColor);
            }
        }

        if(trailInterval > 0f){
            if(b.timer(0, trailInterval)){
                trailEffect.at(b.aimX, b.aimY, trailRotation ? b.angleTo(b.aimX, b.aimY) : (trailParam * b.fslope()), trailColor);
            }
        }
    }

    @Override
    public void updateTrail(Bullet b){
        if(!headless && trailLength > 0){
            if(b.trail == null){
                b.trail = new Trail(trailLength);
            }
            b.trail.length = trailLength;
            b.trail.update(b.aimX, b.aimY, b.fslope() * (1f - (trailSinMag > 0 ? Mathf.absin(Time.time, trailSinScl, trailSinMag) : 0f)));
        }
    }

    public void updateBulletInterval(Bullet b){
        if(intervalBullet != null && b.time >= intervalDelay && b.timer.get(2, bulletInterval)){
            float ang = b.rotation();
            for(int i = 0; i < intervalBullets; i++){
                intervalBullet.create(b, b.aimX, b.aimY, ang + Mathf.range(intervalRandomSpread) + intervalAngle + ((i - (intervalBullets - 1f)/2f) * intervalSpread));
            }
        }
    }
}
