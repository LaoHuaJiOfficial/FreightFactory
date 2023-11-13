package world.block;

import arc.math.geom.Geometry;
import arc.math.geom.Rect;
import arc.struct.Seq;
import arc.util.Nullable;
import mindustry.content.UnitTypes;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.blocks.units.UnitAssembler;
import mindustry.world.consumers.ConsumePayloadDynamic;

import static mindustry.Vars.tilesize;

public class TestBlock extends Block {

    public int areaSize = 11;
    public UnitType droneType = UnitTypes.assemblyDrone;
    public int dronesCreated = 4;
    public float droneConstructTime = 60f * 4f;

    public Seq<UnitAssembler.AssemblerUnitPlan> plans = new Seq<>(4);

    protected @Nullable ConsumePayloadDynamic consPayload;
    public TestBlock(String name) {
        super(name);
    }

    public Rect getRect(Rect rect, float x, float y, int rotation){
        rect.setCentered(x, y, areaSize * tilesize);
        float len = tilesize * (areaSize + size)/2f;

        rect.x += Geometry.d4x(rotation) * len;
        rect.y += Geometry.d4y(rotation) * len;

        return rect;
    }
}
