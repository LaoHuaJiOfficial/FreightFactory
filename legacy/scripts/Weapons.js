
const FFWeapons = {
    重型磁轨炮 : extendContent(Weapon,"standardThorium",{}),
    破晓1 : extend(Weapon,{}),
    卫道士1 : extend(Weapon,{})

}

FFWeapons.重型磁轨炮.bullet = Bullets.standardThorium;
FFWeapons.重型磁轨炮.name = "重型磁轨炮";
FFWeapons.重型磁轨炮.length = 0.2;
FFWeapons.重型磁轨炮.reload = 7;
FFWeapons.重型磁轨炮.alternate = true;
FFWeapons.重型磁轨炮.shootEffect = Fx.shootBig2;
FFWeapons.重型磁轨炮.shootSound = Sounds.shootSnap;

FFWeapons.破晓1.bullet = Bullets.meltdownLaser;
FFWeapons.破晓1.length = 0.2;
FFWeapons.破晓1.reload = 7;
FFWeapons.破晓1.alternate = true;
FFWeapons.破晓1.shootEffect = Fx.shootBig2;
FFWeapons.破晓1.shootSound = Sounds.shootSnap;

FFWeapons.卫道士1.bullet = Bullets.healBullet;
FFWeapons.卫道士1.length = 0.2;
FFWeapons.卫道士1.reload = 20;
FFWeapons.卫道士1.alternate = true;
FFWeapons.卫道士1.shootEffect = Fx.shootBig2;
FFWeapons.卫道士1.shootSound = Sounds.pew;


this.global.FFWeapons = FFWeapons;
