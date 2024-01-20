package contents;

import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.type.Liquid;

public class CoolantLiquid {

    public static final Seq<Liquid> CoolantLiquidSeq = new Seq<>();
    public static Liquid Coolant300K, Coolant200K, Coolant100K, Coolant0K;

    public static void load() {
        Coolant300K = new Liquid("coolant-300K", Color.valueOf("737FE1")) {{
            //hidden = true;
        }};
        Coolant200K = new Liquid("coolant-200K", Color.valueOf("6C8FED")) {{
            //hidden = true;
        }};
        Coolant100K = new Liquid("coolant-100K", Color.valueOf("65B8F8")) {{
            //hidden = true;
        }};
        Coolant0K = new Liquid("coolant-0K", Color.valueOf("299DF7")) {{
            //hidden = true;
        }};

        CoolantLiquidSeq.addAll(Coolant300K, Coolant200K, Coolant100K, Coolant0K);
    }

}
