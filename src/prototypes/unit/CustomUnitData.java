package prototypes.unit;

import arc.struct.Seq;
import mindustry.entities.abilities.Ability;
import mindustry.entities.abilities.ForceFieldAbility;
import mindustry.gen.Groups;
import mindustry.type.Weapon;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CustomUnitData {
    public int id;
    public Seq<Ability> abilities = new Seq<>();
    public Seq<Weapon> weapons = new Seq<>();

    public CustomUnitData(int id, Ability[] abilities){
        this.id = id;
        this.abilities.add(abilities);
    }

    public void write(DataOutput stream) throws IOException {
        stream.writeInt(id);
        for(Ability ability: abilities){
            if (ability instanceof ForceFieldAbility forceField){
                stream.writeFloat(forceField.radius);
                stream.writeFloat(forceField.regen);
                stream.writeFloat(forceField.max);
                stream.writeFloat(forceField.cooldown);
                stream.writeInt(forceField.sides);
                stream.writeFloat(forceField.rotation);
            }
        }
    }

    public void read(DataInput stream) throws IOException{

        Groups.unit.getByID(stream.readInt()).abilities = new Ability[]{
            new ForceFieldAbility(
                stream.readFloat(),
                stream.readFloat(),
                stream.readFloat(),
                stream.readFloat(),
                stream.readInt(),
                stream.readFloat()
            )
        };
    }
}
