package prototypes;

import mindustry.io.SaveFileReader;
import mindustry.io.SaveVersion;
import prototypes.recipe.Recipe;

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

        for (Recipe recipe: FFContent.recipeAll){
            recipe.write(stream);
        }
    }

    @Override
    public void read(DataInput stream) throws IOException {
        version = stream.readShort();

        if (version > 0){
            for (Recipe recipe: FFContent.recipeAll){
                recipe.read(stream, version);
            }
        }

        version = CURRENT_VER;
    }
}
