package contents.bullets;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.TextureRegion;
import arc.math.Interp;
import arc.math.Mathf;
import arc.util.Nullable;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Bullet;
import mindustry.graphics.Pal;

public class InfernoBullet extends BulletType {

    public Color backColor = Pal.bulletYellowBack, frontColor = Pal.bulletYellow;
    public Color mixColorFrom = new Color(1f, 1f, 1f, 0f), mixColorTo = new Color(1f, 1f, 1f, 0f);
    public float width = 5f, height = 7f;
    public float shrinkX = 0f, shrinkY = 0.5f;
    public Interp shrinkInterp = Interp.linear;
    public float spin = 0, rotationOffset = 0f;
    public String sprite;
    public @Nullable String backSprite;

    public TextureRegion backRegion;
    public TextureRegion frontRegion;

    public InfernoBullet(float speed, float damage, String bulletSprite) {
        super(speed, damage);
        this.sprite = bulletSprite;
        trailEffect = new Effect(20f, e -> {
            Draw.color(Color.white, Pal.lancerLaser, e.fslope());
            //Fill.rect(e.x, e.y, 3f, 2f * e.fout(), e.rotation);
            Fill.square(e.x, e.y, 2f * e.fout(), e.rotation + 45f);
        });
        frontColor = Pal.lancerLaser;
        backColor = Color.white;
        trailColor = Pal.lancerLaser;
        despawnEffect = Fx.none;
        hitEffect = Fx.none;
    }

    public InfernoBullet(float speed, float damage) {
        this(speed, damage, "bullet");
    }

    @Override
    public void load() {
        super.load();

        backRegion = Core.atlas.find(backSprite == null ? (sprite + "-back") : backSprite);
        frontRegion = Core.atlas.find(sprite);
    }

    @Override
    public void draw(Bullet b) {
        super.draw(b);
        float shrink = shrinkInterp.apply(b.fout());
        float height = this.height * ((1f - shrinkY) + shrinkY * shrink);
        float width = this.width * ((1f - shrinkX) + shrinkX * shrink);
        float offset = -90 + (spin != 0 ? Mathf.randomSeed(b.id, 360f) + b.time * spin : 0f) + rotationOffset;

        Color mix = Tmp.c1.set(mixColorFrom).lerp(mixColorTo, b.fin());

        Draw.mixcol(mix, mix.a);

        if (backRegion.found()) {
            Draw.color(backColor);
            Draw.rect(backRegion, b.x, b.y, width, height, b.rotation() + offset);
        }

        Draw.color(frontColor);
        Draw.rect(frontRegion, b.x, b.y, width, height, b.rotation() + offset);

        Draw.reset();
    }

    public void updateTrailEffects(Bullet b) {

        //TODO use new modified Trail(TrailUtil)
        if (b.timer(0, trailInterval)) {
            for (int i = 0; i < 3; i++) {
                float shiftX = Mathf.sinDeg(Time.time * 10 + i * 120) * 10;
                float shiftY = Mathf.cosDeg(Time.time * 10 + i * 120) * 10;
                trailEffect.at(b.x + shiftX, b.y + shiftY, b.rotation());
            }
        }
    }
}
