package prototypes.net;

import arc.Events;
import arc.util.Log;
import arc.util.Time;
import mindustry.Vars;
import mindustry.entities.abilities.Ability;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.gen.Call;
import mindustry.gen.Groups;
import mindustry.net.Net;
import prototypes.FFContent;

public class PacketHandler {
    public static void init(){
        Net.registerPacket(CustomUnitDataPacket::new);

        Vars.net.handleServer(CustomUnitDataPacket.class, (con, packet) -> {
            if (con != null){
                packet.handleServer(con);
            }
        });
        Vars.net.handleClient(CustomUnitDataPacket.class, CustomUnitDataPacket::handleClient);

        Events.on(EventType.PlayerConnectionConfirmed.class, e -> {
            Log.info("Player is in game?: " + Vars.state.isPlaying());
            Time.runTask(120, () -> {
                Vars.net.send(new CustomUnitDataPacket(), false);


            });
            //Call.clientPacketReliable();

        });

        Events.on(EventType.SaveLoadEvent.class, e -> {
            //Vars.net.send(new CustomUnitDataPacket(), false);

            //Vars.net.send(new CustomUnitDataPacket(), false);
            /*
            Log.info("Player is client?: " + Vars.net.client());
            if (Vars.net.client()){
                Log.info("Server Loaded Here");
            }

             */
        });

        Events.on(EventType.UnitCreateEvent.class, e -> {
            Log.info("UnitCreateEvent init");
            Vars.net.send(new CustomUnitDataPacket(), false);

        });




    }
}
