package prototypes.map.filter;

import mindustry.maps.generators.BasicGenerator;
import mindustry.world.Tiles;

public class CliffGenerator extends BasicGenerator{
    @Override
    public void generate(Tiles tiles) {

    }


    @Override
    protected float noise(float x, float y, double octaves, double falloff, double scl, double mag) {
        return 0;
    }
}
