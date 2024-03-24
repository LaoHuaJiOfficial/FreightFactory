package prototypes.block.module;

import arc.Events;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import arc.util.Log;
import arc.util.Tmp;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.game.EventType;
import mindustry.gen.Building;
import mindustry.gen.Icon;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.graphics.Shaders;
import mindustry.ui.Bar;
import mindustry.world.Block;
import mindustry.world.blocks.payloads.*;
import mindustry.world.modules.BlockModule;
import prototypes.payload.ModuleStat;

import static mindustry.Vars.state;
import static mindustry.Vars.tilesize;

public class UnitModuleInserter extends BlockProducer {
    public UnitModuleInserter(String name) {
        super(name);
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("progress", (BlockProducerBuild entity) -> new Bar("bar.progress", Pal.ammo, () -> entity.recipe() == null ? 0f : (entity.progress / 60f)));
    }

    public class UnitModuleInserterBuild extends BlockProducerBuild{
        public boolean CurrentUpgraded = false;
        @Override
        public Block recipe() {
            return null;
        }

        @Override
        public void draw(){
            Draw.rect(region, x, y, rotation * 90);

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
        public void dumpPayload(){
            //translate payload forward slightly
            float tx = Angles.trnsx(payload.rotation(), 0.1f), ty = Angles.trnsy(payload.rotation(), 0.1f);
            payload.set(payload.x() + tx, payload.y() + ty, payload.rotation());

            if(payload.dump()){
                payload = null;
            }else{
                payload.set(payload.x() - tx, payload.y() - ty, payload.rotation());
            }
        }

        @Override
        public boolean shouldConsume(){
            return super.shouldConsume() && payload != null;
        }

        @Override
        public void updateTile(){

            if(payload != null){
                UnitModule.UnitModuleBuild pay = (UnitModule.UnitModuleBuild) payload.build;
                //check if offloading
                if(CurrentUpgraded){
                    //Log.info("already upgraded!");
                    moveOutPayload();
                    //Log.info("test1");
                }else{
                    //Log.info("test2");
                    //update progress
                    if(moveInPayload()){
                        //Log.info("test3");
                        if(efficiency > 0){

                        }
                        //progress += buildSpeed * edelta();

                        if(progress >= 60f){

                        }

                        //Log.info("Input a module core: " + pay.stat.base.name());
                        pay.stat.addModule(ModuleStat.ModuleExtra.ArmorModule, 1);
                        //Log.info("After insert armor module MK2: " + pay.stat.ModuleList.get(ModuleStat.ModuleExtra.ArmorModule).tier);
                        //Log.info("Current Module Inserted: " + pay.stat.reconsCount);
                        CurrentUpgraded = true;
                        progress %= 1f;
                        consume();

                        heat = Mathf.lerpDelta(heat, Mathf.num(true), 0.15f);
                        time += heat * delta();

                        progress += edelta();
                    }
                }
            }
        }
    }
}
