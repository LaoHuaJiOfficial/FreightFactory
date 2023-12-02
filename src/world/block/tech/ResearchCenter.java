package world.block.tech;

import arc.math.Mathf;
import arc.scene.ui.Dialog;
import arc.scene.ui.layout.Table;
import mindustry.gen.Building;
import mindustry.gen.Icon;
import mindustry.gen.Tex;
import mindustry.ui.Styles;
import mindustry.world.Block;
import mindustry.world.Build;
import mindustry.world.meta.Env;

public class ResearchCenter extends Block {
    public ResearchCenter(String name){
        super(name);

        configurable = true;
        destructible = true;
        canOverdrive = false;
        solid = true;

        envEnabled = Env.any;
    }

    public void init() {
        super.init();
    }

    public class ResearchCenterBuild extends Building {
        public void buildConfiguration(Table table){

            table.button(Icon.pencil, Styles.defaulti, () ->{
                Dialog dialog = new Dialog();

                dialog.resized(dialog::hide);

                dialog.cont.table(Tex.pane, body -> {});

                dialog.cont.row();

                dialog.closeOnBack();

                dialog.buttons.defaults().size(150f, 64f);
                dialog.buttons.button("@cancel", Icon.cancel, dialog::hide);

                dialog.show();
            }).size(80f);

        }
    }
}
