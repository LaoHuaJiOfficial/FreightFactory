package world.block.liquid;

import contents.CoolantLiquid;
import mindustry.content.Liquids;
import mindustry.gen.Building;
import mindustry.type.Liquid;
import mindustry.world.blocks.liquid.Conduit;

public class CoolantConduit extends Conduit {
    public CoolantConduit(String name){
        super(name);

        leaks = false;
    }

    public class CoolantConduitBuild extends ConduitBuild implements CoolantLiquidType{
        @Override
        public boolean acceptLiquid(Building source, Liquid liquid){
            noSleep();
            return (liquids.current() == liquid || liquids.currentAmount() < 0.2f)
                && (tile == null || source == this || (source.relativeTo(tile.x, tile.y) + 2) % 4 != rotation)
                && isCoolantLiquid(liquid) && sourceIsCoolantLiquid(source, liquid);
        }


    }
}
