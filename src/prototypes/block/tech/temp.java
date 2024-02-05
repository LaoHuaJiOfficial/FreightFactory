package prototypes.block.tech;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.struct.EnumSet;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.gen.Building;
import mindustry.gen.Icon;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.ui.Bar;
import mindustry.ui.Styles;
import mindustry.world.Block;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.Env;
import prototypes.FRules;

public class temp extends Block {
    public float launchTime = 60 * 60f;

    public temp(String name) {
        super(name);

        hasItems = true;
        itemCapacity = 1000;

        update = true;
        configurable = true;
        destructible = true;
        canOverdrive = false;
        solid = true;

        envEnabled = Env.any;
        flags = EnumSet.of(BlockFlag.launchPad);
    }

    public class tempBuild extends Building {
        public float launchCounter;

        public void buildConfiguration(Table table) {
            table.button(Icon.warning, Styles.defaulti, () -> {

            }).size(40f);
        }
    }
}
