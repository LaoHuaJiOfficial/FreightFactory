package world;

import arc.struct.ObjectSet;
import arc.struct.OrderedSet;
import arc.struct.Seq;
import arc.util.Log;
import mindustry.entities.TargetPriority;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Env;

import static world.FRules.pads;

public class test extends Block {

    public test(String name){
        super(name);
        solid = true;
        destructible = true;
        group = BlockGroup.walls;
        buildCostMultiplier = 6f;
        canOverdrive = false;
        drawDisabled = false;
        crushDamageMultiplier = 5f;
        priority = TargetPriority.wall;

        //it's a wall of course it's supported everywhere
        envEnabled = Env.any;
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        return pads.size < 10;
    }

    public class testBuild extends Building{


        //todo different team
        @Override
        public void created(){
            super.created();
            pads.add(this);
            if (pads.size > 10){
                kill();
                pads.remove(this);
            }
        }

        @Override
        public void onRemoved(){
            super.onRemoved();
            pads.remove(this);
            Log.info("exceed");
        }

    }
}
