package contents.bullets;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Angles;
import arc.math.Mathf;
import arc.util.Log;
import arc.util.Nullable;
import arc.util.Time;
import mindustry.content.Fx;
import mindustry.entities.Mover;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.game.Team;
import mindustry.gen.*;
import mindustry.graphics.Trail;

import static mindustry.Vars.*;

public class HelixTrailBulletType extends BasicBulletType {
    public int sideTrailNum = 3;
    public int sideTrailLength = 10;
    public float sideTrailWidth = 3f;
    public float sideTrailSpace = 15f;
    public float sideTrailTime = 25f;

    public HelixTrailBulletType(float speed, float damage) {
        super(speed, damage);
        trailEffect = Fx.none;
        despawnEffect = Fx.none;
        hitEffect = Fx.none;
    }

    public HelixTrailBulletType() {
        super(1, 1);
        trailEffect = Fx.none;
        despawnEffect = Fx.none;
        hitEffect = Fx.none;
    }

    @Override
    public void init(Bullet b){
        Trail[] sideTrails = new Trail[sideTrailNum];
        for (int i = 0; i < sideTrailNum; i++){
            sideTrails[i] = new Trail(sideTrailLength);
        }
        b.data = sideTrails;
    }

    @Override
    public void removed(Bullet b){
        super.removed(b);
        if (!(b.data() instanceof Trail[] sideTrails))return;

        if(sideTrailLength > 0){
            for (Trail trail: sideTrails){
                if (trail != null && trail.size() > 0){
                    Fx.trailFade.at(b.x, b.y, sideTrailWidth, trailColor, trail.copy());
                }
            }
        }
    }

    @Override
    public void draw(Bullet b) {
        super.draw(b);

        drawSideTrail(b);
    }

    @Override
    public void update(Bullet b){
        super.update(b);
        updateSideTrail(b);
    }

    public void drawSideTrail(Bullet b){
        if (!(b.data() instanceof Trail[] sideTrails))return;

        float z = Draw.z();
        Draw.z(z - 0.0001f);
        for (Trail trail: sideTrails){
            if(sideTrailLength > 0){
                if(trail != null){
                    trail.draw(trailColor, sideTrailWidth);
                }
            }
        }
        Draw.z(z);
    }

    public void updateSideTrail(Bullet b){
        if (!(b.data() instanceof Trail[] sideTrails))return;

        if(!headless && sideTrailLength > 0){
            for (int i = 0; i < sideTrailNum; i++){
                if(sideTrails[i] == null){
                    sideTrails[i] = new Trail(sideTrailLength);
                }
                sideTrails[i].length = sideTrailLength;
                sideTrails[i].update(
                    b.x + Mathf.sinDeg(Time.time * sideTrailTime + i * (360f / sideTrailNum)) * sideTrailSpace,
                    b.y + Mathf.cosDeg(Time.time * sideTrailTime + i * (360f / sideTrailNum)) * sideTrailSpace,
                    trailInterp.apply(b.fin()));
            }
        }
    }

}
