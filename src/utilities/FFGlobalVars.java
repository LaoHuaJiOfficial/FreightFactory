package utilities;

import arc.math.geom.Point2;
import arc.struct.Seq;
import prototypes.FFContent;
import prototypes.FFGameData;

public class FFGlobalVars {
    public static final String ModNamePrefix = "ff-";
    public static final String[] rotSuffix = new String[]{"-E", "-N", "-W", "-S"};
    public static final String iconSuffix = "-icon";
    public static final float BaseLine = 300f;


    public static FFGameData gameData;
    public static Seq<Seq<Point2>> UnitTileGrid;


    public static void load(){
        gameData = new FFGameData();
        UnitTileGrid = new Seq<>();

        FFContent.load();
    }

    public static void init(){
    }
}
