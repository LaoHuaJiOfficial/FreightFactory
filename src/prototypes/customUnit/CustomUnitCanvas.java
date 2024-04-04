package prototypes.customUnit;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.input.KeyCode;
import arc.math.Mathf;
import arc.math.geom.Point2;
import arc.math.geom.Vec2;
import arc.scene.event.ElementGestureListener;
import arc.scene.event.InputEvent;
import arc.scene.event.InputListener;
import arc.scene.event.Touchable;
import arc.scene.ui.layout.WidgetGroup;
import arc.struct.Seq;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.graphics.Pal;
import prototypes.FFVars;
import prototypes.customUnit.grid.GridPartData;

import static mindustry.Vars.content;
import static mindustry.Vars.player;

public class CustomUnitCanvas extends WidgetGroup{
    public static int
        objWidth = 2, objHeight = 2,
        bounds = 100;
    public float unitSize = 40;
    public CustomUnitTileCanvas tilemap;
    public Seq<Point2> TileGrid = FFVars.UnitTileGrid.get(0);
    public Seq<Point2> placedTiles = new Seq<>();

    public Seq<GridPartData> GridPartSeq = new Seq<>();
    public GridPartData GridPart;
    boolean query;


    private boolean pressed;
    private long visualPressed;

    public CustomUnitCanvas(){
        setFillParent(true);
        addChild(tilemap = new CustomUnitTileCanvas());

        addCaptureListener(
            new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, KeyCode button){
                if(query && button == KeyCode.mouseRight){
                    event.stop();
                    return true;
                }else{
                    return false;
                }
            }

        });

        addCaptureListener(new ElementGestureListener(){
            int pressPointer = -1;

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY){
                //if(tilemap.moving != null) return;
                tilemap.x = Mathf.clamp(tilemap.x + deltaX, -bounds * unitSize + width, 0);
                tilemap.y = Mathf.clamp(tilemap.y + deltaY, -bounds * unitSize + height, 0);
            }

            @Override
            public void tap(InputEvent event, float x, float y, int count, KeyCode button){
                if(GridPart == null) return;

                Vec2 pos = localToDescendantCoordinates(tilemap, Tmp.v1.set(x, y));
                int startX = (int) (pos.x / unitSize);
                int startY = (int) (pos.y / unitSize);
                if (canCreateTile(startX, startY, GridPart.width, GridPart.height)){
                    CreateTile(startX, startY, GridPart.width, GridPart.height);
                }

                // In mobile, placing the query is done in a separate button.
                //if(!mobile) placeQuery();
            }

            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, KeyCode button){
                if(pressPointer != -1) return;
                pressPointer = pointer;
                pressed = true;
                visualPressed = Time.millis() + 100;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, KeyCode button){
                if(pointer == pressPointer){
                    pressPointer = -1;
                    pressed = false;
                }
            }
        });


    }

    public boolean canCreateTile(int x, int y, int width, int height){
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                if (!TileGrid.contains(new Point2(x+i, y+j)))return false;
                if (placedTiles.contains(new Point2(x+i, y+j)))return false;
            }
        }
        return true;
    }

    public void CreateTile(int x, int y, int width, int height){
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                placedTiles.add(new Point2(x + i, y + j));
            }
        }
        GridPartSeq.add(new GridPartData(GridPart.weapon).setStart(x, y));
    }

    public class CustomUnitTileCanvas extends WidgetGroup{

        public CustomUnitTileCanvas(){
            setTransform(false);
            setSize(getPrefWidth(), getPrefHeight());
            touchable(() -> Touchable.disabled);
        }

        @Override
        public void draw(){
            validate();
            Lines.stroke(4f);
            Draw.color(Pal.accent);
            Lines.rect(x, y, getWidth(), getHeight());
            Draw.color(Pal.gray);
            float centerX = x + getWidth() / 2;
            float centerY = y + getHeight() / 2;

            Lines.line(x + unitSize, y + unitSize, x + getWidth() - unitSize, y + unitSize);
            Lines.line(x + unitSize, y + unitSize, x + unitSize, y + getHeight() - unitSize);

            TextureRegion region = player.unit().type.fullIcon;
            boolean
                shiftX = ((region.width/unitSize)%2 == 0);
                //shiftY = ((region.height/unitSize)%2 == 0);

            Draw.rect(region,
                x + unitSize + ((float) region.width /32) * unitSize + (shiftX?0:-unitSize/2),
                y + unitSize + ((float) region.height /32) * unitSize,
                region.width * unitSize/16, region.height * unitSize/16);

            for (float xPos = x + unitSize; xPos < x + width; xPos += unitSize) {
                Fill.circle(xPos, centerY, 5);
            }

            for (float yPos = y + unitSize; yPos < y + height; yPos += unitSize) {
                Fill.circle(centerX, yPos, 5);
            }

            for (Point2 point: TileGrid){
                Draw.color(Pal.accent, 0.3f);
                drawGridRect(point.x, point.y);
                Draw.reset();
            }
            for (Point2 point: placedTiles){
                Draw.color(Pal.ammo, 0.3f);
                drawGridRect(point.x, point.y);
                Draw.reset();
            }

            for (GridPartData gridPart: GridPartSeq){
                drawGridPart(gridPart);
            }

            if (GridPart != null){
                //drawSelect();
            }


            Draw.reset();
            super.draw();
            Draw.reset();
        }

        /*
        public void drawSelect(){
            Vec2 pos = localToDescendantCoordinates(this, Tmp.v1.set(x, y));
            int startX = (int) (pos.x / unitSize);
            int startY = (int) (pos.y / unitSize);
            Draw.color(Pal.accent);
            Lines.stroke(6f);
            //Lines.rect(this);
            //drawGridPart(GridPart);
        }

         */

        public void drawGridRect(int x, int y){
            Fill.rect(
                this.x + x * unitSize + unitSize/2,
                this.y + y * unitSize + unitSize/2,
                unitSize, unitSize
            );
        }


        public void drawGridPart(GridPartData grid){
            Draw.rect(grid.icon,
                this.x + grid.startX * unitSize + (grid.width * unitSize)/2,
                this.y + grid.startY * unitSize + (grid.height * unitSize)/2,
                grid.width * unitSize, grid.height * unitSize
            );
            Draw.reset();
        }

        @Override
        public float getPrefWidth(){
            return bounds * unitSize;
        }

        @Override
        public float getPrefHeight(){
            return bounds * unitSize;
        }

        public class GridPartTable{
            public int x,y;


        }

    }
}
