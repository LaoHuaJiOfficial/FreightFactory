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

public class FFPlanet {
    public static Planet Nauvis;

    public static void load() {
        Nauvis = new Planet("serpulo", Planets.sun, 1.0F, 3) {{
            this.generator = new SerpuloPlanetGenerator();
            this.meshLoader = (() -> new HexMesh(this, 6));
            this.cloudMeshLoader = (() -> new MultiMesh(
                new HexSkyMesh(this, 11, 0.15f, 0.13f, 5, (new Color()).set(Color.cyan).mul(0.9f).a(0.75f), 2, 0.45f, 0.9f, 0.38f),
                new HexSkyMesh(this, 1, 0.6f, 0.16f, 5, Color.white.cpy().lerp(Color.cyan, 0.55F).a(0.75F), 2, 0.45F, 1.0F, 0.41F)
            ));
            this.launchCapacityMultiplier = 0.5f;
            this.sectorSeed = 2;
            this.allowWaves = true;
            this.allowWaveSimulation = true;
            this.allowSectorInvasion = true;
            this.allowLaunchSchematics = true;
            this.enemyCoreSpawnReplace = true;
            this.allowLaunchLoadout = true;
            this.prebuildBase = false;
            this.ruleSetter = (r -> {
                r.waveTeam = Team.crux;
                r.placeRangeCheck = false;
                r.showSpawns = false;
            });
            this.iconColor = Color.cyan;
            this.atmosphereColor = Color.cyan;
            this.atmosphereRadIn = 0.02f;
            this.atmosphereRadOut = 0.3f;
            this.startSector = 15;
            this.alwaysUnlocked = true;
            this.landCloudColor = Color.cyan.cpy().a(0.5f);
            this.hiddenItems.addAll(Items.erekirItems).removeAll(Items.serpuloItems);
        }};
    }
}