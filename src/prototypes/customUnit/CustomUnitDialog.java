package prototypes.customUnit;

import arc.Core;
import arc.scene.ui.*;
import arc.scene.ui.layout.Table;
import arc.util.Scaling;
import mindustry.Vars;
import mindustry.entities.units.WeaponMount;
import mindustry.gen.Icon;
import mindustry.gen.Tex;
import mindustry.ui.Styles;
import mindustry.ui.dialogs.BaseDialog;
import prototypes.customUnit.grid.GridPartList;

public class CustomUnitDialog extends BaseDialog {
    public CustomUnitCanvas canvas;

    public CustomUnitDialog(){
        super("CustomUnit");
        clear();
        margin(0f);

        canvas = new CustomUnitCanvas();

        stack(
            new Image(Styles.black5),
            canvas,


            new Table(){{
                table(table -> {
                    table.setBackground(Tex.pane);
                    //Table mainCont = new Table();
                    Table GridCont = new Table();


                    ScrollPane pane = new ScrollPane(GridCont, Styles.smallPane);
                    pane.setScrollingDisabled(true, false);
                    pane.setOverscroll(false, false);
                    table.add(pane).maxHeight(800f);

                    Runnable rebuildWeapon = () -> {
                        GridCont.clear();
                        ButtonGroup<Button> gridButtons = new ButtonGroup<>();
                        gridButtons.setMaxCheckCount(1);
                        for (var grid: GridPartList.GridWeaponList){
                            GridCont.table(cont -> {
                                Button GridTable = new Button(Styles.defaultb);
                                gridButtons.add(GridTable);
                                GridTable.table(table1 -> {
                                    table1.image(grid.weapon.icon).scaling(Scaling.fit).size(120,120);
                                    table1.table(table2 -> {
                                        table2.label(() -> grid.weapon.name).wrap().width(240).row();
                                        table2.label(() -> "Grid Width: " + grid.width).wrap().width(240).row();
                                        table2.label(() -> "Grid Height: " + grid.height).wrap().width(240).row();
                                    });
                                }).size(400,0);
                                GridTable.clicked(() -> canvas.queryGridPart = grid);
                                GridCont.add(GridTable);
                            }).row();
                        }
                    };

                    rebuildWeapon.run();
                    //table.add(mainCont);

                }).size(400,0).expandY().right();
            }}.right(),

            new Table(){{
                buttons.defaults().size(160f, 64f).pad(2f);

                buttons.button("@back", Icon.left, CustomUnitDialog.this::hide);
                //buttons.button("Set Scale", Icon.add, () -> canvas.unitSize = 12);
                //buttons.button("Reset Scale", Icon.add, () -> canvas.unitSize = 40);
                buttons.button("Clear", Icon.add, () -> {
                    canvas.GridPartSeq.clear();
                    canvas.placedTiles.clear();
                });
                buttons.button("Apply", Icon.add, () -> {
                    Vars.player.unit().mounts = new WeaponMount[0];
                    for (var grid: canvas.GridPartSeq){
                        grid.apply(Vars.player.unit());
                    }
                });
                buttons.slider(10, 120, 10, 40, num -> {
                    canvas.unitSize = num;
                });

                setFillParent(true);
                margin(3f);

                add(titleTable).growX().fillY();
                row().add().grow();
                row().add(buttons).fill();
                addCloseListener();
            }}.left().bottom()

            ).grow().pad(0f).margin(0f);
    }

}
