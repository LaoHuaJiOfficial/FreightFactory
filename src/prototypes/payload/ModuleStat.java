package prototypes.payload;

import arc.struct.ObjectMap;

import java.util.Map;

public class ModuleStat {
    public enum ModuleBase{
        GroundCore,
        AirCore,
        NavyCore,
        TankCore,
        HoverCore,
        MechCore,
    }
    public enum ModuleExtra {
        ArmorModule,
        AttackModule,
        SupportModule,
    }
    public ModuleBase base;
    public ObjectMap<ModuleExtra, ModuleData> ModuleList;
    public int reconsCount;
    public ModuleStat (ModuleBase name){
        base = name;
        reconsCount = 0;
    }

    public void addModule(ModuleExtra module, int tier){
        if (ModuleList == null)ModuleList = new ObjectMap<>();
        if (ModuleList.containsKey(module)){
            ModuleList.get(module).addTier(tier);
        }else {
            ModuleList.put(module, new ModuleData(module, tier));
        }
        reconsCount++;
    }

    public static class ModuleData{
        public ModuleExtra module;
        public int tier;
        public ModuleData(ModuleExtra module, int tier){
            this.module = module;
            this.tier = tier;
        }

        public void addTier(int tier){
            this.tier += tier;
        }
    }
}
