package prototypes;

import arc.struct.IntMap;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import contents.FFItems;
import mindustry.content.Items;
import mindustry.type.Item;
import prototypes.recipe.Recipe;

public class FFContent {
    public static Seq<Recipe> recipeAll;
    public static ObjectMap<Item, Integer> ItemValue;

    public static void load(){
        recipeAll = new Seq<>();

        ItemValue = new ObjectMap<>();
        ItemValue.putAll(
            Items.copper, 10,
            Items.lead, 10,
            Items.metaglass, 30,
            Items.graphite, 35,
            Items.sand, 1,
            Items.coal, 12,
            Items.titanium, 20,
            Items.thorium, 25,
            Items.scrap, 1,
            Items.silicon, 30,
            Items.plastanium, 80,
            Items.phaseFabric, 150,
            Items.surgeAlloy, 250,
            Items.sporePod, 10,
            Items.blastCompound, 50,
            Items.pyratite, 30,
            Items.beryllium, 20,
            Items.tungsten, 30,
            Items.oxide, 50,
            Items.carbide, 100,

            FFItems.bauxite, 12,
            FFItems.aluminium, 45,
            FFItems.crystalAlloy, 180,
            FFItems.multiCompound, 400,
            FFItems.wheat, 10,
            FFItems.leaf, 12,
            FFItems.flour, 15,
            FFItems.fermentedTea, 18,
            FFItems.iceCube, 10,
            FFItems.teaIceCube, 30,
            FFItems.blackTeaIceCube, 40,
            FFItems.colaIceCube, 45,
            FFItems.bread, 30,
            FFItems.hardCandy, 20,
            FFItems.cannedCola, 50,
            FFItems.cannedNucola, 150,
            FFItems.tinTea, 80,
            FFItems.rainbowCandy, 400,
            FFItems.radiantCake, 400
        );
    }
}
