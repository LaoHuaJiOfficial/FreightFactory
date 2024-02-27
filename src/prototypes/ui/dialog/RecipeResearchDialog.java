package prototypes.ui.dialog;

import arc.Core;
import arc.graphics.Color;
import arc.scene.Group;
import arc.scene.ui.ButtonGroup;
import arc.scene.ui.Dialog;
import arc.scene.ui.ScrollPane;
import arc.scene.ui.TextButton;
import arc.scene.ui.layout.Table;
import arc.util.Log;
import mindustry.gen.Icon;
import mindustry.gen.Tex;
import mindustry.graphics.Pal;
import mindustry.ui.Styles;
import prototypes.FFContent;
import prototypes.recipe.Recipe;

import static mindustry.Vars.discordURL;
import static mindustry.Vars.ui;

public class RecipeResearchDialog extends Dialog {
    public RecipeResearchDialog(){
        super("name");
        addCloseButton();
        setup();
    }

    private void setup(){
        Table recipeList = new Table(Styles.grayPanel);
        recipeList.marginRight(24f).marginLeft(24f);

        ScrollPane pane = new ScrollPane(recipeList);
        pane.setScrollingDisabled(true, false);

        ButtonGroup<TextButton> group = new ButtonGroup<>();

        for (Recipe recipe: FFContent.recipeAll){
            TextButton recipeSelect = new TextButton(recipe.recipeName, Styles.flatTogglet);

            recipeList.add(recipeSelect).group(group).size(480, 80).row();
            //recipeList.image().growX().pad(0).height(5).color(Pal.accent).row();
        }

        cont.add(pane);
    }
}
