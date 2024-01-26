package prototypes.block.HeatBox;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.geom.Point2;
import arc.struct.Seq;
import arc.util.Strings;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.Block;
import utilities.FVars;

public class BlockF extends Block {


    //todo may add a heat module later
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

    public BlockF(String name) {
        super(name);

        solid = true;
        update = true;
        destructible = true;
        size = 1;
    }

    @Override
    public void init() {
        super.init();
        if (HeatCapacity > 0f || OutputHeatAmount > 0f || InputHeatAmount > 0f) {
            HasHeat = true;
        }
        if (OutputHeatAmount > 0f) {
            OutputHeat = true;
        }
        if (InputHeatAmount > 0f) {
            InputHeat = true;
        }
        if (OutputTempThreshold > FVars.BaseLine) {
            MaxTemp = OutputTempThreshold;
        }
        if (InputTempThreshold < FVars.BaseLine) {
            MinTemp = InputTempThreshold;
        }
    }

    @Override
    public void setBars() {
        super.setBars();


        if (HasHeat) {
            addBar("HeatBoxArea", (BuildF e) -> new Bar(() -> Core.bundle.format("bar.area", e.HeatBox.HeatBoxArea), () -> Pal.accent, () -> 1f));

            addBar("HeatBoxAll", (BuildF e) -> new Bar(() -> Core.bundle.format("bar.num", e.HeatBox.HeatBoxBuildingAll.size), () -> Pal.accent, () -> 1f));


            addBar("HeatBoxHeat", (BuildF e) -> new Bar(
                () -> Core.bundle.format("bar.heat-amount", Strings.fixed(e.HeatBox.GetHeatBoxHeat(), 0)),
                () -> e.HeatBox.CurrentTemp > FVars.BaseLine ? Pal.remove : Pal.techBlue,
                e::GetTempPercent));

            addBar("HeatBoxTemp", (BuildF e) -> new Bar(
                () -> Core.bundle.format("bar.height", Strings.fixed(e.HeatBox.GetCurrentTemp(), 0)),
                () -> e.HeatBox.CurrentTemp > FVars.BaseLine ? Pal.remove : Pal.techBlue,
                e::GetTempPercent));

        }


    }

    public class BuildF extends Building {
        public HeatBoxEntity HeatBox;

        public float GetTempPercent() {
            if (HasHeat) {
                if (HeatBox.CurrentTemp > 300f) {
                    return (HeatBox.CurrentTemp - 300f) / (MaxTemp - 300f);
                } else if (HeatBox.CurrentTemp < 300f) {
                    return (300f - HeatBox.CurrentTemp) / (300f - MinTemp);
                } else return 0f;
            } else {
                return 0f;
            }
        }

        public boolean HasHeat() {
            return HasHeat;
        }

        public boolean IsHeatProducer() {
            return OutputHeat;
        }

        public boolean IsHeatConsumer() {
            return InputHeat;
        }

        public float GetHeatBoxArea() {
            return HeatCapacity;
        }


        /*
        public void buildConfiguration(Table table){
            if(OutputHeat){
                table.button(Icon.add, Styles.defaulti, () -> extra.HeatBox.HeatAdd(OutputHeatAmount, OutputTempThreshold)).size(40f);
            }
            if(InputHeat){
                table.button(Icon.warning, Styles.defaulti, () -> extra.HeatBox.HeatRemove(InputHeatAmount, InputTempThreshold)).size(40f);
            }
        }
         */

        @Override
        public void drawSelect() {
            if (HasHeat) {
                for (BuildF build : HeatBox.HeatBoxBuildingAll) {
                    Draw.color(Pal.accent);
                    Fill.square(build.x, build.y, 4, 45);
                    Draw.reset();
                }
            }
        }

        @Override
        public void created() {
            if (HasHeat) {
                HeatBox = new HeatBoxEntity(0, 300f);
                HeatBox.AddBuild(this, 300f);
                HeatBox.CreateHeatBox();

                for (HeatBoxEntity other : PotentialHeatBoxLinks()) {
                    HeatBox.UpdateAddHeatBox(other);
                }
            }
        }

        /**
         * Get connected heatbox, TODO later
         *
         * @return all connected heatbox
         */
        public Seq<HeatBoxEntity> PotentialHeatBoxLinks() {
            Seq<HeatBoxEntity> PotentialLinks = new Seq<>();
            for (Point2 pos : AdjoinTile) {
                if (GetAdjoinTile(pos) != null) {
                    PotentialLinks.add(GetAdjoinTile(pos).HeatBox);
                }
            }
            return PotentialLinks;
        }

        /**
         * Get connected Buildings(FF)
         *
         * @return all Building(FF)
         */
        public Seq<BuildF> GetAdjoinBuildingF() {
            Seq<BuildF> AdjoinLinks = new Seq<>();
            for (Point2 pos : AdjoinTile) {
                if (GetAdjoinTile(pos) != null) {
                    AdjoinLinks.add(GetAdjoinTile(pos));
                }
            }
            return AdjoinLinks;
        }

        @Override
        public void onRemoved() {
            //extra.HeatBox.HeatBoxRemoveBuild(this);
            if (HeatBox != null) {
                HeatBox.remove(this);
            }
        }

        public boolean CheckAdjoinTile(Point2 pos) {
            Building build = Vars.world.build(tileX() + pos.x, tileY() + pos.y);
            return build instanceof BuildF;
        }

        public BuildF GetAdjoinTile(Point2 pos) {
            Building build = Vars.world.build(tileX() + pos.x, tileY() + pos.y);
            return (build instanceof BuildF ? (BuildF) build : null);
        }
    }
}

