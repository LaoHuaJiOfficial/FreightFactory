package prototypes.struct;

import arc.struct.IntSeq;
import arc.struct.ObjectIntMap;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import arc.util.Log;

public class TechTree {
    public static Seq<TechTreeNode> allNodes;
    public static Seq<Seq<TechTreeNode>> outNodes;
    public static ObjectIntMap<TechTreeNode> depthNodes;

    public static void init(){
        allNodes = new Seq<>();
        outNodes = new Seq<>();
        depthNodes = new ObjectIntMap<>();
    }

    /** return a node's all child nodes as depth arr. */

    public static void depthNodeArr(TechTreeNode node){
        getDepthNodes(node);
        IntSeq intSeq = depthNodes.values().toArray();
        int maxDepth = 0;
        for (int i: intSeq.items){
            if (i > maxDepth){
                maxDepth = i;
            }
        }
        outNodes.clear();
        for (int i = 0; i <= maxDepth; i++) {
            int depthI = i;
            outNodes.add(new Seq<TechTreeNode>());
            depthNodes.forEach(nodeEntry -> {
                if (nodeEntry.value == depthI) {
                    outNodes.get(depthI).add(nodeEntry.key);
                }
            });
        }
        outNodes.remove(value -> value.size == 0);
    }

    private static void getDepthNodes(TechTreeNode node){
        depthNodes.clear();
        //add this node as root node
        depthNodes.put(node, 0);
        addChildDepthNodes(node.childNode, 1);
    }

    private static void addChildDepthNodes(Seq<TechTreeNode> childNodes, int nodeDepth){
        if (!childNodes.any()) return;
        for (TechTreeNode child: childNodes){
            if (!child.isAdded()){
                depthNodes.put(child, nodeDepth);
                child.add();
                addChildDepthNodes(child.childNode, nodeDepth + 1);
            }else {
                depthNodes.increment(child, 1);
            }
        }
    }
}
