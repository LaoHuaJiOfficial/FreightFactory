package prototypes;

import prototypes.customUnit.CustomUnitDialog;
import arc.math.geom.Point2;
import arc.struct.Seq;

public class FFVars {

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
