package prototypes.customUnit;

import arc.Core;
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
import arc.struct.ObjectMap;
import arc.struct.Seq;
import arc.util.Tmp;
import contents.GlobalSprites;
import mindustry.graphics.Pal;
import utilities.FFGlobalVars;
import prototypes.customUnit.grid.GridPartData;

import static mindustry.Vars.player;

public class CustomUnitCanvas extends WidgetGroup{
    public static int bounds = 100;
    public float unitSize = 40;
    public CustomUnitTileCanvas tilemap;
    public Seq<Point2> TileGrid = FFGlobalVars.UnitTileGrid.get(0);
    //public Seq<Point2> placedTiles = new Seq<>();
    public ObjectMap<GridPartData, Seq<Point2>> placedTiles = new ObjectMap<>();

    public Seq<GridPartData> GridPartSeq = new Seq<>();
    public GridPartData queryGridPart;

    public CustomUnitCanvas(){
        setFillParent(true);
        addChild(tilemap = new CustomUnitTileCanvas());

        addCaptureListener(
            new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, KeyCode button){
                if(queryGridPart != null && button == KeyCode.mouseRight){
                    event.stop();
                    return true;
                }else{
                    return false;
                }
            }

        });

        addCaptureListener(new ElementGestureListener(){

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY){
                //if(tilemap.moving != null) return;
                tilemap.x = Mathf.clamp(tilemap.x + deltaX, -200, 200);
                tilemap.y = Mathf.clamp(tilemap.y + deltaY, -200, 200);
            }

            @Override
            public void tap(InputEvent event, float x, float y, int count, KeyCode button){
                if(queryGridPart == null) return;

                Vec2 pos = localToDescendantCoordinates(tilemap, Tmp.v1.set(x, y));
                int startX = (int) (pos.x / unitSize);
                int startY = (int) (pos.y / unitSize);
                if (canCreateTile(startX, startY, queryGridPart.width, queryGridPart.height)){
                    CreateTile(startX, startY, queryGridPart.width, queryGridPart.height);
                }

                // In mobile, placing the query is done in a separate button.
                //if(!mobile) placeQuery();
            }

        });


    }

    public boolean canCreateTile(int x, int y, int width, int height){
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                if (!TileGrid.contains(new Point2(x+i, y+j)))return false;
                for (var gridPoints: placedTiles.values()){
                    if (gridPoints.contains(new Point2(x+i, y+j)))return false;
                }
            }
        }
        return true;
    }

    public void CreateTile(int x, int y, int width, int height){
        Seq<Point2> occupy = new Seq<>();
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                occupy.add(new Point2(x + i, y + j));
            }
        }
        GridPartData grid = new GridPartData(queryGridPart.weapon).setStart(x, y);
        placedTiles.put(grid, occupy);
        GridPartSeq.add(grid);
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
            //Draw x and y axis, TODO remove
            Lines.stroke(4f);
            Draw.color(Pal.accent);
            Lines.rect(x, y, getWidth(), getHeight());
            Draw.color(Pal.gray);

            Lines.line(x + unitSize, y + unitSize, x + getWidth() - unitSize, y + unitSize);
            Lines.line(x + unitSize, y + unitSize, x + unitSize, y + getHeight() - unitSize);

            //Draw region and shift it TODO properly
            TextureRegion region = player.unit().type.fullIcon;
            boolean
                shiftX = ((region.width/unitSize)%2 == 0);
                //shiftY = ((region.height/unitSize)%2 == 0);

            Draw.rect(region,
                x + unitSize + ((float) region.width /32) * unitSize + (shiftX?0:-unitSize/2),
                y + unitSize + ((float) region.height /32) * unitSize,
                region.width * unitSize/16, region.height * unitSize/16);

            //Draw Tile Grids
            for (Point2 point: TileGrid){
                if (point.x > 0 && point.y > 0){
                    Draw.color(Pal.accent, 0.3f);
                    drawGridRect(point.x, point.y);
                    Draw.color(Pal.accent, 0.4f);
                    drawGridOutline(point.x, point.y);
                    Draw.reset();
                }
            }

            //Draw Occupied Tile Grids
            for (Seq<Point2> point2Seq: placedTiles.values()){
                for (Point2 point: point2Seq){
                    Draw.color(Pal.ammo, 0.3f);
                    drawGridRect(point.x, point.y);
                    Draw.reset();
                }
            }

            for (GridPartData gridPart: GridPartSeq){
                drawGridPart(gridPart);
            }

            drawSelect();

            Draw.reset();
            super.draw();
            Draw.reset();
        }


        public void drawSelect(){
            Vec2 pos = screenToLocalCoordinates(Core.input.mouse());
            int tx = Mathf.floor(pos.x / unitSize);
            int ty = Mathf.floor(pos.y / unitSize);
            Draw.color(Pal.techBlue, 0.3f);
            if (queryGridPart!=null){
                //float shiftX = queryGridPart.width/2 == 0? unitSize/2: 0;
                //float shiftY = queryGridPart.height/2 == 0? 0: unitSize/2;
                Fill.rect(
                    this.x + tx * unitSize + (queryGridPart.width * unitSize)/2,
                    this.y + ty * unitSize + (queryGridPart.height * unitSize)/2,
                    unitSize * queryGridPart.width, unitSize * queryGridPart.height
                );
                Draw.color();
                Draw.rect(
                    queryGridPart.icon,
                    this.x + tx * unitSize + (queryGridPart.width * unitSize)/2,
                    this.y + ty * unitSize + (queryGridPart.height * unitSize)/2,
                    unitSize * queryGridPart.width, unitSize * queryGridPart.height
                );
            }else {
                Fill.rect(
                    this.x + tx * unitSize + unitSize/2,
                    this.y + ty * unitSize + unitSize/2,
                    unitSize, unitSize
                );
            }
            Draw.reset();
        }

        public void drawGridRect(int x, int y){
            Fill.rect(
                this.x + x * unitSize + unitSize/2,
                this.y + y * unitSize + unitSize/2,
                unitSize, unitSize
            );
        }

        public void drawGridOutline(int x, int y){
            Draw.alpha(0.5f);
            Draw.rect(
                GlobalSprites.GridOutline,
                this.x + x * unitSize + unitSize/2,
                this.y + y * unitSize + unitSize/2,
                unitSize,
                unitSize
            );
            Draw.alpha(1);
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
    }
}
