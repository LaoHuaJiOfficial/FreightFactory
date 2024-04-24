package contents;

import prototypes.struct.TechTreeNode;

public class Technology {
    public static TechTreeNode
        smelt1, electricity1, smelt2, defense1,
        ironProcess, basicTransport, motorManufacturing;

    public static void load(){
        smelt1 = new TechTreeNode("smelt-1");
        smelt2 = new TechTreeNode("smelt-2");
        electricity1 = new TechTreeNode("electricity-1");
        defense1 = new TechTreeNode("defense-1");
        ironProcess = new TechTreeNode("iron-process");
        basicTransport = new TechTreeNode("basic-transport");
        motorManufacturing = new TechTreeNode("motor-manufacturing");
    }

    public static void init(){
        ironProcess.init(smelt1);
        basicTransport.init(smelt1);
        electricity1.init(smelt1);
        defense1.init(electricity1);
        motorManufacturing.init(ironProcess, electricity1);
        smelt2.init(smelt1, electricity1);
    }
}
