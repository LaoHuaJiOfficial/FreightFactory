package contents.blocks;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.Pixmap;
import arc.graphics.g2d.PixmapRegion;
import contents.FFItems;
import mindustry.graphics.MultiPacker;
import mindustry.world.Block;
import mindustry.world.blocks.environment.OreBlock;

import static mindustry.Vars.tilesize;

public class EnvironmentBlock {
    public static Block OreBauxite, RawCopper;

    public static void load(){
        OreBauxite = new OreBlock("ore-bauxite", FFItems.bauxite) {{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};

        RawCopper = new OreBlock("raw-copper", FFItems.rawCopper){{
            oreDefault = true;
        }
            @Override
            public void createIcons(MultiPacker packer) {
                oreLoad(packer, this);
            }
        };
    }

    public static void oreLoad(MultiPacker packer, Block block){
        for(int i = 0; i < block.variants; i++){
            PixmapRegion shadow = Core.atlas.has(block.name + (i + 1)) ?
                Core.atlas.getPixmap(block.name + (i + 1)) :
                Core.atlas.getPixmap(block.itemDrop.name + (i + 1));

            Pixmap image = shadow.crop();

            int offsetX = 2;
            int offsetY = 2;
            int shadowColor = Color.rgba8888(0, 0, 0, 0.3f);

            for(int x = offsetX; x < image.width; x++){
                for(int y = offsetY; y < image.height; y++){
                    if(shadow.getA(x, y) == 0 && shadow.getA(x + offsetX, y - offsetY) != 0){
                        image.setRaw(x, y, shadowColor);
                    }
                }
            }

            packer.add(MultiPacker.PageType.environment, block.name + (i + 1), image);
            packer.add(MultiPacker.PageType.editor, "editor-" + block.name + (i + 1), image);

            if(i == 0){
                packer.add(MultiPacker.PageType.editor, "editor-block-" + block.name + "-full", image);
                packer.add(MultiPacker.PageType.main, "block-" + block.name + "-full", image);
            }
        }
    }
}
