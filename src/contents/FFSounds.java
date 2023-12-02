package contents;

import arc.Core;
import arc.assets.AssetDescriptor;
import arc.assets.loaders.SoundLoader;
import arc.audio.Sound;
import arc.struct.Seq;
import mindustry.Vars;

import java.lang.reflect.Field;

public class FFSounds {
    public static Sound CrystaShoot, InfernoShoot;

    public static void load(){
        CrystaShoot = loadSound("crystaShoot.ogg");
        InfernoShoot = loadSound("inferno-shoot.mp3");
    }

    private static Sound loadSound(String soundName){
        if(!Vars.headless){
            String path = "sounds/" + soundName;
            Sound sound = new Sound();

            AssetDescriptor<?> desc = Core.assets.load(path, Sound.class, new SoundLoader.SoundParameter(sound));
            desc.errored = Throwable::printStackTrace;
            return sound;
        }else return new Sound();
    }
}
