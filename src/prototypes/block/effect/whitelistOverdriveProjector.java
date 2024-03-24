package prototypes.block.effect;

import arc.math.Mathf;
import arc.struct.Seq;
import arc.util.Log;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.logic.Ranged;
import mindustry.world.Block;
import mindustry.world.blocks.defense.OverdriveProjector;

import static mindustry.Vars.*;
import static mindustry.Vars.tilesize;

public class whitelistOverdriveProjector extends OverdriveProjector {
    public Seq<Block> whiteListBlock = new Seq<>();
    public whitelistOverdriveProjector(String name) {
        super(name);
    }

    public class whitelistOverdriveBuild extends OverdriveBuild implements Ranged {
        @Override
        public void updateTile(){
            smoothEfficiency = Mathf.lerpDelta(smoothEfficiency, efficiency, 0.08f);
            heat = Mathf.lerpDelta(heat, efficiency > 0 ? 1f : 0f, 0.08f);
            charge += heat * Time.delta;

            if(hasBoost){
                phaseHeat = Mathf.lerpDelta(phaseHeat, optionalEfficiency, 0.1f);
            }

            if(charge >= reload){
                float realRange = range + phaseHeat * phaseRangeBoost;

                charge = 0f;
                indexer.eachBlock(this, realRange,   other -> other.block.canOverdrive && whiteListBlock.contains(other.block()), other -> other.applyBoost(realBoost(), reload + 1f));
            }

            if(timer(timerUse, useTime) && efficiency > 0){
                consume();
            }
        }

        @Override
        public void drawSelect(){
            float realRange = range + phaseHeat * phaseRangeBoost;

            indexer.eachBlock(this, realRange,
                other -> other.block.canOverdrive && whiteListBlock.contains(other.block()),
                other -> Drawf.selected(other, Tmp.c1.set(baseColor).a(Mathf.absin(4f, 1f))));

            Drawf.dashCircle(x, y, realRange, baseColor);
        }
    }
}
