import arc.Core;
import arc.Events;
import arc.input.KeyCode;
import arc.scene.ui.Dialog;
import arc.scene.ui.ScrollPane;
import arc.scene.ui.layout.Table;
import arc.util.Log;
import arc.util.Scaling;
import arc.util.Time;
import contents.*;
import mindustry.Vars;
import mindustry.entities.abilities.Ability;
import mindustry.entities.units.WeaponMount;
import mindustry.game.EventType;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.gen.Groups;
import mindustry.gen.Icon;
import mindustry.mod.Mod;
import mindustry.type.Category;
import mindustry.ui.Styles;
import prototypes.FFContent;
import prototypes.FFVars;
import prototypes.customUnit.AbilityList;
import prototypes.customUnit.CustomUnitDialog;
import prototypes.customUnit.grid.GridPartData;
import prototypes.customUnit.grid.GridPartList;
import prototypes.customUnit.weapon.WeaponList;
import prototypes.net.PacketHandler;
import prototypes.recipe.Recipe;
import utilities.functions.GameUtil;
import utilities.game.FListener;
import utilities.game.FVanillaChange;

import static mindustry.Vars.content;
import static mindustry.Vars.player;

public class ModMain extends Mod {

    public ModMain() {
        Log.info("<MOD FOOD FACTORY LOADED>");

        Events.on(ClientLoadEvent.class, e -> {
            Time.runTask(10f, () -> {
                AbilityList.init();
                WeaponList.init();
            });
            Time.runTask(20f, GridPartList::init);
        });

        Events.run(EventType.Trigger.preDraw, () -> {
            if (FFContent.CustomUnits.any() && Vars.state.isPlaying()){
                for (var unit: FFContent.CustomUnits){
                    if (Groups.unit.getByID(unit.id) != null){
                        Groups.unit.getByID(unit.id).abilities = unit.abilities.toArray(Ability.class);
                    }
                }
            }
        });

        Events.run(EventType.Trigger.uiDrawBegin, () -> {
            if (Vars.player != null && Core.input.keyTap(KeyCode.l)){
                //FFVars.UnitTileGrid.add(GameUtil.getUnitRect(content.unit("eclipse"), 16));
                FFVars.UnitTileGrid.clear();
                FFVars.UnitTileGrid.add(GameUtil.getUnitRect(player.unit().type, 16));

                CustomUnitDialog customUnitDialog = new CustomUnitDialog();
                customUnitDialog.show();
            }
        });
    }
    @Override
    public void init(){
        PacketHandler.init();
        FFSprites.init();
        Events.on(EventType.WorldLoadEvent.class, e -> {
            for (Recipe recipe: FFContent.recipeAll){
                recipe.resetUnlock();
            }
            /*


             */
            //Log.info("reset");
        });

        Events.on(EventType.SaveLoadEvent.class, e -> {
            if (!FFContent.CustomUnits.isEmpty()){
                Log.info(FFContent.CustomUnits.size);
                for (var unit: FFContent.CustomUnits){
                    Log.info("key: " + unit.id);
                    if (Groups.unit.getByID(unit.id) != null){
                        //Log.info("id" + Groups.unit.getByID(unit.key));
                        //Log.info("length" + );
                        Groups.unit.getByID(unit.id).abilities = unit.abilities.toArray(Ability.class);
                        Log.info("ability" + Groups.unit.getByID(unit.id).abilities.length);
                    }else {
                        Log.info("unit == null");
                    }
                }
            }else {
                Log.info("no-null");

            }

        });
    }

    @Override
    public void loadContent() {
        //Highest load priority
        FFSounds.load();
        FFItems.load();
        FFLiquids.load();

        FFVars.load();

        Recipes.init();
        FFBullets.load();

        FFBlock.load();
        FFPlanet.load();

        FListener FListener = new FListener();
        FListener.init();

        FVanillaChange.init();
    }

}
