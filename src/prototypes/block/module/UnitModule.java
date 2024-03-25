package prototypes.block.module;

import mindustry.gen.Building;
import mindustry.world.Block;
import prototypes.payload.ModuleStat;

//A tricky way for so-called module
public class UnitModule extends Block {
    public static ModuleStat.ModuleBase moduleBase;

    public UnitModule(String name) {
        super(name);
        size = 2;
        update = true;
        solid = true;
        destructible = true;
        canOverdrive = false;
        drawDisabled = false;
    }
    @SuppressWarnings("InnerClassMayBeStatic")
    public class UnitModuleBuild extends Building{
        public ModuleStat stat = new ModuleStat(moduleBase);
        @Override
        public void created(){
            super.created();
            this.kill();
        }
    }
}
