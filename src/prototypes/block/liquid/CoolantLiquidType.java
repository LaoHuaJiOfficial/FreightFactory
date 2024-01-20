package prototypes.block.liquid;

import contents.CoolantLiquid;
import mindustry.gen.Building;
import mindustry.type.Liquid;

public interface CoolantLiquidType {
    default boolean isCoolantLiquid(Liquid liquid) {
        return (liquid == CoolantLiquid.Coolant300K ||
            liquid == CoolantLiquid.Coolant200K ||
            liquid == CoolantLiquid.Coolant100K ||
            liquid == CoolantLiquid.Coolant0K
        );
    }

    default boolean sourceIsCoolantLiquid(Building source, Liquid liquid) {
        return (source.liquids.current() == CoolantLiquid.Coolant300K ||
            source.liquids.current() == CoolantLiquid.Coolant200K ||
            source.liquids.current() == CoolantLiquid.Coolant100K ||
            source.liquids.current() == CoolantLiquid.Coolant0K
        );
    }
}
