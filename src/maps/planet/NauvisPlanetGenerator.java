package maps.planet;

import arc.graphics.Color;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.math.geom.Vec2;
import arc.math.geom.Vec3;
import arc.struct.FloatSeq;
import arc.struct.ObjectSet;
import arc.struct.Seq;
import arc.util.Structs;
import arc.util.Tmp;
import arc.util.noise.Noise;
import arc.util.noise.Ridged;
import arc.util.noise.Simplex;
import mindustry.Vars;
import mindustry.ai.Astar;
import mindustry.ai.BaseRegistry;
import mindustry.content.Blocks;
import mindustry.content.Liquids;
import mindustry.game.Schematics;
import mindustry.game.Team;
import mindustry.game.Waves;
import mindustry.graphics.g3d.PlanetGrid;
import mindustry.maps.generators.BaseGenerator;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.type.Sector;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.TileGen;
import mindustry.world.Tiles;
import mindustry.world.blocks.environment.Floor;

public class NauvisPlanetGenerator extends PlanetGenerator {
    public static boolean alt = false;

    BaseGenerator basegen = new BaseGenerator();

    float scl = 5.0F;

    float waterOffset = 0.07F;

    Block[][] arr = new Block[][]{
        {Blocks.water, Blocks.water, Blocks.grass, Blocks.grass, Blocks.grass, Blocks.grass, Blocks.dirt, Blocks.dirt, Blocks.dirt, Blocks.grass, Blocks.water, Blocks.stone, Blocks.stone},
        {Blocks.water, Blocks.water, Blocks.grass, Blocks.grass, Blocks.grass, Blocks.grass, Blocks.grass, Blocks.mud, Blocks.mud, Blocks.water, Blocks.stone, Blocks.stone, Blocks.stone},
        {Blocks.water, Blocks.water, Blocks.grass, Blocks.dirt, Blocks.salt, Blocks.grass, Blocks.grass, Blocks.sand, Blocks.sand, Blocks.sandWater, Blocks.stone, Blocks.stone, Blocks.stone},
        {Blocks.water, Blocks.sandWater, Blocks.grass, Blocks.salt, Blocks.salt, Blocks.salt, Blocks.grass, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.snow, Blocks.iceSnow, Blocks.ice},
        {Blocks.deepwater, Blocks.water, Blocks.sandWater, Blocks.grass, Blocks.salt, Blocks.grass, Blocks.mud, Blocks.basalt, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.ice},
        {Blocks.deepwater, Blocks.water, Blocks.sandWater, Blocks.mud, Blocks.grass, Blocks.grass, Blocks.grass, Blocks.iceSnow, Blocks.snow, Blocks.snow, Blocks.ice, Blocks.snow, Blocks.ice},
        {Blocks.deepwater, Blocks.sandWater, Blocks.grass, Blocks.grass, Blocks.grass, Blocks.grass, Blocks.snow, Blocks.basalt, Blocks.basalt, Blocks.basalt, Blocks.ice, Blocks.snow, Blocks.ice},
        {Blocks.deepwater, Blocks.sandWater, Blocks.grass, Blocks.grass, Blocks.basalt, Blocks.grass, Blocks.basalt, Blocks.hotrock, Blocks.basalt, Blocks.ice, Blocks.snow, Blocks.ice, Blocks.ice},
        {Blocks.water, Blocks.grass, Blocks.grass, Blocks.grass, Blocks.grass, Blocks.dirt, Blocks.snow, Blocks.basalt, Blocks.basalt, Blocks.ice, Blocks.snow, Blocks.ice, Blocks.ice},
        {Blocks.water, Blocks.grass, Blocks.grass, Blocks.dirt, Blocks.ice, Blocks.ice, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.ice, Blocks.ice, Blocks.ice},
        {Blocks.deepwater, Blocks.sandWater, Blocks.grass, Blocks.dirt, Blocks.dirt, Blocks.ice, Blocks.ice, Blocks.snow, Blocks.snow, Blocks.ice, Blocks.ice, Blocks.ice, Blocks.ice},
        {Blocks.water, Blocks.sandWater, Blocks.grass, Blocks.dirt, Blocks.grass, Blocks.dirt, Blocks.iceSnow, Blocks.snow, Blocks.ice, Blocks.ice, Blocks.ice, Blocks.ice, Blocks.ice},
        {Blocks.water, Blocks.grass, Blocks.snow, Blocks.ice, Blocks.iceSnow, Blocks.snow, Blocks.snow, Blocks.snow, Blocks.ice, Blocks.ice, Blocks.ice, Blocks.ice, Blocks.ice}};

    float water = 2.0F / (this.arr[0]).length;

    float rawHeight(Vec3 position) {
        position = Tmp.v33.set(position).scl(this.scl);
        return (Mathf.pow(Simplex.noise3d(this.seed, 7.0D, 0.5D, 0.3333333432674408D, position.x, position.y, position.z), 2.3F) + this.waterOffset) / (1.0F + this.waterOffset);
    }

    public void generateSector(Sector sector) {
        if (sector.id == 154 || sector.id == 0) {
            sector.generateEnemyBase = true;
            return;
        }
        PlanetGrid.Ptile tile = sector.tile;
        boolean any = false;
        float poles = Math.abs(tile.v.y);
        float noise = Noise.snoise3(tile.v.x, tile.v.y, tile.v.z, 0.001F, 0.58F);
        if (noise + poles / 7.1D > 0.12D && poles > 0.23D)
            any = true;
        if (noise < 0.16D)
            for (PlanetGrid.Ptile other : tile.tiles) {
                Sector osec = sector.planet.getSector(other);
                if (osec.id == sector.planet.startSector || (osec.generateEnemyBase && poles < 0.85D) || (sector.preset != null && noise < 0.11D))
                    return;
            }
        sector.generateEnemyBase = any;
    }

    public float getHeight(Vec3 position) {
        float height = rawHeight(position);
        return Math.max(height, this.water);
    }

    public Color getColor(Vec3 position) {
        Block block = getBlock(position);
        return Tmp.c1.set(block.mapColor).a(1.0F - block.albedo);
    }

    public void genTile(Vec3 position, TileGen tile) {
        tile.floor = getBlock(position);
        tile.block = (tile.floor.asFloor()).wall;
        if (Ridged.noise3d(this.seed + 1, position.x, position.y, position.z, 2, 22.0F) > 0.31D)
            tile.block = Blocks.air;
    }

    Block getBlock(Vec3 position) {
        float height = rawHeight(position);
        Tmp.v31.set(position);
        position = Tmp.v33.set(position).scl(this.scl);
        float rad = this.scl;
        float temp = Mathf.clamp(Math.abs(position.y * 2.0F) / rad);
        float tnoise = Simplex.noise3d(this.seed, 7.0D, 0.56D, 0.3333333432674408D, position.x, (position.y + 999.0F), position.z);
        temp = Mathf.lerp(temp, tnoise, 0.5F);
        height *= 1.2F;
        height = Mathf.clamp(height);
        return this.arr[Mathf.clamp((int) (temp * this.arr.length), 0, (this.arr[0]).length - 1)][Mathf.clamp((int) (height * (this.arr[0]).length), 0, (this.arr[0]).length - 1)];
    }

    protected float noise(float x, float y, double octaves, double falloff, double scl, double mag) {
        Vec3 v = this.sector.rect.project(x, y).scl(5.0F);
        return Simplex.noise3d(this.seed, octaves, falloff, 1.0D / scl, v.x, v.y, v.z) * (float) mag;
    }

    protected void generate() {
        class Room {
            final int x;

            final int y;

            final int radius;

            final ObjectSet<Room> connected = new ObjectSet<>();

            Room(int x, int y, int radius) {
                this.x = x;
                this.y = y;
                this.radius = radius;
                this.connected.add(this);
            }

            void join(int x1, int y1, int x2, int y2) {
                float nscl = NauvisPlanetGenerator.this.rand.random(100.0F, 140.0F) * 6.0F;
                int stroke = NauvisPlanetGenerator.this.rand.random(3, 9);
                NauvisPlanetGenerator.this.brush(NauvisPlanetGenerator.this.pathfind(x1, y1, x2, y2, tile -> (tile.solid() ? 50.0F : 0.0F) + NauvisPlanetGenerator.this.noise(tile.x, tile.y, 2.0D, 0.4000000059604645D, (1.0F / nscl)) * 500.0F, Astar.manhattan), stroke);
            }

            void connect(Room to) {
                if (!this.connected.add(to) || to == this)
                    return;
                Vec2 midpoint = Tmp.v1.set(to.x, to.y).add(this.x, this.y).scl(0.5F);
                NauvisPlanetGenerator.this.rand.nextFloat();
                if (NauvisPlanetGenerator.alt) {
                    midpoint.add(Tmp.v2.set(1.0F, 0.0F).setAngle(Angles.angle(to.x, to.y, this.x, this.y) + 90.0F * (NauvisPlanetGenerator.this.rand.chance(0.5D) ? 1.0F : -1.0F)).scl(Tmp.v1.dst(this.x, this.y) * 2.0F));
                } else {
                    midpoint.add(Tmp.v2.setToRandomDirection(NauvisPlanetGenerator.this.rand).scl(Tmp.v1.dst(this.x, this.y)));
                }
                midpoint.sub(NauvisPlanetGenerator.this.width / 2.0F, NauvisPlanetGenerator.this.height / 2.0F).limit(NauvisPlanetGenerator.this.width / 2.0F / Mathf.sqrt3).add(NauvisPlanetGenerator.this.width / 2.0F, NauvisPlanetGenerator.this.height / 2.0F);
                int mx = (int) midpoint.x, my = (int) midpoint.y;
                join(this.x, this.y, mx, my);
                join(mx, my, to.x, to.y);
            }

            void joinLiquid(int x1, int y1, int x2, int y2) {
                float nscl = NauvisPlanetGenerator.this.rand.random(100.0F, 140.0F) * 6.0F;
                int rad = NauvisPlanetGenerator.this.rand.random(7, 11);
                int avoid = 2 + rad;
                Seq<Tile> path = NauvisPlanetGenerator.this.pathfind(x1, y1, x2, y2, tile -> ((tile.solid() || !(tile.floor()).isLiquid) ? 70.0F : 0.0F) + NauvisPlanetGenerator.this.noise(tile.x, tile.y, 2.0D, 0.4000000059604645D, (1.0F / nscl)) * 500.0F, Astar.manhattan);
                path.each(t -> {
                    if (Mathf.dst2(t.x, t.y, x2, y2) <= (avoid * avoid))
                        return;
                    for (int x = -rad; x <= rad; x++) {
                        for (int y = -rad; y <= rad; y++) {
                            int wx = t.x + x;
                            int wy = t.y + y;
                            if (Structs.inBounds(wx, wy, NauvisPlanetGenerator.this.width, NauvisPlanetGenerator.this.height) && Mathf.within(x, y, rad)) {
                                Tile other = NauvisPlanetGenerator.this.tiles.getn(wx, wy);
                                other.setBlock(Blocks.air);
                                if (Mathf.within(x, y, (rad - 1)) && !(other.floor()).isLiquid) {
                                    other.setFloor((Floor) Blocks.sandWater);
                                }
                            }
                        }
                    }
                });
            }

            void connectLiquid(Room to) {
                if (to == this)
                    return;
                Vec2 midpoint = Tmp.v1.set(to.x, to.y).add(this.x, this.y).scl(0.5F);
                NauvisPlanetGenerator.this.rand.nextFloat();
                midpoint.add(Tmp.v2.setToRandomDirection(NauvisPlanetGenerator.this.rand).scl(Tmp.v1.dst(this.x, this.y)));
                midpoint.sub(NauvisPlanetGenerator.this.width / 2.0F, NauvisPlanetGenerator.this.height / 2.0F).limit(NauvisPlanetGenerator.this.width / 2.0F / Mathf.sqrt3).add(NauvisPlanetGenerator.this.width / 2.0F, NauvisPlanetGenerator.this.height / 2.0F);
                int mx = (int) midpoint.x, my = (int) midpoint.y;
                joinLiquid(this.x, this.y, mx, my);
                joinLiquid(mx, my, to.x, to.y);
            }
        }
        cells(4);
        distort(10.0F, 12.0F);
        float constraint = 1.3F;
        float radius = this.width / 2.0F / Mathf.sqrt3;
        int rooms = this.rand.random(2, 5);
        Seq<Room> roomseq = new Seq<>();
        for (int i = 0; i < rooms; i++) {
            Tmp.v1.trns(this.rand.random(360.0F), this.rand.random(radius / constraint));
            float rx = this.width / 2.0F + Tmp.v1.x;
            float ry = this.height / 2.0F + Tmp.v1.y;
            float maxrad = radius - Tmp.v1.len();
            float rrad = Math.min(this.rand.random(9.0F, maxrad / 2.0F), 30.0F);
            roomseq.add(new Room((int) rx, (int) ry, (int) rrad));
        }
        Room spawn = null;
        Seq<Room> enemies = new Seq<>();
        int enemySpawns = this.rand.random(1, Math.max((int) (this.sector.threat * 4.0F), 1));
        int offset = this.rand.nextInt(360);
        float length = this.width / 2.55F - this.rand.random(13, 23);
        int angleStep = 5;
        int waterCheckRad = 5;
        int j;
        for (j = 0; j < 360; j += angleStep) {
            int angle = offset + j;
            int cx = (int) ((this.width / 2) + Angles.trnsx(angle, length));
            int cy = (int) ((this.height / 2) + Angles.trnsy(angle, length));
            int waterTiles = 0;
            for (int rx = -waterCheckRad; rx <= waterCheckRad; rx++) {
                for (int ry = -waterCheckRad; ry <= waterCheckRad; ry++) {
                    Tile tile = this.tiles.get(cx + rx, cy + ry);
                    if (tile == null || (tile.floor()).liquidDrop != null)
                        waterTiles++;
                }
            }
            if (waterTiles <= 4 || j + angleStep >= 360) {
                roomseq.add(spawn = new Room(cx, cy, this.rand.random(8, 15)));
                for (int i1 = 0; i1 < enemySpawns; i1++) {
                    float enemyOffset = this.rand.range(60.0F);
                    Tmp.v1.set((cx - (float) this.width / 2), (cy - (float) this.height / 2)).rotate(180.0F + enemyOffset).add(((float) this.width / 2), ((float) this.height / 2));
                    Room espawn = new Room((int) Tmp.v1.x, (int) Tmp.v1.y, this.rand.random(8, 16));
                    roomseq.add(espawn);
                    enemies.add(espawn);
                }
                break;
            }
        }
        for (Room room : roomseq)
            erase(room.x, room.y, room.radius);
        int connections = this.rand.random(Math.max(rooms - 1, 1), rooms + 3);
        for (int k = 0; k < connections; k++)
            roomseq.random(this.rand).connect(roomseq.random(this.rand));
        for (Room room : roomseq)
            if (spawn != null) {
                spawn.connect(room);
            }
        Room fspawn = spawn;
        cells(1);
        int tlen = this.tiles.width * this.tiles.height;
        int total = 0, waters = 0;
        for (int m = 0; m < tlen; m++) {
            Tile tile = this.tiles.geti(m);
            if (tile.block() == Blocks.air) {
                total++;
                if ((tile.floor()).liquidDrop == Liquids.water)
                    waters++;
            }
        }
        boolean naval = ((float) waters / total >= 0.19F);
        if (naval)
            for (Room room : enemies)
                room.connectLiquid(spawn);
        distort(10.0F, 6.0F);
        pass((x, y) -> {
            if (this.block.solid)
                return;
            Vec3 v = this.sector.rect.project(x, y);
            float rr = Simplex.noise2d(this.sector.id, 2.0D, 0.6000000238418579D, 0.1428571492433548D, x, y) * 0.1F;
            float value = Ridged.noise3d(2, v.x, v.y, v.z, 1, 0.018181818F) + rr - rawHeight(v) * 0.0F;
            float rrscl = rr * 44.0F - 2.0F;
            if (value > 0.17F && !Mathf.within(x, y, fspawn.x, fspawn.y, 12.0F + rrscl)) {
                boolean deep = (value > 0.27F && !Mathf.within(x, y, fspawn.x, fspawn.y, 15.0F + rrscl));
                if (this.floor != Blocks.ice && this.floor != Blocks.iceSnow && this.floor != Blocks.snow && !(this.floor.asFloor()).isLiquid)
                    this.floor = deep ? Blocks.water : Blocks.sandWater;
            }
        });
        pass((x, y) -> {
            int deepRadius = 3;
            if ((this.floor.asFloor()).isLiquid && (this.floor.asFloor()).shallow) {
                for (int cx = -deepRadius; cx <= deepRadius; cx++) {
                    for (int cy = -deepRadius; cy <= deepRadius; cy++) {
                        if (cx * cx + cy * cy <= deepRadius * deepRadius) {
                            int wx = cx + x;
                            int wy = cy + y;
                            Tile tile = this.tiles.get(wx, wy);
                            if (tile != null && (!(tile.floor()).isLiquid || tile.block() != Blocks.air))
                                return;
                        }
                    }
                }
                this.floor = Blocks.water;
            }
        });
        if (naval) {
            int deepRadius = 2;
            pass((x, y) -> {
                if ((this.floor.asFloor()).isLiquid && !this.floor.asFloor().isDeep() && !(this.floor.asFloor()).shallow) {
                    for (int cx = -deepRadius; cx <= deepRadius; cx++) {
                        for (int cy = -deepRadius; cy <= deepRadius; cy++) {
                            if (cx * cx + cy * cy <= deepRadius * deepRadius) {
                                int wx = cx + x;
                                int wy = cy + y;
                                Tile tile = this.tiles.get(wx, wy);
                                if (tile != null && ((tile.floor()).shallow || !(tile.floor()).isLiquid))
                                    return;
                            }
                        }
                    }
                    this.floor = (this.floor == Blocks.water) ? Blocks.deepwater : Blocks.taintedWater;
                }
            });
        }
        Seq<Block> ores = Seq.with(Blocks.oreCopper, Blocks.oreLead);
        float poles = Math.abs(this.sector.tile.v.y);
        float nmag = 0.5F;
        float scl = 1.0F;
        float addscl = 1.3F;
        if (Simplex.noise3d(this.seed, 2.0D, 0.5D, scl, this.sector.tile.v.x, this.sector.tile.v.y, this.sector.tile.v.z) * nmag + poles > 0.25F * addscl)
            ores.add(Blocks.oreCoal);
        if (Simplex.noise3d(this.seed, 2.0D, 0.5D, scl, (this.sector.tile.v.x + 1.0F), this.sector.tile.v.y, this.sector.tile.v.z) * nmag + poles > 0.5F * addscl)
            ores.add(Blocks.oreTitanium);
        if (Simplex.noise3d(this.seed, 2.0D, 0.5D, scl, (this.sector.tile.v.x + 2.0F), this.sector.tile.v.y, this.sector.tile.v.z) * nmag + poles > 0.7F * addscl)
            ores.add(Blocks.oreThorium);
        if (this.rand.chance(0.25D))
            ores.add(Blocks.oreScrap);
        FloatSeq frequencies = new FloatSeq();
        for (int n = 0; n < ores.size; n++)
            frequencies.add(this.rand.random(-0.1F, 0.01F) - n * 0.01F + poles * 0.04F);
        pass((x, y) -> {
            if (!this.floor.asFloor().hasSurface())
                return;
            int offsetX = x - 4;
            int offsetY = y + 23;
            for (int i = ores.size - 1; i >= 0; i--) {
                Block entry = ores.get(i);
                float freq = frequencies.get(i);
                if (Math.abs(0.5F - noise(offsetX, (offsetY + i * 999), 2.0D, 0.7D, (40 + i * 2))) > 0.2199999988079071D + i * 0.01D && Math.abs(0.5F - noise(offsetX, (offsetY - i * 999), 1.0D, 1.0D, (30 + i * 4))) > 0.37F + freq) {
                    this.ore = entry;
                    break;
                }
            }
            if (this.ore == Blocks.oreScrap && this.rand.chance(0.33D))
                this.floor = Blocks.metalFloorDamaged;
        });
        trimDark();
        median(2);
        inverseFloodFill(this.tiles.getn(spawn.x, spawn.y));
        tech();
        pass((x, y) -> {
            if (this.rand.chance(0.0075D)) {
                boolean any = false;
                boolean all = true;
                for (Point2 p : Geometry.d4) {
                    Tile other = this.tiles.get(x + p.x, y + p.y);
                    if (other != null && other.block() == Blocks.air) {
                        any = true;
                    } else {
                        all = false;
                    }
                }
                if (any && (this.block == Blocks.snowWall || this.block == Blocks.iceWall || (all && this.block == Blocks.air && this.floor == Blocks.snow && this.rand.chance(0.03D))))
                    this.block = this.rand.chance(0.5D) ? Blocks.whiteTree : Blocks.whiteTreeDead;
            }
            for (int i = 0; i < 4; i++) {
                Tile near = Vars.world.tile(x + (Geometry.d4[i]).x, y + (Geometry.d4[i]).y);
                if (near != null && near.block() != Blocks.air)
                    break;
            }
        });
        float difficulty = this.sector.threat;
        this.ints.clear();
        this.ints.ensureCapacity(this.width * this.height / 4);
        int ruinCount = this.rand.random(-2, 4);
        if (ruinCount > 0) {
            int padding = 25;
            for (int x = padding; x < this.width - padding; x++) {
                for (int y = padding; y < this.height - padding; y++) {
                    Tile tile = this.tiles.getn(x, y);
                    if (!tile.solid() && (tile.drop() != null || (tile.floor()).liquidDrop != null))
                        this.ints.add(tile.pos());
                }
            }
            this.ints.shuffle(this.rand);
            int placed = 0;
            float diffRange = 0.4F;
            for (int i1 = 0; i1 < this.ints.size && placed < ruinCount; i1++) {
                int val = this.ints.items[i1];
                int x = Point2.x(val), y = Point2.y(val);
                if (!Mathf.within(x, y, spawn.x, spawn.y, 18.0F)) {
                    float range = difficulty + this.rand.random(diffRange);
                    Tile tile = this.tiles.getn(x, y);
                    BaseRegistry.BasePart part = null;
                    if ((tile.overlay()).itemDrop != null) {
                        part = Vars.bases.forResource(tile.drop()).getFrac(range);
                    } else if ((tile.floor()).liquidDrop != null && this.rand.chance(0.05D)) {
                        part = Vars.bases.forResource((tile.floor()).liquidDrop).getFrac(range);
                    } else if (this.rand.chance(0.05D)) {
                        part = Vars.bases.parts.getFrac(range);
                    }
                    if (part != null && BaseGenerator.tryPlace(part, x, y, Team.derelict, (cx, cy) -> {
                        Tile other = this.tiles.getn(cx, cy);
                        if (other.floor().hasSurface()) {
                            other.setOverlay(Blocks.oreScrap);
                            for (int i = 1; i <= 2; i++) {
                                for (Point2 p : Geometry.d8) {
                                    Tile t = this.tiles.get(cx + p.x * i, cy + p.y * i);
                                    if (t != null && t.floor().hasSurface() && this.rand.chance((i == 1) ? 0.4D : 0.2D))
                                        t.setOverlay(Blocks.oreScrap);
                                }
                            }
                        }
                    })) {
                        placed++;
                        int debrisRadius = Math.max(part.schematic.width, part.schematic.height) / 2 + 3;
                        Geometry.circle(x, y, this.tiles.width, this.tiles.height, debrisRadius, (cx, cy) -> {
                            float dst = Mathf.dst(cx, cy, x, y);
                            float removeChance = Mathf.lerp(0.05F, 0.5F, dst / debrisRadius);
                            Tile other = this.tiles.getn(cx, cy);
                            if (other.build != null && other.isCenter())
                                if (other.team() == Team.derelict && this.rand.chance(removeChance)) {
                                    other.remove();
                                } else if (this.rand.chance(0.5D)) {
                                    other.build.health -= this.rand.random(other.build.health * 0.9F);
                                }
                        });
                    }
                }
            }
        }
        for (Tile tile : this.tiles) {
            if ((tile.overlay()).needsSurface && !tile.floor().hasSurface())
                tile.setOverlay(Blocks.air);
        }
        Schematics.placeLaunchLoadout(spawn.x, spawn.y);
        for (Room espawn : enemies)
            this.tiles.getn(espawn.x, espawn.y).setOverlay(Blocks.spawn);
        if (this.sector.hasEnemyBase()) {
            this.basegen.generate(this.tiles, enemies.map(r -> this.tiles.getn(r.x, r.y)), this.tiles.get(spawn.x, spawn.y), Vars.state.rules.waveTeam, this.sector, difficulty);
            Vars.state.rules.attackMode = this.sector.info.attack = true;
        } else {
            Vars.state.rules.winWave = this.sector.info.winWave = 10 + 5 * (int) Math.max(difficulty * 10.0F, 1.0F);
        }
        float waveTimeDec = 0.4F;
        Vars.state.rules.waveSpacing = Mathf.lerp(7800.0F, 3600.0F, Math.max(difficulty - waveTimeDec, 0.0F));
        Vars.state.rules.waves = true;
        Vars.state.rules.env = this.sector.planet.defaultEnv;
        Vars.state.rules.enemyCoreBuildRadius = 600.0F;
        Vars.state.rules.spawns = Waves.generate(difficulty, new Rand(this.sector.id), Vars.state.rules.attackMode, (Vars.state.rules.attackMode && Vars.spawner.countGroundSpawns() == 0), naval);
    }

    public void postGenerate(Tiles tiles) {
        if (this.sector.hasEnemyBase()) {
            this.basegen.postGenerate();
            if (Vars.spawner.countGroundSpawns() == 0)
                Vars.state.rules.spawns = Waves.generate(this.sector.threat, new Rand(this.sector.id), Vars.state.rules.attackMode, true, false);
        }
    }
}
