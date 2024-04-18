package prototypes.map.planet;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.math.geom.Vec3;
import arc.util.Tmp;
import arc.util.noise.Simplex;
import mindustry.content.Blocks;
import mindustry.game.Rules;
import mindustry.game.Schematics;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.type.Sector;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.TileGen;

import static mindustry.Vars.world;

public class NauvisPlanetGenerator extends PlanetGenerator {
    Block[][] arr = {
        {Blocks.sandWater, Blocks.grass, Blocks.stone, Blocks.snow, Blocks.ice}
    };

    {
        baseSeed = 1;
    }

    @Override
    public void generateSector(Sector sector){
        //no bases
    }

    @Override
    public float getHeight(Vec3 position){
        return Mathf.pow(rawHeight(position), 3) * 1.5f;
    }

    @Override
    public Color getColor(Vec3 position){
        Block block = getBlock(position);

        if(block == Blocks.crystallineStone) block = Blocks.crystalFloor;

        return Tmp.c1.set(block.mapColor).a(1f - block.albedo);
    }

    Block getBlock(Vec3 position){
        float height = rawHeight(position);
        Tmp.v31.set(position);
        position = Tmp.v33.set(position).scl(2f);
        float temp = Simplex.noise3d(seed, 8, 0.6, 1f/2f, position.x, position.y + 99f, position.z);
        height *= 1.2f;
        height = Mathf.clamp(height);

        return arr[Mathf.clamp((int)(temp * arr.length), 0, arr[0].length - 1)][Mathf.clamp((int)(height * arr[0].length), 0, arr[0].length - 1)];
    }

    @Override
    public float getSizeScl(){
        return 2000;
    }

    @Override
    public void addWeather(Sector sector, Rules rules){

    }

    @Override
    public void genTile(Vec3 position, TileGen tile){

    }

    @Override
    protected void generate(){
        cliffGenerate(1325f, 4);

        Schematics.placeLaunchLoadout(width / 2, height / 2);
    }

    public void cliffGenerate(float scl, int density){
        float step = 0.4f /density;
        for (int i = 0; i < density; i++){
            float currentAltitude = step * i;
            pass((x, y) -> {
                float noise = noise(x, y, 3, 5f, scl);
                if(noise > 0.3 && noise < 0.8 && noise > currentAltitude + 0.3f){
                    block = Blocks.stoneWall;
                }
            });
            cliffs();
        }

        pass((x, y) -> {
            if(block == Blocks.cliff){
                if (Mathf.chance(0.01f)){
                    block = Blocks.air;
                    Geometry.circle(x, y, width, height, 20, (cx, cy) -> {
                        Tile tile = tiles.get(cx, cy);
                        if (tile.block() == Blocks.cliff){
                            tile.remove();
                        }
                    });
                }
            };
        });
    }

    float rawHeight(Vec3 position){
        return Simplex.noise3d(seed, 8, 0.7f, 1f, position.x, position.y, position.z);
    }

    @Override
    protected float noise(float x, float y, double octaves, double falloff, double scl, double mag){
        return Simplex.noise2d(0, octaves, falloff, 1f / scl, x, y) * (float)mag;
    }
}
