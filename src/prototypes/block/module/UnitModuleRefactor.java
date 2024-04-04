package prototypes.block.module;

import arc.Events;
import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import arc.struct.EnumSet;
import arc.struct.Seq;
import arc.util.Log;
import arc.util.Tmp;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.content.UnitTypes;
import mindustry.entities.abilities.Ability;
import mindustry.entities.abilities.ForceFieldAbility;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.effect.ExplosionEffect;
import mindustry.entities.part.RegionPart;
import mindustry.entities.units.WeaponMount;
import mindustry.game.EventType;
import mindustry.gen.Building;
import mindustry.gen.Call;
import mindustry.gen.Sounds;
import mindustry.gen.Unit;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.graphics.Shaders;
import mindustry.type.Weapon;
import mindustry.world.Block;
import mindustry.world.blocks.payloads.*;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.BlockGroup;
import prototypes.FFContent;
import prototypes.payload.ModuleStat;
import prototypes.unit.CustomUnitData;

import static mindustry.Vars.tilesize;

public class UnitModuleRefactor extends PayloadBlock {
    public UnitModuleRefactor(String name) {
        super(name);
        update = solid = true;
        rotate = true;
        rotateDraw = false;
        acceptsPayload = true;
        flags = EnumSet.of(BlockFlag.unitAssembler);
        sync = true;
        commandable = true;
        quickRotate = false;
    }

    public class UnitModuleRefactorBuild extends PayloadBlockBuild<Payload>{
        boolean NeedRefactored = true;
        boolean pushed = true;


        @Override
        public void moveOutPayload(){
            if(payload == null){
                //Log.info("null");
                return;
            }

            updatePayload();

            Vec2 dest = Tmp.v1.trns(rotdeg(), size * tilesize/2f);

            payRotation = Angles.moveToward(payRotation, rotdeg(), payloadRotateSpeed * delta());
            payVector.approach(dest, payloadSpeed);

            Building front = front();
            boolean canDump = front == null || !front.tile().solid();
            boolean canMove = front != null && (front.block.outputsPayload || front.block.acceptsPayload);

            if(canDump && !canMove){
                //Log.info("test n 100");
                pushOutput(payload, 1f - (payVector.dst(dest) / (size * tilesize / 2f)));
            }
            //Log.info(payload.x() + " " + payload.y());
            //Log.info(canDump + " " +  canMove + " " + payVector.within(dest, 0.001f));

            if(payVector.within(dest, 0.001f)){
                //Log.info("test n 200");

                payVector.clamp(-size * tilesize / 2f, -size * tilesize / 2f, size * tilesize / 2f, size * tilesize / 2f);

                if(canMove){
                    //Log.info("test n 300");
                    if(movePayload(payload)){
                        //Log.info("test n 1");
                        payload = null;
                        NeedRefactored = true;
                        pushed = true;
                    }
                }else if(canDump){
                    //Log.info("test n 2");
                    NeedRefactored = true;
                    pushed = true;
                    dumpPayload();
                }
            }
        }

        public void spawned(){
            payload = null;
        }

        @Override
        public void dumpPayload(){
            if(payload.dump()){
                Call.unitBlockSpawn(tile);
                Unit unitPay = ((UnitPayload)payload).unit;
                Events.fire(new EventType.UnitCreateEvent(unitPay, this));
                spawned();
            }
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
        public void draw(){
            Draw.rect(region, x, y, rotation * 90);

            if(payload != null){
                drawPayload();
            }

            Draw.z(Layer.blockBuilding + 1.1f);
            if (payload instanceof UnitPayload pay){
                Draw.rect(pay.unit.type.region, x, y, rotation * 90);
                for (var mount: pay.unit.mounts){
                    if (mount.weapon.region != null){
                        Draw.rect(mount.weapon.region, x, y, rotation * 90);
                    }
                }
            }




            //Draw.z(Layer.blockBuilding + 1.1f);
            //Draw.rect(topRegion, x, y);
        }

        @Override
        public void updateTile(){

            if(payload != null){

                if(moveInPayload() && pushed) {

                    if (payload instanceof BuildPayload b && NeedRefactored) {
                        //Log.info("test3");

                        UnitModule.UnitModuleBuild pay = (UnitModule.UnitModuleBuild) b.build;
                        if (pay.stat.ModuleList.get(ModuleStat.ModuleExtra.ArmorModule).tier == 4) {
                            this.payload = new UnitPayload(UnitTypes.reign.create(this.team));
                            ((UnitPayload)payload).unit.abilities = new Ability[]{
                                new ForceFieldAbility(80,4000,10000,5)
                            };
                            //((UnitPayload)payload).unit.write();


                            FFContent.CustomUnits.add(new CustomUnitData(((UnitPayload)payload).unit.id, ((UnitPayload)payload).unit.abilities));
                            //((UnitPayload)payload).unit.id
                            Log.info("test unit 4");
                            NeedRefactored = false;
                        }
                        if (pay.stat.ModuleList.get(ModuleStat.ModuleExtra.ArmorModule).tier == 3) {
                            this.payload = new UnitPayload(UnitTypes.scepter.create(this.team));
                            Log.info("test unit 3");
                            NeedRefactored = false;

                            WeaponMount[] mounts = new WeaponMount[UnitTypes.eclipse.weapons.size];
                            for(int i = 0; i < UnitTypes.eclipse.weapons.size; i++){
                                mounts[i]= new WeaponMount(UnitTypes.eclipse.weapons.get(i));
                            }
                            ((UnitPayload)payload).unit.mounts = mounts;
                            //((UnitPayload)payload).draw();

                        }
                        if (pay.stat.ModuleList.get(ModuleStat.ModuleExtra.ArmorModule).tier == 2) {
                            this.payload = new UnitPayload(UnitTypes.fortress.create(this.team));
                            Log.info("test unit 2");
                            NeedRefactored = false;

                        }
                        if (pay.stat.ModuleList.get(ModuleStat.ModuleExtra.ArmorModule).tier == 1) {
                            this.payload = new UnitPayload(UnitTypes.mace.create(this.team));
                            Log.info("test unit 1");
                            NeedRefactored = false;
                        }
                        pushed = false;

                        consume();
                    }else {
                        //.info(moveInPayload());


                        //Log.info("remove");

                    }
                }else {
                    if (payload instanceof UnitPayload){
                        moveOutPayload();


                    }
                    //Log.info("test");
                }
            }
        }
    }
}
