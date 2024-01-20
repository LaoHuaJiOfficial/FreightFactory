package contents.bullets;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Rand;
import arc.math.geom.Geometry;
import arc.util.Tmp;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Building;
import mindustry.gen.Bullet;
import mindustry.gen.Unit;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;

import static arc.graphics.g2d.Lines.lineAngle;

public class CrystaBullet extends BulletType {
    private static float cdist = 0f;
    private static Unit result;

    public float trailSpacing = 10f;

    public Effect trailEffectCenter = Fx.none;
    public Effect trailEffectSide = Fx.none;

    public CrystaBullet() {
        scaleLife = true;
        lifetime = 100f;
        collides = false;
        reflectable = false;
        keepVelocity = false;
        backMove = false;

        shootEffect = despawnEffect = Fx.none;

        trailEffectCenter = new Effect(15f, e -> {
            Rand rand = new Rand(e.id);
            Draw.color(Color.white, Pal.lancerLaser, e.fslope());
            Lines.stroke(e.fout() * 4f * rand.random(1f));
            lineAngle(e.x, e.y, e.rotation + 180f, e.fout() * 4 * rand.random(0, 1) * 10f);
            lineAngle(e.x, e.y, e.rotation, e.fin() * 4 * rand.random(0, 1) * 15f);

        });

        trailEffectSide = new Effect(10f, e -> {
            Rand rand = new Rand(e.id);

            Draw.color(Color.white, Pal.lancerLaser, e.fslope());
            Lines.stroke(e.fout() * 2f * rand.random(1f));
            lineAngle(e.x, e.y, e.rotation + 180f, e.fout() * 4 * rand.random(0, 1) * 6f);
            lineAngle(e.x, e.y, e.rotation, e.fin() * 4 * rand.random(0, 1) * 8f);
        });

        hitEffect = new Effect(14, e -> {
            Rand rand = new Rand(e.id);

            Draw.color(Color.white, Pal.lancerLaser, e.fslope());

            float ang = rand.random(180f);
            Drawf.tri(e.x, e.y, 15 * e.fout(), 50 * e.fin(), ang);
            Drawf.tri(e.x, e.y, 15 * e.fout(), 50 * e.fin(), ang + 180f);

        });
    }

    @Override
    public void init(Bullet b) {
        super.init(b);

        Rand rand = new Rand(b.id);

        float px = b.x + b.lifetime * b.vel.x,
            py = b.y + b.lifetime * b.vel.y,
            rot = b.rotation();

        Geometry.iterateLine(0f, b.x, b.y, px, py, trailSpacing, (x, y) -> {
            if (rand.chance(0.8)) {
                float offset = 2f;
                Tmp.v1.set(x, y).nor();
                float nx = Tmp.v1.x + rand.random(-1f, 1f) * offset;
                float ny = Tmp.v1.y + rand.random(-1f, 1f) * offset;
                trailEffectCenter.at(x + nx, y + ny, rot);
            }

            if (rand.chance(0.4)) {
                float offset = 5f;
                Tmp.v1.set(x, y).nor();
                float nx = Tmp.v1.x + rand.random(-1f, 1f) * offset;
                float ny = Tmp.v1.y + rand.random(-1f, 1f) * offset;
                trailEffectSide.at(x + nx, y + ny, rot);
            }
        });

        b.time = b.lifetime;
        b.set(px, py);

        //calculate hit entity

        cdist = 0f;
        result = null;
        float range = 1f;

        Units.nearbyEnemies(b.team, px - range, py - range, range * 2f, range * 2f, e -> {
            if (e.dead() || !e.checkTarget(collidesAir, collidesGround) || !e.hittable()) return;

            e.hitbox(Tmp.r1);
            if (!Tmp.r1.contains(px, py)) return;

            float dst = e.dst(px, py) - e.hitSize;
            if ((result == null || dst < cdist)) {
                result = e;
                cdist = dst;
            }
        });

        if (result != null) {
            b.collision(result, px, py);
        } else if (collidesTiles) {
            Building build = Vars.world.buildWorld(px, py);
            if (build != null && build.team != b.team) {
                build.collision(b);
            }
        }

        b.remove();

        b.vel.setZero();
    }
}