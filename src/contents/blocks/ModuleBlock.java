package contents.blocks;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.world.Block;
import prototypes.block.inner.UnitModule;
import prototypes.block.module.UnitModuleCrafter;
import prototypes.block.module.UnitModuleInserter;
import prototypes.block.module.UnitModuleRefactor;
import prototypes.payload.ModuleStat;

import static mindustry.type.ItemStack.with;

public class ModuleBlock {
    public static Block
        GroundCore,

        CoreConstructor, ModuleInserter, UnitRefactor;

    public static void load(){
        GroundCore = new UnitModule("module") {{
            moduleBase = ModuleStat.ModuleBase.GroundCore;
            requirements(Category.distribution, with(Items.beryllium, 1));
            health = 100;
        }};

        CoreConstructor = new UnitModuleCrafter("core-constructor"){{
            requirements(Category.units, with(Items.silicon, 100, Items.beryllium, 150, Items.tungsten, 80));
            //regionSuffix = "-dark";
            hasPower = true;
            buildSpeed = 0.6f;
            consumePower(2f);
            size = 5;
            //TODO expand this list
            filter = Seq.with(
                GroundCore
            );
        }};

        ModuleInserter = new UnitModuleInserter("module-inserter"){{
            requirements(Category.units, with(Items.silicon, 100, Items.beryllium, 150, Items.tungsten, 80));
            hasPower = true;
            itemCapacity = 40;
            consumePower(2f);
            consumeItem(Items.beryllium, 20);
            size = 5;
        }};

        UnitRefactor = new UnitModuleRefactor("unit-refactor"){{
            requirements(Category.units, with(Items.silicon, 100, Items.beryllium, 150, Items.tungsten, 80));
            itemCapacity = 40;
            hasPower = true;
            consumePower(2f);
            consumeItem(Items.beryllium, 20);
            size = 5;
        }};
    }
}
