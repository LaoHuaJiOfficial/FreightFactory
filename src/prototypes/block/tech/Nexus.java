package prototypes.block.tech;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.struct.EnumSet;
import arc.util.Log;
import contents.recipes.recipes;
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
import prototypes.FFContent;
import prototypes.FRules;
import prototypes.recipe.Recipe;
import prototypes.ui.dialog.RecipeResearchDialog;

public class Nexus extends Block {

    public float launchTime = 60 * 60f;

    public Nexus(String name) {
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

    public void init() {
        super.init();
    }

    @Override
    public void setBars() {
        super.setBars();

        addBar("Credit", (NexusBuild e) -> new Bar(() -> Core.bundle.format("bar.credit", e.GetCredit()), () -> Pal.accent, () -> 1f));

        addBar("CreditPerSecond", (NexusBuild e) -> new Bar(() -> Core.bundle.format("bar.credit-perSecond", e.GetCreditPerMinute()), () -> Pal.accent, () -> 1f));

        addBar("progress", (NexusBuild e) -> new Bar(() -> Core.bundle.get("bar.launchcooldown"), () -> Pal.ammo, () -> Mathf.clamp(e.launchCounter / launchTime)));

    }

    public class NexusBuild extends Building {
        public float launchCounter;

        public void buildConfiguration(Table table) {
            table.button(Icon.zoom, Styles.defaulti, () -> {
                //RecipeResearchDialog dialog = new RecipeResearchDialog();
                //dialog.show();
            }).size(40f);

            table.button(Icon.warning, Styles.defaulti, () -> {
                Log.info(recipes.graphite_1.unlocked + "|" + FFContent.recipeAll.get(1).name + " " + FFContent.recipeAll.get(1).unlocked);
            }).size(40f);

        }

        @Override
        public boolean acceptItem(Building source, Item item) {
            return items.total() < itemCapacity && Items.serpuloItems.contains(item);
        }

        @Override
        public void updateTile() {
            //increment launchCounter then launch when full and base conditions are met
            if ((launchCounter += edelta()) >= launchTime) {
                //if there are item requirements, use those.
                FRules.Credit += items.total() * 5;
                FRules.CreditPerMinute = items.total() * 5;

                Fx.massiveExplosion.at(x, y);

                items.clear();
                launchCounter = 0f;
            }
        }

        public float GetProgress() {
            return launchCounter / launchTime;
        }

        public int GetCredit() {
            return FRules.Credit;
        }

        public int GetCreditPerMinute() {
            return FRules.CreditPerMinute;
        }

        @Override
        public void onRemoved() {
            super.onRemoved();
            launchCounter = 0;
        }

        @Override
        public void draw() {
            super.draw();

            Draw.color(Pal.accent);
            Draw.z(Layer.effect);
            Lines.stroke(2f);
            Lines.arc(x, y, 20f, GetProgress());
            Draw.reset();
        }

        @Override
        public void drawSelect() {
            super.drawSelect();
        }
    }
}
