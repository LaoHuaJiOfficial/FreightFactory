package prototypes.net;

import arc.struct.Seq;
import arc.util.Log;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.entities.abilities.Ability;
import mindustry.entities.abilities.ForceFieldAbility;
import mindustry.gen.Groups;
import mindustry.net.NetConnection;
import mindustry.net.Packet;
import prototypes.FFContent;
import prototypes.unit.CustomUnitData;

public class CustomUnitDataPacket extends Packet {
    public Seq<CustomUnitData> unitDataPacket;
    @Override
    public void write(Writes buffer){
        for (var unit: FFContent.CustomUnits){
            buffer.i(unit.id);
            for(Ability ability: unit.abilities){
                if (ability instanceof ForceFieldAbility forceField){
                    buffer.f(forceField.radius);
                    buffer.f(forceField.regen);
                    buffer.f(forceField.max);
                    buffer.f(forceField.cooldown);
                    buffer.i(forceField.sides);
                    buffer.f(forceField.rotation);
                }
            }
        }
    }

    @Override
    public void read(Reads buffer){
        unitDataPacket = new Seq<>();
        unitDataPacket.add(new CustomUnitData(buffer.i(), new ForceFieldAbility[]{
            new ForceFieldAbility(
                buffer.f(),
                buffer.f(),
                buffer.f(),
                buffer.f(),
                buffer.i(),
                buffer.f()
                )
        }));
    }

    @Override
    public void handleClient(){
        FFContent.CustomUnits = unitDataPacket;

        for (var unit: FFContent.CustomUnits){
            Log.info("client key: " + unit.id);
            if (Groups.unit.getByID(unit.id) != null){
                //Log.info("id" + Groups.unit.getByID(unit.key));
                //Log.info("length" + );
                Groups.unit.getByID(unit.id).abilities = unit.abilities.toArray(Ability.class);
                Log.info("client ability" + Groups.unit.getByID(unit.id).abilities.length);
            }else {
                Log.info("client unit == null");
            }
        }
    }

    @Override
    public void handleServer(NetConnection con){
        if (con != null){
            FFContent.CustomUnits = unitDataPacket;

            for (var unit: FFContent.CustomUnits){
                Log.info("key: " + unit.id);
                if (Groups.unit.getByID(unit.id) != null){
                    //Log.info("id" + Groups.unit.getByID(unit.key));
                    //Log.info("length" + );
                    Groups.unit.getByID(unit.id).abilities = unit.abilities.toArray(Ability.class);
                    Log.info("ability" + Groups.unit.getByID(unit.id).abilities.length);
                }else {
                    Log.info("unit == null");
                }
            }
        }
    }

}
