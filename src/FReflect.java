import arc.util.Log;
import mindustry.core.UI;
import mindustry.ui.fragments.HudFragment;
import ui.PlacementFragmentF;

import java.lang.reflect.Field;

public class FReflect {

    public static void init(){
        try {
            Field blockfrag = HudFragment.class.getDeclaredField("blockfrag");

            PlacementFragmentF BlockFrag = new PlacementFragmentF();

            blockfrag.setAccessible(true);

            HudFragment hud = new HudFragment();

            blockfrag.set(hud, BlockFrag);

            Log.info("BlockFrag field after modification: " + hud.blockfrag.getClass().getSimpleName());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
