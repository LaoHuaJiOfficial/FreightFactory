package contents;

import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.type.Liquid;

public class FFLiquids {
    public static Liquid
        mineralFluid, syrup, greenTea, blackTea, cola, icedCola, ionFluid;

    public static void load(){
        mineralFluid = new Liquid("mineral-fluid", Color.valueOf("db7970")){{
            explosiveness = 0f;
            flammability = 0f;
            heatCapacity = 0.5f;
            viscosity = 1f;
            temperature = 1f;

            gas = false;
            coolant = false;

            effect = StatusEffects.melting;
            lightColor = Color.valueOf("db7970").a(0.4f);
        }};

        syrup = new Liquid("syrup", Color.valueOf("e6e0ff")){{
            explosiveness = 0f;
            flammability = 0f;
            heatCapacity = 0.5f;
            viscosity = 0.5f;
            temperature = 0.5f;

            gas = false;
            coolant = false;

            effect = StatusEffects.slow;
            lightColor = Color.valueOf("e6e0ff").a(0.4f);
        }};

        greenTea = new Liquid("green-tea", Color.valueOf("9be08e")){{
            explosiveness = 0f;
            flammability = 0f;
            heatCapacity = 0.5f;
            viscosity = 0.5f;
            temperature = 0.5f;

            gas = false;
            coolant = false;

            lightColor = Color.valueOf("e6e0ff").a(0.4f);
        }};

        blackTea = new Liquid("black-tea", Color.valueOf("ea8878")){{
            explosiveness = 0f;
            flammability = 0f;
            heatCapacity = 0.5f;
            viscosity = 0.5f;
            temperature = 0.5f;

            gas = false;
            coolant = false;

            lightColor = Color.valueOf("ea8878").a(0.4f);
        }};

        cola = new Liquid("cola", Color.valueOf("494539")){{
            explosiveness = 0f;
            flammability = 0f;
            heatCapacity = 1.2f;
            viscosity = 0.5f;
            temperature = 0.3f;

            gas = false;
            coolant = true;

            effect = StatusEffects.slow;
            lightColor = Color.valueOf("ea8878").a(0.4f);
        }};

        icedCola = new Liquid("iced-cola", Color.valueOf("605a4a")){{
            explosiveness = 0f;
            flammability = 0f;
            heatCapacity = 1.5f;
            viscosity = 0.5f;
            temperature = 0.2f;

            gas = false;
            coolant = true;

            effect = StatusEffects.slow;
            lightColor = Color.valueOf("ea8878").a(0.4f);
        }};

    }
}
