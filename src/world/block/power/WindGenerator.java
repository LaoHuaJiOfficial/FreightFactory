package world.block.power;

import arc.math.Mathf;
import arc.struct.EnumSet;
import arc.util.Time;
import mindustry.world.blocks.power.PowerGenerator;
import mindustry.world.meta.Env;
import mindustry.world.meta.StatUnit;

public class WindGenerator extends PowerGenerator {

    public WindGenerator(String name) {
        super(name);
        flags = EnumSet.of();
        envEnabled = Env.any;
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.remove(generationType);
        stats.add(generationType, powerProduction * 60.0f, StatUnit.powerSecond);
    }

    public class WindGeneratorBuild extends GeneratorBuild {

        public boolean inRange() {
            return false;
        }

        public void updateTile() {
            productionEfficiency = 1 + Mathf.sin(Time.delta) * 0.1f;
        }
    }
}
