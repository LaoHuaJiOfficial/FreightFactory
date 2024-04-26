package contents;

import prototypes.struct.Technology;

public class Technologies {
    public static Technology
        smelt1, electricity1, smelt2, defense1,
        ironProcess, basicTransport, motorManufacturing,
        name1, name2, name3, name4, name5, name6, name7, name8, name9;

    public static void load(){
        smelt1 = new Technology("material");
        smelt2 = new Technology("smelt-2");
        electricity1 = new Technology("electricity-1");
        defense1 = new Technology("defense-1");
        ironProcess = new Technology("iron-process");
        basicTransport = new Technology("basic-transport");
        motorManufacturing = new Technology("motor-manufacturing");
        name1 = new Technology("name1");
        name2 = new Technology("name2");
        name3 = new Technology("name3");
        name4 = new Technology("name4");
        name5 = new Technology("name5");
        name6 = new Technology("name6");
        name7 = new Technology("name7");
        name8 = new Technology("name8");
        name9 = new Technology("name9");
    }

    public static void init(){
        ironProcess.addNode(smelt1);
        basicTransport.addNode(smelt1);
        electricity1.addNode(smelt1);
        defense1.addNode(electricity1);
        motorManufacturing.addNode(ironProcess, electricity1);
        smelt2.addNode(smelt1, electricity1);
        name1.addNode(defense1);
        name2.addNode(electricity1);
        name3.addNode(motorManufacturing);
        name4.addNode(name1, name2);
        name5.addNode(name4);
        name6.addNode(name3, smelt2);
        name7.addNode(name4, name5, name6);
        name8.addNode(name7);
        name9.addNode(name8);

    }
}
