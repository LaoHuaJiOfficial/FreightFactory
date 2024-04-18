package contents;

import arc.graphics.Color;
import mindustry.content.Items;
import mindustry.content.Planets;
import mindustry.game.Team;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.type.Planet;
import prototypes.map.planet.NauvisPlanetGenerator;

public class FFPlanet {
    public static Planet Nauvis;

    public static void load() {
        Nauvis = new Planet("Nauvis", Planets.sun, 1f, 1) {{

            generator = new NauvisPlanetGenerator();
            meshLoader = (() -> new HexMesh(this, 6));
            launchCapacityMultiplier = 0.5f;
            sectorSeed = 2;
            ruleSetter = (r -> {
                r.waveTeam = Team.crux;
                r.placeRangeCheck = false;
                r.showSpawns = false;
            });
            iconColor = Color.cyan;
            atmosphereColor = Color.cyan;

            minZoom = 1f;
        }};
    }
}