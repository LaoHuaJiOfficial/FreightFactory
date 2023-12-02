package world.block.production;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Geometry;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.struct.EnumSet;
import arc.struct.ObjectFloatMap;
import arc.struct.ObjectIntMap;
import arc.struct.Seq;
import arc.util.Eachable;
import arc.util.Nullable;
import arc.util.Time;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.entities.units.BuildPlan;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Sounds;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.logic.LAccess;
import mindustry.type.Item;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.consumers.ConsumeLiquidBase;
import mindustry.world.meta.*;
import world.block.storage.RemoteCoreBlock;

import static mindustry.Vars.*;

public class ScanMiner extends Block {

    public TextureRegion standRegion, beamRegion, rotatorRegion;
    protected final ObjectIntMap<Item> oreCount = new ObjectIntMap<>();
    protected final Seq<Item> itemArray = new Seq<>();
    public float hardnessDrillMultiplier = 50f;

    /**
     * Maximum tier of blocks this drill can mine.
     */
    public int tier = 5;
    /**
     * Base time to drill one ore, in frames.
     */
    public float drillTime = 300;
    /**
     * How many times faster the drill will progress when boosted by liquid.
     */
    public float liquidBoostIntensity = 1.6f;
    /**
     * Speed at which the drill speeds up.
     */
    public float warmupSpeed = 0.015f;
    /**
     * Special exemption item that this drill can't mine.
     */
    public @Nullable Item blockedItem;
    /**
     * Whether to draw the item this drill is mining.
     */
    public boolean drawMineItem = true;
    /**
     * Effect played when an item is produced. This is colored.
     */
    public Effect drillEffect = Fx.mine;
    /**
     * Drill effect randomness. Block size by default.
     */
    public float drillEffectRnd = -1f;
    /**
     * Chance the update effect will appear.
     */
    public float updateEffectChance = 0.02f;
    /**
     * Multipliers of drill speed for each item. Defaults to 1.
     */
    public ObjectFloatMap<Item> drillMultipliers = new ObjectFloatMap<>();
    //return variables for countOre
    protected @Nullable Item returnItem;
    protected int returnCount;

    public ScanMiner(String name) {
        super(name);
        rotate = true;
        update = true;
        solid = true;
        group = BlockGroup.drills;
        hasLiquids = true;
        liquidCapacity = 5f;
        hasItems = true;
        ambientSound = Sounds.drill;
        ambientSoundVolume = 0.018f;
        //drills work in space I guess
        envEnabled |= Env.space;
        flags = EnumSet.of(BlockFlag.drill);

        config(Item.class, (RemoteCoreBlock.RemoteCoreBuild tile, Item item) -> tile.sortItem = item);
        configClear((RemoteCoreBlock.RemoteCoreBuild tile) -> tile.sortItem = null);
    }

    @Override
    public void load(){
        super.load();
        standRegion = Core.atlas.find(name + "-stand");
        beamRegion = Core.atlas.find(name + "-beam");
        rotatorRegion = Core.atlas.find(name + "-rotator");
    }

    @Override
    public void init() {
        super.init();
        updateClipRadius(size * tilesize);
        if (drillEffectRnd < 0) drillEffectRnd = size;
    }

    @Override
    public void drawPlanConfigTop(BuildPlan plan, Eachable<BuildPlan> list) {
        if (!plan.worldContext) return;
        Tile tile = plan.tile();
        if (tile == null) return;

        countOre(tile, plan.rotation);
        if (returnItem == null || !drawMineItem) return;

        Draw.color();
    }

    @Override
    public void setBars() {
        super.setBars();
    }

    public Item getDrop(Tile tile) {
        return tile.drop();
    }

    public Rect getRect(Rect rect, float x, float y, int rotation) {

        x = (size % 2 == 0 ? (x + 0.5f) * tilesize : x * tilesize);
        y = (size % 2 == 0 ? (y + 0.5f) * tilesize : y * tilesize);

        rect.setCentered(x, y, size * tilesize);
        float len = size * tilesize;

        rect.x += Geometry.d4x(rotation) * len;
        rect.y += Geometry.d4y(rotation) * len;

        return rect;
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);

        Tile tile = world.tile(x, y);
        if (tile == null) return;

        countOre(tile, rotation);

        if (returnItem != null) {
            float width = drawPlaceText(Core.bundle.formatFloat("bar.drillspeed", 60f / getDrillTime(returnItem) * returnCount, 2), x, y, valid);
            float dx = x * tilesize + offset - width / 2f - 4f, dy = y * tilesize + offset + size * tilesize / 2f + 5, s = iconSmall / 4f;
            Draw.mixcol(Color.darkGray, 1f);
            Draw.rect(returnItem.fullIcon, dx, dy - 1, s, s);
            Draw.reset();
            Draw.rect(returnItem.fullIcon, dx, dy, s, s);
        } else {
            Tile to = tile.getLinkedTilesAs(this, tempTiles).find(t -> t.drop() != null && (t.drop().hardness > tier || t.drop() == blockedItem));
            Item item = to == null ? null : to.drop();
            if (item != null) {
                drawPlaceText(Core.bundle.get("bar.drilltierreq"), x, y, valid);
            }
        }

        Rect rect = getRect(Tmp.r1, x, y, rotation);

        Drawf.dashRect(valid ? returnItem.color : Pal.remove, rect);
    }

    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        countOre(tile, rotation);

        return itemArray.size != 0;
    }

    public float getDrillTime(Item item) {
        return (drillTime + hardnessDrillMultiplier * item.hardness) / drillMultipliers.get(item, 1f);
    }

    @Override
    public void setStats() {
        super.setStats();

        stats.add(Stat.drillTier, StatValues.drillables(drillTime, hardnessDrillMultiplier, size * size, drillMultipliers, b -> b instanceof Floor f && !f.wallOre && f.itemDrop != null &&
            f.itemDrop.hardness <= tier && f.itemDrop != blockedItem && (indexer.isBlockPresent(f) || state.isMenu())));

        stats.add(Stat.drillSpeed, 60f / drillTime * size * size, StatUnit.itemsSecond);

        if (liquidBoostIntensity != 1 && findConsumer(f -> f instanceof ConsumeLiquidBase) instanceof ConsumeLiquidBase consBase) {
            stats.remove(Stat.booster);
            stats.add(Stat.booster,
                StatValues.speedBoosters("{0}" + StatUnit.timesSpeed.localized(),
                    consBase.amount,
                    liquidBoostIntensity * liquidBoostIntensity, false, this::consumesLiquid)
            );
        }
    }

    @Override
    public TextureRegion[] icons() {
        return new TextureRegion[]{region};
    }

    protected void countOre(Tile tile, int rotation) {
        returnItem = null;
        returnCount = 0;

        oreCount.clear();
        itemArray.clear();

        int area = 4;

        int offset = (size % 2 == 0 ? size / 2 - 1 : size / 2);
        int tx = tile.x - offset, ty = tile.y - offset;
        for (int x = 0; x < area; x++) {
            for (int y = 0; y < area; y++) {
                Tile other = Vars.world.tile(tx + Geometry.d4x(rotation) * size + x, ty + Geometry.d4y(rotation) * size + y);;

                if (other != null && canMine(other)) {
                    oreCount.increment(getDrop(other), 0, 1);
                }
            }
        }

        for (Item item : oreCount.keys()) {
            itemArray.add(item);
        }

        itemArray.sort((item1, item2) -> {
            int type = Boolean.compare(!item1.lowPriority, !item2.lowPriority);
            if (type != 0) return type;
            int amounts = Integer.compare(oreCount.get(item1, 0), oreCount.get(item2, 0));
            if (amounts != 0) return amounts;
            return Integer.compare(item1.id, item2.id);
        });

        if (itemArray.size == 0) {
            return;
        }

        returnItem = itemArray.peek();
        returnCount = oreCount.get(itemArray.peek(), 0);
    }

    public boolean canMine(Tile tile) {
        if (tile == null || tile.block().isStatic()) return false;
        Item drops = tile.drop();
        return drops != null && drops.hardness <= tier && drops != blockedItem;
    }

    public class ScanMinerBuild extends Building {

        public float charge = Mathf.random(60);

        public Item sortItem = null;
        public float progress;
        public float warmup;
        public float timeDrilled;
        public float lastDrillSpeed;

        public int dominantItems;
        public Item dominantItem;

        public Vec2 getScanCenter() {
            float len = tilesize * size;
            float scanX = x + Geometry.d4x(rotation) * len, scanY = y + Geometry.d4y(rotation) * len;
            return Tmp.v4.set(scanX, scanY);
        }

        //TODO Later
        public boolean checkSolid(Vec2 v) {
            float rectRad = size;
            return Units.anyEntities(v.x - rectRad / 2f, v.y - rectRad / 2f, rectRad, rectRad);
        }


        @Override
        public Item config() {
            return sortItem;
        }

        @Override
        public boolean shouldConsume() {
            return items.total() < itemCapacity && enabled;
        }

        @Override
        public boolean shouldAmbientSound() {
            return efficiency > 0.01f && items.total() < itemCapacity;
        }

        @Override
        public float ambientVolume() {
            return efficiency * (size * size) / 4f;
        }

        @Override
        public void drawSelect() {
            if (dominantItem != null) {
                float dx = x - size * tilesize / 2f, dy = y + size * tilesize / 2f, s = iconSmall / 4f;
                Draw.mixcol(Color.darkGray, 1f);
                Draw.rect(dominantItem.fullIcon, dx, dy - 1, s, s);
                Draw.reset();
                Draw.rect(dominantItem.fullIcon, dx, dy, s, s);
                Draw.reset();
                Drawf.dashRect(dominantItem.color, getRect(Tmp.r1, tile.worldx(), tile.worldy(), rotation));
            }
        }

        @Override
        public void pickedUp() {
            dominantItem = null;
        }

        @Override
        public void onProximityUpdate() {
            super.onProximityUpdate();

            countOre(tile, rotation);
            dominantItem = returnItem;
            dominantItems = returnCount;
        }

        @Override
        public Object senseObject(LAccess sensor) {
            if (sensor == LAccess.firstItem) return dominantItem;
            return super.senseObject(sensor);
        }

        @Override
        public void updateTile() {

            Vec2 scanCenter = getScanCenter();
            //boolean wasOccupied = checkSolid(scanCenter);

            if (timer(timerDump, dumpTime)) {
                dump(dominantItem != null && items.has(dominantItem) ? dominantItem : null);
            }

            if (dominantItem == null) {
                return;
            }

            timeDrilled += warmup * delta();

            float delay = getDrillTime(dominantItem);

            if (items.total() < itemCapacity && dominantItems > 0 && efficiency > 0) {
                float speed = Mathf.lerp(1f, liquidBoostIntensity, optionalEfficiency) * efficiency;

                lastDrillSpeed = (speed * dominantItems * warmup) / delay;
                warmup = Mathf.approachDelta(warmup, speed, warmupSpeed);
                progress += delta() * dominantItems * speed * warmup;


                if (Mathf.chanceDelta(0.3 * warmup)) {
                    //updateEffect.at(x + Mathf.range(size * 2f), y + Mathf.range(size * 2f));
                    //FFFx.ScanMinerScanEffect.at(x, y, rotation);
                }
            } else {
                lastDrillSpeed = 0f;
                warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
                return;
            }

            if (dominantItems > 0 && progress >= delay && items.total() < itemCapacity) {
                offload(dominantItem);

                progress %= delay;

                if (wasVisible && Mathf.chanceDelta(updateEffectChance * warmup))
                    drillEffect.at(x + Mathf.range(drillEffectRnd), y + Mathf.range(drillEffectRnd), dominantItem.color);
            }

            charge += delta();
        }

        @Override
        public float progress() {
            return dominantItem == null ? 0f : Mathf.clamp(progress / getDrillTime(dominantItem));
        }

        @Override
        public double sense(LAccess sensor) {
            if (sensor == LAccess.progress && dominantItem != null) return progress;
            return super.sense(sensor);
        }

        @Override
        public void drawCracks() {
        }

        public void drawDefaultCracks() {
            super.drawCracks();
        }

        @Override
        public void draw() {

            /*
            for(int i = 0; i < 3 ; i++){
                for (int j : Mathf.signs){
                    Draw.rect(rotatorRegion, cx + bx + shift * Geometry.d4y(rotation) * j, cy + by + shift * Geometry.d4x(rotation) * j, rotation + 120f * i + Time.time * 2f + j * 30);
                }
            }
            Draw.rect(beamRegion, cx + bx, cy + by);
            Draw.rect(standRegion, cx, cy);
            */
            float cx = Geometry.d4x(rotation) * size * tilesize;
            float cy = Geometry.d4y(rotation) * size * tilesize;
            float shift = Mathf.sin(Time.time * 0.05f) * size * tilesize * 0.5f;

            Draw.rect(region, x, y);
            Draw.z(Layer.blockCracks);
            drawDefaultCracks();

            if (dominantItem != null) {
                Draw.color(dominantItem.color);
                Lines.stroke((0.8f + Mathf.sin(totalProgress() * 0.1f) * 0.2f) * warmup);
                Lines.square(x + cx, y + cy, 15, 45);

                for (int i : Mathf.signs){
                    Lines.lineAngle(
                        x + cx / 2f + i * Geometry.d4y(rotation) * 16f,
                        y + cy / 2f + i * Geometry.d4x(rotation) * 16f,
                        rotation * 90, size * tilesize);


                    Draw.reset();

                    Lines.lineAngle(
                        x + cx + Mathf.cosDeg(rotation * 90) * shift,
                        y + cy + Mathf.sinDeg(rotation * 90) * shift,
                        rotation * 90 * i + 90,
                        size * tilesize * 0.5f);
                }



                /*

                for(int i = 0; i < 2; i++){
                    LinesUtil.chainLighting(
                        x + cx / 2f + Geometry.d4y(rotation) * 16f,
                        y + cy / 2f + Geometry.d4x(rotation) * 16f,
                        x + cx / 2f - Geometry.d4y(rotation) * 16f,
                        y + cy / 2f - Geometry.d4x(rotation) * 16f,
                        5f,3f, (long) (id * (i + 1) * Time.time),
                        40, (0.8f + Mathf.sin(totalProgress() * 0.1f) * 0.2f) * warmup
                    );
                }

                int area = 4;

                tileArray.clear();

                int offset = (size % 2 == 0 ? size / 2 - 1 : size / 2);
                int tx = tile.x - offset, ty = tile.y - offset;
                for (int x = 0; x < area; x++) {
                    for (int y = 0; y < area; y++) {
                        timer();
                        Tile other = Vars.world.tile(tx + Geometry.d4x(rotation) * size + x, ty + Geometry.d4y(rotation) * size + y);;

                        if (other != null && canMine(other) && other.drop() == dominantItem) {
                            //TODO Wired
                            Draw.color(dominantItem.color);
                            Lines.stroke((0.5f + Mathf.sin(totalProgress()) * 0.15f) * warmup);
                            //Lines.poly(other.worldx(), other.worldy(), 6, tilesize / 2f, 45);

                        }
                    }
                }
                */

            }
        }

        @Override
        public byte version() {
            return 1;
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            write.f(progress);
            write.f(warmup);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            if (revision >= 1) {
                progress = read.f();
                warmup = read.f();
            }
        }
    }

}
