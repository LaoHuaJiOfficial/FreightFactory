import arc.Core;
import arc.Events;
import arc.input.KeyCode;
import arc.util.Log;
import arc.util.Time;
import contents.*;
import mindustry.Vars;
import mindustry.entities.abilities.Ability;
import mindustry.game.EventType;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.gen.Groups;
import mindustry.mod.Mod;
import mindustry.ui.dialogs.PlanetDialog;
import mindustry.ui.dialogs.ResearchDialog;
import prototypes.FFContent;
import prototypes.customUnit.AbilityList;
import prototypes.customUnit.CustomUnitDialog;
import prototypes.customUnit.grid.GridPartList;
import prototypes.customUnit.weapon.WeaponList;
import prototypes.net.PacketHandler;
import prototypes.recipe.Recipe;
import prototypes.struct.TechTree;
import utilities.FFGlobalVars;
import utilities.functions.GameUtil;
import utilities.game.FListener;

import static mindustry.Vars.player;

public class ModMain extends Mod {

    public ModMain() {
        Log.info("<MOD FOOD FACTORY LOADED>");

        Events.on(ClientLoadEvent.class, e -> {
            PlanetDialog.debugSelect = true;
            ResearchDialog.debugShowRequirements = true;


            Time.runTask(10f, () -> {
                AbilityList.init();
                WeaponList.init();

                //FFGlobalVars.noiseTest.show();
            });
            Time.runTask(20f, GridPartList::init);
            Time.runTask(10f, () -> {
                TechTree.depthNodeArr(Technology.smelt1);
                var seq = TechTree.outNodes;
                for (int i = 0; i < seq.size; i++){
                    for (int j = 0; j < seq.get(i).size; j++){
                        Log.info(seq.get(i).get(j).name + " in depth:" + i + " in index:" + j);
                    }
                }
            });

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
                FFGlobalVars.UnitTileGrid.clear();
                FFGlobalVars.UnitTileGrid.add(GameUtil.getUnitRect(player.unit().type, 16));

                CustomUnitDialog customUnitDialog = new CustomUnitDialog();
                customUnitDialog.show();
            }
        });

        Events.on(EventType.WorldLoadEvent.class, e -> {
            for (Recipe recipe: FFContent.recipeAll){
                recipe.resetUnlock();
            }
        });
    }
    @Override
    public void init(){
        PacketHandler.init();
        GlobalSprites.init();
        FFGlobalVars.init();

        TechTree.init();
        Technology.init();
    }

    @Override
    public void loadContent() {
        //Highest load priority
        FFSounds.load();
        FFItems.load();
        FFLiquids.load();

        FFGlobalVars.load();

        Recipes.init();
        FFBullets.load();

        FFBlock.load();
        FFPlanet.load();

        //FListener FListener = new FListener();
        //FListener.init();

        Technology.load();
        //FVanillaChange.init();

    }

}
