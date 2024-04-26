package prototypes.struct;

import arc.scene.ui.Button;
import arc.struct.Seq;

public class TechTreeNode extends Button{
    public Technology technology;
    public Seq<TechTreeNode> childNode;
    public Seq<TechTreeNode> parentNode;

    public TechTreeNode(Technology tech){
        this.technology = tech;
    }
}
