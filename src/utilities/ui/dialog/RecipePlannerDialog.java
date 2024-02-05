package utilities.ui.dialog;

import arc.Core;
import arc.graphics.Color;
import arc.scene.ui.Button;
import arc.scene.ui.Dialog;
import arc.scene.ui.Label;
import arc.scene.ui.layout.Table;
import mindustry.gen.Icon;
import mindustry.gen.Tex;
import mindustry.graphics.Pal;
import mindustry.ui.Fonts;
import mindustry.ui.Styles;

import static mindustry.Vars.*;

public class RecipePlannerDialog extends Dialog {
    public RecipePlannerDialog(){
        super("");

        int width = 200;
        int height = 40;
        int lineStroke = 5;


        Table main = new Table();
        //main.setSize(1280, 720);

        Table plannerListTable = new Table();
        //plannerListTable.setSize(200, 720);
        plannerListTable.background(Tex.windowEmpty).margin(-3,-3,-3,-3);

        Table recipeList = new Table();
        //recipeList.setSize(1080, 720);
        recipeList.background(Tex.windowEmpty).margin(0);

        Table resourceList = new Table();
        //recipeList.setSize(1080, 120);
        resourceList.background(Tex.windowEmpty).margin(0,-2,0,-2);

        Table plannerListButtons = new Table();
        plannerListButtons.background(Tex.windowEmpty);
        plannerListButtons.button("List Buttons", Icon.settings, Styles.cleart, iconMed, () -> {}).size
            (width, height).pad(0).margin(0).row();

        Table plannerList = new Table();
        plannerList.background(Tex.windowEmpty);
        plannerList.button("Recipe List", Icon.settings, Styles.cleart, iconMed, () -> {}).
            size(width, height * 13).pad(0).margin(0).row();

        Table factorySettings = new Table();
        factorySettings.background(Tex.windowEmpty);
        factorySettings.button("Factory Settings", Icon.settings, Styles.cleart, iconMed, () -> {}).
            size(width, height * 2).pad(0).margin(0).row();

        Table products = new Table();
        products.background(Tex.windowEmpty);
        products.add(new Label("Target Product")).left().row();
        products.button("Products Here", Icon.settings, Styles.cleart, iconMed, () -> {}).size(width, height * 2).pad(0).margin(0).row();

        Table byproducts = new Table();
        byproducts.background(Tex.windowEmpty);
        byproducts.add(new Label("Byproducts Output")).left().row();
        byproducts.button("Byproducts Here", Icon.settings, Styles.cleart, iconMed, () -> {}).size(width, height * 2).pad(0).margin(0).row();

        Table material = new Table();
        material.background(Tex.windowEmpty);
        material.add(new Label("Required Resource")).left().row();
        material.button("Materials Here", Icon.settings, Styles.cleart, iconMed, () -> {}).size(width * 3, height * 2).pad(0).margin(0).row();

        Table recipeSettings = new Table();
        recipeSettings.background(Tex.windowEmpty).margin(0);
        recipeSettings.button("Recipe Settings Here", Icon.settings, Styles.cleart, iconMed, () -> {}).size(width * 5, height).pad(0).margin(0).expandY().row();

        Table recipe = new Table();
        recipe.background(Tex.windowEmpty).margin(0);
        recipe.button("Some Recipes Here", Icon.settings, Styles.cleart, iconMed, () -> {}).size(width * 5, height * 12).pad(0).margin(0).expandY().row();

        plannerListTable.add(plannerListButtons).row();
        plannerListTable.image().growX().pad(0).height(lineStroke).color(Pal.gray).row();
        plannerListTable.add(plannerList).row();
        plannerListTable.image().growX().pad(0).height(lineStroke).color(Pal.gray).row();
        plannerListTable.add(factorySettings).row();

        resourceList.add(products);
        resourceList.image().growY().pad(0,-2,0,-2).width(lineStroke).color(Pal.gray);
        resourceList.add(byproducts);
        resourceList.image().growY().pad(0,-2,0,-2).width(lineStroke).color(Pal.gray);
        resourceList.add(material);

        recipeList.add(resourceList).row();
        recipeList.image().growX().pad(0).height(lineStroke).color(Pal.gray).row();
        recipeList.add(recipeSettings).row();
        recipeList.image().growX().pad(0).height(lineStroke).color(Pal.gray).row();
        recipeList.add(recipe);

        main.add(plannerListTable);
        main.image().growY().pad(0,-2,0, 0).width(lineStroke).color(Pal.gray);
        main.add(recipeList);

        cont.add(main);
        cont.background(Tex.pane).margin(0);

        buttons.button("@back", Icon.left, this::hide).size(300, 80);

    }
}
