package world.block.power;

import arc.math.*;
import arc.math.geom.Geometry;
import arc.struct.*;
import arc.util.Time;
import mindustry.game.Team;
import mindustry.world.blocks.power.PowerGenerator;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class WindGenerator extends PowerGenerator {

    public WindGenerator(String name){
        super(name);
        flags = EnumSet.of();
        envEnabled = Env.any;
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.remove(generationType);
        stats.add(generationType, powerProduction * 60.0f, StatUnit.powerSecond);
    }

    public class WindGeneratorBuild extends GeneratorBuild{

        public boolean inRange(){
            return false;
        }

        public void updateTile(){
            productionEfficiency = 1 + Mathf.sin(Time.delta) * 0.1f;
        }
    }
}
