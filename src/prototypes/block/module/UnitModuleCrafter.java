package prototypes.block.module;

import arc.math.Angles;
import arc.math.geom.Vec2;
import arc.util.Tmp;
import mindustry.gen.Building;
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

    public class UnitModuleCrafterBuild extends ConstructorBuild{
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
