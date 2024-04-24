package prototypes.block.module;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Vec2;
import arc.util.Eachable;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.entities.units.BuildPlan;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.Tile;
import mindustry.world.blocks.payloads.*;
import prototypes.block.inner.UnitModule;
import prototypes.payload.ModuleStat;

import static mindustry.Vars.*;

public class UnitModuleInserter extends PayloadBlock {
    public float moduleUpgradeTime = 60f;
    public UnitModuleInserter(String name) {
        super(name);

        size = 5;
        update = true;
        outputsPayload = true;
        hasItems = true;
        solid = true;
        hasPower = true;
        rotate = true;
        regionRotated1 = 1;
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("progress", (UnitModuleInserterBuild entity) -> new Bar("bar.progress", Pal.ammo, () -> entity.payload == null ? 0f : entity.fraction()));
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        int len = size/2+1;
        for (int i = 0; i < len; i++){
            for (int j: Mathf.signs){
                Building build = world.tile(tile.x - Geometry.d4x(rotation) * size + i * j, tile.y - Geometry.d4y(rotation) * size + i * j).build;
                if (!((build instanceof UnitModuleCrafter.UnitModuleCrafterBuild)||(build instanceof UnitModuleInserterBuild))){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region, topRegion};
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
        //Draw.rect(outRegion, plan.drawx(), plan.drawy(), plan.rotation * 90);
        Draw.rect(topRegion, plan.drawx(), plan.drawy());
        //Fill.rect(plan.drawx(), plan.drawy(), 8,8);
    }

    public class UnitModuleInserterBuild extends PayloadBlockBuild<Payload>{
        public float progress, time, speedScl;
        public boolean CurrentUpgraded = false;

        public float fraction(){
            return payload == null ? 0 : progress / moduleUpgradeTime;
        }

        @Override
        public void draw(){
            Draw.rect(region, x, y, rotation * 90);

            /*
            if(payload != null){
                Drawf.shadow(x, y, payload.size * tilesize * 2f, progress / recipe.buildCost);
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

             */

            drawPayload();

            Draw.z(Layer.blockBuilding + 1.1f);
            Draw.rect(topRegion, x, y);
        }

        @Override
        public boolean acceptPayload(Building source, Payload payload){
            if(!(this.payload == null && (this.enabled || source == this) && this.back() == source)){
                return false;
            }else {
                return payload instanceof BuildPayload pay && pay.build.block instanceof UnitModule;
            }
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
            boolean canMove = front != null && (front.block.outputsPayload || front.block.acceptsPayload);

            if(canDump && !canMove){
                pushOutput(payload, 1f - (payVector.dst(dest) / (size * tilesize / 2f)));
            }

            if(payVector.within(dest, 0.001f)){
                payVector.clamp(-size * tilesize / 2f, -size * tilesize / 2f, size * tilesize / 2f, size * tilesize / 2f);

                if(canMove){
                    if(movePayload(payload)){
                        CurrentUpgraded = false;
                        //Log.info("Reset can upgrade");
                        payload = null;
                    }
                }else if(canDump){
                    dumpPayload();
                }
            }
        }

        @Override
        public boolean shouldConsume(){
            return super.shouldConsume() && payload != null;
        }

        @Override
        public void updateTile(){

            if(payload != null){
                if (payload instanceof BuildPayload buildPay){
                    UnitModule.UnitModuleBuild pay = (UnitModule.UnitModuleBuild) buildPay.build;
                    //check if offloading
                    if(CurrentUpgraded){
                        moveOutPayload();
                    }else{
                        //update progress
                        if(moveInPayload()){
                            if (efficiency > 0){
                                progress += edelta();
                                if (progress >= moduleUpgradeTime){
                                    pay.stat.addModule(ModuleStat.ModuleExtra.ArmorModule, 1);
                                    CurrentUpgraded = true;
                                    progress %= 1f;
                                    consume();
                                }
                            }


                            //heat = Mathf.lerpDelta(heat, Mathf.count(true), 0.15f);
                            //time += heat * delta();

                            //progress += edelta();
                        }
                    }
                }

            }
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(progress);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            progress = read.f();
        }
    }
}
