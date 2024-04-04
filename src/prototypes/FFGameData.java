package prototypes;

import arc.struct.IntMap;
import arc.struct.Seq;
import arc.util.Log;
import mindustry.entities.abilities.Ability;
import mindustry.entities.abilities.ForceFieldAbility;
import mindustry.gen.Groups;
import mindustry.io.SaveFileReader;
import mindustry.io.SaveVersion;
import prototypes.recipe.Recipe;
import prototypes.unit.CustomUnitData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FFGameData implements SaveFileReader.CustomChunk {
    public static short CURRENT_VER = 1;
    public static FFGameData data;

    public FFGameData(){
        data = this;
        SaveVersion.addCustomChunk("ff-world-data", this);
    }

    public short version = 0;

    @Override
    public void write(DataOutput stream) throws IOException {
        stream.writeShort(CURRENT_VER);
        /*

        for (Recipe recipe: FFContent.recipeAll){
            recipe.write(stream);
        }

         */

        stream.writeInt(FFContent.CustomUnits.size);
        //Log.info(FFContent.CustomUnits.size);

        for (CustomUnitData customUnit: FFContent.CustomUnits){
            Log.info("write:" +  customUnit.id);
            stream.writeInt(customUnit.id);
            customUnit.write(stream);
        }
    }

    @Override
    public void read(DataInput stream) throws IOException {
        version = stream.readShort();

        if (version > 0){
            /*
            for (Recipe recipe: FFContent.recipeAll){
                recipe.read(stream, version);
            }

             */
            int num = stream.readInt();
            Log.info(num);

            for (int i = 0; i < num; i++){
                int id = stream.readInt();

                Log.info("read: "+ id);
                FFContent.CustomUnits.add(
                    new CustomUnitData(stream.readInt(),new Ability[]{
                    new ForceFieldAbility(
                        stream.readFloat(),
                        stream.readFloat(),
                        stream.readFloat(),
                        stream.readFloat(),
                        stream.readInt(),
                        stream.readFloat()
                    )
                }));
            }
        }



        version = CURRENT_VER;
    }
}
