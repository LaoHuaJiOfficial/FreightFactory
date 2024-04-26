package prototypes.struct;

import arc.struct.IntSeq;
import arc.struct.ObjectIntMap;
import arc.struct.Seq;

public class TechTree {
    public static Seq<Technology> allNodes = new Seq<>();
    public static Seq<Seq<Technology>> outNodes = new Seq<>();
    public static ObjectIntMap<Technology> depthNodes = new ObjectIntMap<>();

    /** return a node's all child nodes as depth arr. */
    public static void depthNodeMap(Technology node){
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
            outNodes.add(new Seq<Technology>());
            depthNodes.forEach(nodeEntry -> {
                if (nodeEntry.value == depthI) {
                    outNodes.get(depthI).add(nodeEntry.key);
                }
            });
        }
        outNodes.remove(value -> value.size == 0);
        for (var technology: allNodes){
            technology.resetAdd();
        }
    }

    private static void getDepthNodes(Technology node){
        depthNodes.clear();
        //add this node as root node
        depthNodes.put(node, 0);
        addChildDepthNodes(node.childNode, 1);
    }

    private static void addChildDepthNodes(Seq<Technology> childNodes, int nodeDepth){
        if (!childNodes.any()) return;
        for (Technology child: childNodes){
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
