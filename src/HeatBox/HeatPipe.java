package HeatBox;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.geom.Geometry;
import arc.struct.IntMap;
import arc.util.Log;
import mindustry.Vars;
import mindustry.gen.Building;

public class HeatPipe extends BlockF{

    public TextureRegion atlas;
    public IntMap<TextureRegion> split = new IntMap<>();
    public HeatPipe(String name){
        super(name);

        HasHeat = true;
        HeatCapacity = 10f;
    }


    public TextureRegion tile(int x, int y){
        if(atlas == null) return null;
        return new TextureRegion(atlas, x * 32, y * 32, 32, 32);
    }
    public IntMap<TextureRegion> split(TextureRegion atlas){
        if(atlas == null) return null;

        /*
          0000,0010,1010,1000,
          0100,0110,1110,1100,
          0101,0111,1111,1101,
          0001,0011,1011,1001
        */
        
        split.put(0, tile(0,0));
        split.put(1, tile(0,3));
        split.put(2, tile(1,0));
        split.put(3, tile(1,3));
        split.put(4, tile(0,1));
        split.put(5, tile(0,2));
        split.put(6, tile(1,1));
        split.put(7, tile(1,2));
        split.put(8, tile(3,0));
        split.put(9, tile(3,3));
        split.put(10, tile(2,0));
        split.put(11, tile(2,3));
        split.put(12, tile(3,1));
        split.put(13, tile(3,2));
        split.put(14, tile(2,1));
        split.put(15, tile(2,2));


        return split;
    }

    @Override
    public void load(){
        super.load();
        atlas = Core.atlas.find(name + "-atlas");
        split = split(atlas);
    }

    public class HeatPipeBuild extends BuildF{

        int index = 0;
        @Override
        public void onProximityUpdate(){
            super.onProximityUpdate();
            getRegion();
        }
        @Override
        public void created(){
            super.created();
            getRegion();
        }

        @Override
        public void onRemoved(){
            super.onRemoved();
        }

        public void getRegion(){

            index = 0;

            if (Vars.world.build(tile.x, tile.y + 1) instanceof HeatPipeBuild){
                index += 1;
            }
            if (Vars.world.build(tile.x + 1, tile.y) instanceof HeatPipeBuild){
                index += 2;
            }
            if (Vars.world.build(tile.x, tile.y - 1) instanceof HeatPipeBuild){
                index += 4;
            }
            if (Vars.world.build(tile.x - 1, tile.y) instanceof HeatPipeBuild){
                index += 8;
            }
        }
        @Override
        public void draw(){
            super.draw();
            Draw.rect(split.get(index), x, y);
        }
    }
}
