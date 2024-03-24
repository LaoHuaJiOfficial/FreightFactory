package prototypes.block.module;

import arc.util.Log;
import mindustry.entities.TargetPriority;
import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Env;
import prototypes.payload.ModuleStat;

public class UnitModule extends Block {
    public static ModuleStat.ModuleBase moduleBase;
    public static int tier;
    public UnitModule(String name) {
        super(name);
        //stat = new ModuleStat(moduleBase);

        size = 2;
        update = true;
        solid = true;
        destructible = true;
        canOverdrive = false;
        drawDisabled = false;

    }
    public class UnitModuleBuild extends Building{
        public boolean upgraded = false;
        public ModuleStat stat = new ModuleStat(moduleBase);

        @Override
        public void created(){
            super.created();
            //stat.addModule(ModuleStat.ModuleExtra.ArmorModule, 4);
            stat.reconsCount++;
            //Log.info(stat.reconsCount);
            Log.info("created armor tier" + stat.ModuleList.get(ModuleStat.ModuleExtra.ArmorModule).tier);
            this.kill();
        }
    }
}
