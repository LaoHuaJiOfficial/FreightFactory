package prototypes.block.module;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.util.Eachable;
import arc.util.Time;
import arc.util.Tmp;
import contents.blocks.ModuleBlock;
import mindustry.Vars;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.graphics.Shaders;
import mindustry.world.blocks.payloads.BlockProducer;
import mindustry.world.blocks.payloads.Constructor;
import mindustry.world.meta.Stat;

import static mindustry.Vars.tilesize;

public class UnitModuleCrafter extends Constructor {
    public UnitModuleCrafter(String name) {
        super(name);
    }
    @Override
    public void setStats(){
        super.setStats();
        stats.remove(Stat.output);
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region};
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
        //Draw.rect(outRegion, plan.drawx(), plan.drawy(), plan.rotation * 90);
        //Draw.rect(topRegion, plan.drawx(), plan.drawy());
    }

    public Rect getRect(Rect rect, float x, float y, int rotation){
        float len = tilesize * size;
        rect.setCentered(x, y, len);
        rect.x += Geometry.d4x(rotation) * len;
        rect.y += Geometry.d4y(rotation) * len;
        return rect;
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);
        float len = tilesize * size;

        x *= tilesize;
        y *= tilesize;
        Rect rect = getRect(Tmp.r1, x, y, rotation);

        Draw.reset();
        Draw.mixcol(!valid ? Pal.breakInvalid : Color.white, (!valid ? 0.4f : 0.24f) + Mathf.absin(Time.globalTime, 6f, 0.28f));
        Draw.alpha(0.5f);
        Draw.rect(ModuleBlock.ModuleInserter.region, x + Geometry.d4x(rotation) * len, y + Geometry.d4y(rotation) * len, rotation);
        Draw.reset();

        Drawf.dashRect(valid ? Pal.accent : Pal.remove, rect);
    }

    public class UnitModuleCrafterBuild extends ConstructorBuild{

        @Override
        public void draw(){
            //Draw.mixcol(Color.white, 0.2f);
            Draw.rect(region, x, y, rotdeg());
            //Draw.reset();
            //Draw.rect(outRegion, x, y, rotdeg());

            var recipe = recipe();
            if(recipe != null){
                Drawf.shadow(x, y, recipe.size * tilesize * 2f, progress / recipe.buildCost);
                Draw.draw(Layer.blockBuilding, () -> {
                    Draw.color(Pal.accent);

                    for(TextureRegion region : recipe.getGeneratedIcons()){
                        Shaders.blockbuild.region = region;
                        Shaders.blockbuild.time = time;
                        Shaders.blockbuild.progress = progress / recipe.buildCost;

                        Draw.rect(region, x, y, recipe.rotate ? rotdeg() : 0);
                        Draw.flush();
                    }

                    Draw.color();
                });
                Draw.z(Layer.blockBuilding + 1);
                Draw.color(Pal.accent, heat);

                Lines.lineAngleCenter(x + Mathf.sin(time, 10f, Vars.tilesize / 2f * recipe.size + 1f), y, 90, recipe.size * Vars.tilesize + 1f);

                Draw.reset();
            }

            drawPayload();

            Draw.z(Layer.blockBuilding + 1.1f);
            //Drawf.shadow(topRegion, x - (size / 2f), y - (size / 2f), rotation - 90);
            //Draw.rect(topRegion, x, y, rotdeg());
        }
        @Override
        public void moveOutPayload(){
            if(payload == null) return;

            updatePayload();

            Vec2 dest = Tmp.v1.trns(rotdeg(), size * tilesize/2f);

            payRotation = Angles.moveToward(payRotation, rotdeg(), payloadRotateSpeed * delta());
            payVector.approach(dest, payloadSpeed * delta());

            Building front = front();
            boolean canDump = front == null || !front.tile().solid();
            boolean canMove = front != null && (front.block.outputsPayload || front.block.acceptsPayload) && (front.block instanceof UnitModuleInserter);

            if(canDump && !canMove){
                pushOutput(payload, 1f - (payVector.dst(dest) / (size * tilesize / 2f)));
            }

            if(payVector.within(dest, 0.001f)){
                payVector.clamp(-size * tilesize / 2f, -size * tilesize / 2f, size * tilesize / 2f, size * tilesize / 2f);

                if(canMove){
                    if(movePayload(payload)){
                        payload = null;
                    }
                }else if(canDump){
                    dumpPayload();
                }
            }
        }
    }
}
