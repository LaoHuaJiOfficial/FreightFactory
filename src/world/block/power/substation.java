package world.block.power;

import mindustry.world.blocks.power.PowerBlock;
import mindustry.world.meta.Env;

public class substation extends PowerBlock {

    public int PowerArea = 1;
    public substation(String name) {
        super(name);

        configurable = true;
        consumesPower = false;
        outputsPower = false;
        canOverdrive = false;
        swapDiagonalPlacement = true;
        schematicPriority = -10;
        drawDisabled = false;
        envEnabled |= Env.space;
        destructible = true;

        //nodes do not even need to update
        update = false;
    }
}
