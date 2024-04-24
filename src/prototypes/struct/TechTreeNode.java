package prototypes.struct;

import arc.Core;
import arc.graphics.g2d.TextureRegion;
import arc.struct.ObjectSet;
import arc.struct.Seq;
import arc.util.Nullable;
import mindustry.ctype.Content;
import prototypes.recipe.Recipe;

public class TechTreeNode {
    public String name;
    public Seq<? extends Content> unlockContents;
    public Seq<Recipe> unlockRecipes;
    public @Nullable Seq<TechTreeNode> parentNode = new Seq<>();
    public Seq<TechTreeNode> childNode = new Seq<>();
    public Seq<TechDataStack> requirement;

    public TextureRegion icon;
    public boolean defaultUnlocked;
    public boolean unlocked;

    private boolean added = false;

    public void add(){
        added = !added;
    }

    public boolean isAdded(){
        return added;
    }

    public TechTreeNode(String name, boolean defaultUnlocked){
        this.name = name;
        this.defaultUnlocked = defaultUnlocked;

        load();
    }

    public TechTreeNode(String name){
        this(name, true);
    }

    public void load(){
        icon = Core.atlas.find(name);
    }

    public void init(TechTreeNode...nodes){
        TechTree.allNodes.add(this);
        checkNodes(nodes);

        parentNode.add(nodes);
        for (TechTreeNode node: nodes){
            node.childNode.add(this);
        }
    }

    private void checkNodes(TechTreeNode[] array) {
        ObjectSet<TechTreeNode> set = new ObjectSet<>();
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
