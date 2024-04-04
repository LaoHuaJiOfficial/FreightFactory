package prototypes.customUnit;

import arc.struct.Seq;
import arc.util.Log;
import mindustry.entities.abilities.*;

public class AbilityList {
    public static Seq<Class<? extends Ability>> abilities;

    public static void init(){
        abilities = new Seq<>();

        abilities.add(ArmorPlateAbility.class);
        abilities.add(EnergyFieldAbility.class);
        abilities.add(ForceFieldAbility.class);
        abilities.add(LiquidExplodeAbility.class);
        abilities.add(LiquidRegenAbility.class);
        abilities.add(MoveEffectAbility.class);
        abilities.add(MoveLightningAbility.class);
        abilities.add(RegenAbility.class);
        abilities.add(RepairFieldAbility.class);
        abilities.add(ShieldArcAbility.class);
        abilities.add(ShieldRegenFieldAbility.class);
        abilities.add(SpawnDeathAbility.class);
        abilities.add(StatusFieldAbility.class);
        abilities.add(SuppressionFieldAbility.class);
        abilities.add(UnitSpawnAbility.class);

        Log.info("FF: Ability List Size: " + abilities.size);
    }
}
