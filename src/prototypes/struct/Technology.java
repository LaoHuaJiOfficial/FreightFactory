package prototypes.struct;

import arc.Core;
import arc.graphics.g2d.TextureRegion;
import arc.struct.ObjectSet;
import arc.struct.Seq;
import arc.util.Nullable;
import mindustry.ctype.Content;
import mindustry.ctype.ContentType;
import prototypes.recipe.Recipe;

import static prototypes.struct.TechTree.allNodes;
import static utilities.FFGlobalVars.ModNamePrefix;

public class Technology extends Content {
    public String name;
    public Seq<? extends Content> unlockContents;
    public Seq<Recipe> unlockRecipes;
    public @Nullable Seq<Technology> parentNode = new Seq<>();
    public Seq<Technology> childNode = new Seq<>();
    public Seq<TechDataStack> requirement;
    public TechTreeNode node;

    public TextureRegion icon;
    public boolean defaultUnlocked;
    public boolean unlocked;

    private boolean added = false;

    public void add(){
        added = !added;
    }

    public void resetAdd(){
        added = false;
    }

    public boolean isAdded(){
        return added;
    }

    public Technology(String name, boolean defaultUnlocked){
        this.name = name;
        this.defaultUnlocked = defaultUnlocked;

    }

    public Technology(String name){
        this(name, true);
    }

    @Override
    public ContentType getContentType() {
        //this is just for internal use
        return ContentType.error;
    }

    @Override
    public void load(){
        icon = Core.atlas.find(ModNamePrefix + name);
    }

    @Override
    public void init() {
        allNodes.add(this);
    }

    public void addNode(Technology...nodes){

        checkNodes(nodes);

        parentNode.add(nodes);
        for (Technology node: nodes){
            node.childNode.add(this);
        }
    }

    private void checkNodes(Technology[] array) {
        ObjectSet<Technology> set = new ObjectSet<>();
        for (var node : array) {
            if (!set.add(node)) {
                throw new RuntimeException("Technology: " + this.name + "'s tech requirement has duplicated tech node!");
            }
            if (node == this){
                throw new RuntimeException("Technology: " + this.name + "require self as tech requirement!");
            }
        }
    }
}
