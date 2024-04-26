package prototypes.ui.dialog;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.input.KeyCode;
import arc.math.Mathf;
import arc.math.geom.Rect;
import arc.scene.Element;
import arc.scene.Group;
import arc.scene.event.ElementGestureListener;
import arc.scene.event.InputEvent;
import arc.scene.event.InputListener;
import arc.scene.event.Touchable;
import arc.scene.ui.*;
import arc.scene.ui.layout.Scl;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Align;
import arc.util.Log;
import arc.util.Nullable;
import arc.util.Scaling;
import contents.Technologies;
import mindustry.gen.Icon;
import mindustry.gen.Tex;
import mindustry.ui.Styles;
import prototypes.struct.TechTree;
import prototypes.struct.TechTreeNode;
import prototypes.struct.Technology;

import static mindustry.Vars.mobile;
import static prototypes.struct.TechTree.allNodes;
import static prototypes.struct.TechTree.outNodes;

public class TechnologyResearchDialog extends Dialog {
    public Rect bounds = new Rect(-2000, -2000, 4000, 4000);
    public TechTreeView view = new TechTreeView();


    public TechnologyResearchDialog() {
        super("");
        margin(0f);
        setFillParent(true);

        shown(() -> {
            build();
            view.hoverNode = null;
            view.infoTable.remove();
            view.infoTable.clear();
        });
        addListener(new InputListener(){
            @Override
            public boolean scrolled(InputEvent event, float x, float y, float amountX, float amountY){
                view.setScale(Mathf.clamp(view.scaleX - amountY / 10f * view.scaleX, 0.25f, 1f));
                view.setOrigin(Align.center);
                view.setTransform(true);
                return true;
            }

            @Override
            public boolean mouseMoved(InputEvent event, float x, float y){
                view.requestScroll();
                return super.mouseMoved(event, x, y);
            }
        });
        touchable = Touchable.enabled;
        addCaptureListener(new ElementGestureListener(){
            @Override
            public void zoom(InputEvent event, float initialDistance, float distance){
                if(view.lastZoom < 0){
                    view.lastZoom = view.scaleX;
                }

                view.setScale(Mathf.clamp(distance / initialDistance * view.lastZoom, 0.25f, 1f));
                view.setOrigin(Align.center);
                view.setTransform(true);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, KeyCode button){
                view.lastZoom = view.scaleX;
            }

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY){
                view.panX += deltaX / view.scaleX;
                view.panY += deltaY / view.scaleY;
                view.moved = true;
                view.clamp();
            }
        });
    }

    public void build(){
        clearChildren();
        table(full -> {
            full.left();
            full.table(this::buildTechInfoTable).margin(0).left().growY();
            full.table(this::buildTechTreeTable).margin(0).grow();
        }).grow();
    }

    private void buildTechInfoTable(Table table){
        table.clear();
        table.top().left();

        Table cont = new Table().background(Styles.flatOver);

        Table techIcon = new Table(Tex.pane);
        Table selection = new Table(Tex.pane2);
        Table buttons = new Table(Tex.pane2);

        ButtonGroup<Button> group = new ButtonGroup<>();

        techIcon.table(inner -> {
            inner.top();
            inner.labelWrap(() -> "Tech Name").size(Scl.scl(200), 0).fontScale(1f).style(Styles.outlineLabel).row();
            inner.image(Technologies.smelt1.icon).size(Scl.scl(200)).scaling(Scaling.fit).row();
        });
        selection.table(inner -> {
            Table techList = new Table();
            techList.top();
            int i = 0;
            for (Technology node: allNodes){
                i++;
                Button button = new Button();
                button.table(buttonInfo -> {
                    buttonInfo.image(node.icon).size(60);
                }).size(64);
                button.clicked(() -> {
                    TechTree.depthNodeMap(node);
                    view.rebuildAll();
                });
                group.add(button);
                techList.add(button);
                if (i == 4){
                    techList.row();
                    i = 0;
                }
            }
            int extra = allNodes.size % 4;
            if (extra > 0){
                for (int j = 0; j < extra; j++){
                    Table button = new Table();
                    button.table(buttonInfo -> buttonInfo.image().color(Color.clear).size(60)).size(64);
                    techList.add(button);
                }
            }
            inner.add(techList).grow().top();
        }).grow();
        buttons.table(inner -> {
            inner.bottom();
            inner.button("Exit", Icon.exit, Styles.cleart, this::hide).growX().bottom();
        }).growX();


        cont.add(techIcon).growX().row();
        cont.add(selection).grow().row();
        cont.add(buttons).growX().row();
        table.add(cont).growY();
    }

    private void buildTechTreeTable(Table table){
        table.clear();
        table.add(view).grow();
    }



    public class TechTreeView extends Group {
        public float panX = 0, panY = -200, lastZoom = -1;
        public boolean moved = false;
        public ImageButton hoverNode;
        public Table infoTable = new Table();
        public Seq<TechTreeNode> nodes = new Seq<>();
        public Seq<Seq<Float>> start = new Seq<>();
        public Seq<Seq<Float>> line = new Seq<>();
        public Seq<Seq<Float>> end = new Seq<>();

        {
            rebuildAll();
        }

        public void rebuildAll(){
            clear();
            nodes.clear();

            hoverNode = null;
            infoTable.clear();
            infoTable.touchable = Touchable.enabled;

            float depth = 0f;
            for(int i = 0; i < outNodes.size; i++){
                int finalI = i;
                Log.info(i);
                for(int j = 0; j < outNodes.get(i).size; j++){
                    float DepthF = depth;
                    int finalJ = j;
                    var node = outNodes.get(i).get(j);
                    TechTreeNode techButton = new TechTreeNode(node);
                    techButton.setStyle(Styles.nodei);
                    techButton.table(inner -> {
                        inner.image(node.icon).size(60).scaling(Scaling.fit);
                    }).tooltip(node.name).size(60, 80);
                    techButton.update(() -> {
                        setNodePos(techButton, DepthF, outNodes.get(finalI).size, finalJ);
                    });
                    techButton.setSize(90, 120);
                    addChild(techButton);
                    nodes.add(techButton);
                }
                depth -= 80f;
                depth -= spaceBetweenDepth(outNodes.get(i));
            }

            if(mobile){
                tapped(() -> {
                    Element e = Core.scene.hit(Core.input.mouseX(), Core.input.mouseY(), true);
                    if(e == this){
                        hoverNode = null;
                        rebuild();
                    }
                });
            }

            setOrigin(Align.center);
            setTransform(true);
            released(() -> moved = false);
        }

        private void setNodePos(Button button, float depth, int length, int index){
            float offsetX = x + panX + width / 2f, offsetY = panY + height / 2f;
            float nodeX = index * 150f - (length - 1) * 75;
            button.setPosition(
                nodeX + offsetX,
                depth + offsetY,
                Align.center
            );
        }

        private float spaceBetweenDepth(Seq<Technology> nodes){
            float space = 100;
            for(var node: nodes){
                if (node.childNode.any()){
                    space += 20f;
                }
            }
            return space;
        }

        void clamp(){
            //todo
            float pad = 600;

            float ox = width/2f, oy = height/2f;
            float rx = bounds.x + panX + ox, ry = panY + oy + bounds.y;
            float rw = bounds.width, rh = bounds.height;
            rx = Mathf.clamp(rx, -rw + pad, width - pad);
            ry = Mathf.clamp(ry, -rh + pad, height - pad);
            panX = rx - bounds.x - ox;
            panY = ry - bounds.y - oy;

        }

        void rebuild(){
            rebuild(null);
        }

        //pass an array of stack indexes that should shine here
        void rebuild(@Nullable boolean[] shine){
            ImageButton button = hoverNode;

            infoTable.remove();
            infoTable.clear();
            infoTable.update(null);

            infoTable.pack();
            infoTable.act(Core.graphics.getDeltaTime());
        }

        @Override
        public void drawChildren(){
            clamp();
            //toBack();
            float offsetX = panX + width / 2f, offsetY = panY + height / 2f;

            Draw.sort(true);
            //Draw.z(1f);


            Lines.stroke(8f);
            Lines.rect(bounds.x + offsetX, bounds.y + offsetY, bounds.width, bounds.height);
            Lines.stroke(1f);

            Draw.sort(false);
            Draw.reset();
            super.drawChildren();
        }
    }
}
