package prototypes.block.inner;

import arc.func.Cons;
import arc.math.geom.Point2;
import arc.util.Log;
import contents.FFBlock;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.gen.Teamc;
import mindustry.gen.Unit;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.payloads.Payload;
import mindustry.world.meta.BuildVisibility;

import static mindustry.Vars.state;

public class LinkBlock extends Block {
    public LinkBlock(String name) {
        super(name);

        update = true;
        squareSprite = false;

        destructible = true;
        breakable = false;
        solid = true;
        rebuildable = false;

        hasItems = true;
        hasLiquids = true;
        //hasPower = true;

        buildVisibility = BuildVisibility.hidden;
    }

    public boolean canBreak(Tile tile){
        return false;
    }


    @SuppressWarnings("InnerClassMayBeStatic")
    public class LinkBuild extends Building{
        /**Linked Build. Can't be null*/
        public Building linkBuild;

        public void updateLink(Building link){
            linkBuild = link;
            //this.tile.build = linkBuild;
        }

        @Override
        public void draw() {}

        @Override
        public boolean acceptItem(Building source, Item item) {
            if (linkBuild != null){
                return linkBuild.acceptItem(source, item);
            }else {
                return false;
            }
        }

        @Override
        public int acceptStack(Item item, int amount, Teamc source) {
            if (linkBuild != null){
                return linkBuild.acceptStack(item, amount, source);
            }else {
                return 0;
            }
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid) {
            if (linkBuild != null){
                return linkBuild.acceptLiquid(source, liquid);
            }else {
                return false;
            }
        }

        @Override
        public boolean acceptPayload(Building source, Payload payload) {
            //todo
            return super.acceptPayload(source, payload);
        }

        @Override
        public float handleDamage(float amount) {
            if (linkBuild != null){
                return linkBuild.handleDamage(amount);
            }else {
                return 0;
            }
        }

        @Override
        public void handleItem(Building source, Item item) {
            if (linkBuild != null){
                linkBuild.handleItem(source, item);
            }
        }

        @Override
        public void handleStack(Item item, int amount, Teamc source) {
            if (linkBuild != null) {
                linkBuild.handleStack(item, amount, source);
            }
        }

        @Override
        public void handleLiquid(Building source, Liquid liquid, float amount) {
            if (linkBuild != null) {
                linkBuild.handleLiquid(source, liquid, amount);
            }
        }

        @Override
        public void handlePayload(Building source, Payload payload) {
            if (linkBuild != null) {
                linkBuild.handlePayload(source, payload);
            }
        }

        @Override
        public void handleString(Object value) {
            if (linkBuild != null) {
                linkBuild.handleString(value);
            }
        }

        @Override
        public void handleUnitPayload(Unit unit, Cons<Payload> grabber) {
            if (linkBuild != null) {
                linkBuild.handleUnitPayload(unit, grabber);
            }
        }

        @Override
        public void onProximityUpdate() {
            super.onProximityUpdate();
            if (linkBuild != null){
                linkBuild.onProximityUpdate();
            }
            //todo this is wired
        }

        @Override
        public void onProximityAdded() {
            super.onProximityAdded();
        }

        @Override
        public void onProximityRemoved() {
            super.onProximityRemoved();
        }
    }
}
