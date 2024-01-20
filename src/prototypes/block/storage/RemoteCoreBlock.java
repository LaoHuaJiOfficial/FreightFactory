package prototypes.block.storage;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.geom.Vec2;
import arc.scene.ui.layout.Table;
import arc.struct.EnumSet;
import mindustry.entities.TargetPriority;
import mindustry.gen.Building;
import mindustry.graphics.Layer;
import mindustry.type.Item;
import mindustry.world.blocks.ItemSelection;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.meta.BlockFlag;

import static mindustry.Vars.*;

public class RemoteCoreBlock extends StorageBlock {
    public TextureRegion topRegion;
    public boolean incinerateNonBuildable = false;

    public RemoteCoreBlock(String name) {
        super(name);
        solid = true;
        update = true;
        hasItems = true;
        priority = TargetPriority.core;
        flags = EnumSet.of(BlockFlag.core);
        configurable = true;
        saveConfig = true;
        itemCapacity = 0;
        drawDisabled = false;
        canOverdrive = false;
        replaceable = false;
        unloadable = false;

        config(Item.class, (RemoteCoreBuild tile, Item item) -> tile.sortItem = item);
        configClear((RemoteCoreBuild tile) -> tile.sortItem = null);
    }

    public void init() {
        updateClipRadius(200f);
        super.init();
    }

    public void load() {
        super.load();
        topRegion = Core.atlas.find(name + "-top");
    }

    public class RemoteCoreBuild extends Building {
        public Item sortItem = null;
        public int storageCapacity;
        public boolean noEffect = false;

        public void buildConfiguration(Table table) {
            ItemSelection.buildTable(RemoteCoreBlock.this, table, content.items(), () -> sortItem, this::configure, selectionRows, selectionColumns);
        }

        @Override
        public Item config() {
            return sortItem;
        }

        @Override
        public boolean acceptItem(Building source, Item item) {
            return items.get(item) < getMaximumAccepted(item) && item == sortItem;
        }

        @Override
        public int getMaximumAccepted(Item item) {
            return state.rules.coreIncinerates ? storageCapacity * 20 : storageCapacity;
        }

        @Override
        public void onProximityUpdate() {

            super.onProximityUpdate();

            for (Building other : state.teams.cores(team)) {
                if (other.tile() != tile) {
                    this.items = other.items;
                }
            }

            storageCapacity = itemCapacity + proximity().sum(e -> owns(e) ? e.block.itemCapacity : 0);
            proximity.each(this::owns, t -> {
                t.items = items;
                ((StorageBuild) t).linkedCore = this;
            });

            for (Building other : state.teams.cores(team)) {
                if (other.tile() == tile) continue;
                storageCapacity += other.block.itemCapacity + other.proximity().sum(e -> owns(other, e) ? e.block.itemCapacity : 0);
            }

            if (!world.isGenerating()) {
                for (Item item : content.items()) {
                    items.set(item, Math.min(items.get(item), storageCapacity));
                }
            }

            for (CoreBlock.CoreBuild other : state.teams.cores(team)) {
                other.storageCapacity = storageCapacity;
            }
        }

        @Override
        public void handleItem(Building source, Item item) {
            boolean incinerate = incinerateNonBuildable && !item.buildable;

            if (team == state.rules.defaultTeam) {
                state.stats.coreItemCount.increment(item);
            }

            if (net.server() || !net.active()) {
                if (team == state.rules.defaultTeam && state.isCampaign() && !incinerate) {
                    state.rules.sector.info.handleCoreItem(item, 1);
                }

                if (items.get(item) >= storageCapacity || incinerate) {
                    //create item incineration effect at random intervals
                    if (!noEffect) {
                        incinerateEffect(this, source);
                    }
                    noEffect = false;
                } else {
                    super.handleItem(source, item);
                }
            } else if (((state.rules.coreIncinerates && items.get(item) >= storageCapacity) || incinerate) && !noEffect) {
                //create item incineration effect at random intervals
                incinerateEffect(this, source);
                noEffect = false;
            }
        }

        public boolean owns(Building tile) {
            return owns(this, tile);
        }

        public boolean owns(Building core, Building tile) {
            return tile instanceof StorageBuild b && ((StorageBlock) b.block).coreMerge && (b.linkedCore == core || b.linkedCore == null);
        }

        public void draw() {
            super.draw();

            drawConnection();

            Draw.color(sortItem == null ? Color.clear : sortItem.color);
            Draw.rect(topRegion, x, y);
            Draw.color();
        }

        public void drawConnection() {
            if (!(sortItem == null)) {
                Draw.z(Layer.shields);

                Building core = state.teams.closestCore(x, y, team());

                float ang = Angles.angle(core.x, core.y, x, y);

                Vec2 joint;

                if ((int) ((ang - 45f) / 90f) % 2 == 0) {
                    joint = new Vec2(core.x, y);
                } else {
                    joint = new Vec2(x, core.y);
                }
                Draw.color(sortItem == null ? Color.clear : sortItem.color);

                Lines.stroke(4.5f);

                Lines.beginLine();
                Lines.linePoint(x, y);
                Lines.linePoint(joint);
                Lines.linePoint(core.x, core.y);
                Lines.endLine();
            }

            Draw.reset();
        }
    }
}
