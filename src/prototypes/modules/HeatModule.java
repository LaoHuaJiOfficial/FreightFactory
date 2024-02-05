package prototypes.modules;

import arc.math.geom.Point2;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.world.modules.BlockModule;
import prototypes.block.HeatBox.HeatBoxEntity;

public class HeatModule extends BlockModule {

    protected static final Point2[] AdjoinTile = {
        new Point2(1, 0),
        new Point2(0, 1),
        new Point2(-1, 0),
        new Point2(0, -1),
    };

    /**
     * Used in heat box check , for heat consumers and heat producers
     */
    public boolean HasHeat = false;
    public boolean InputHeat = false;
    public boolean OutputHeat = false;

    /**
     * Basic element provided to heat box
     *  TODO change this shitty name
     */
    public float HeatCapacity = 0f;
    public float MaxTemp = 3300f;
    public float MinTemp = 0f;

    /**
     * Input and Output heat
     *  TODO Max/Min temp
     */
    public float InputHeatAmount = 0f;
    public float InputTempThreshold = 300f;
    public float OutputHeatAmount = 0f;
    public float OutputTempThreshold = 300f;

    public HeatBoxEntity HeatBox;

    @Override
    public void write(Writes write){

    }

    @Override
    public void read(Reads read){

    }
}
