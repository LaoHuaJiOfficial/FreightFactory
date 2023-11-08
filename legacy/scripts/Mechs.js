const FFWeapons = this.global.FFWeapons; 
const 维和 = extendContent(Mech,"维和",{
	load(){
		this.weapon.load();
        if(!this.flying){
            this.legRegion = Core.atlas.find(this.name + "-leg");
            this.baseRegion = Core.atlas.find(this.name + "-base");
        }
		this.region = Core.atlas.find(this.name);
		this.weapon.region = Core.atlas.find("食品工厂-重型磁轨炮-equip");
	},	
	draw(player	){
		if(player.shootHeat <= 0.01) return;
		
		Shaders.build.progress = player.shootHeat;
		Shaders.build.region = Core.atlas.find(this.name + "-armor");
		Shaders.build.time = Time.time() / 10;
		Shaders.build.color.set(Pal.accent).a = player.shootHeat;
		Draw.shader(Shaders.build);
		Draw.rect(Core.atlas.find(this.name + "-armor"), player.x, player.y, player.rotation);
		Draw.shader();
	},
	spreadX(player){
		return player.shootHeat * 2;
	}
})

const 破晓 = extendContent(Mech,"破晓",{
	

	load(){
		this.weapon.load();
		this.region = Core.atlas.find(this.name);
		this.weapon.region = Core.atlas.find("食品工厂-破晓1");
	},	

	getRotationAlpha(player){
		return 0.5;
	},

	updateAlt(player){
		const scl = Mathf.clamp((player.velocity().len() - this.minV) / (this.maxV - this.minV));
		if(Mathf.chance(Time.delta() * (0.15 * scl))){
			
			//特效        newEffect    Color.valueOf("123123")
			//Effects.effect(特效,颜色,player.x, player.y)
			
			
			Effects.effect(Fx.hitLancer, Pal.lancerLaser, player.x, player.y);
			// Lightning.create(player.getTeam(), Pal.lancerLaser, 10 * Vars.state.rules.playerDamageMultiplier,
			// player.x + player.velocity().x, player.y + player.velocity().y, player.rotation, 14);
		}
	},

	draw(player){
		const shield = Core.atlas.find(this.name + "-shield");
		const scl = Mathf.clamp((player.velocity().len() - this.minV) / (this.maxV - this.minV));
		if(scl < 0.01) return;
		Draw.color(Pal.lancerLaser);
		Draw.alpha(scl / 2);
		Draw.blend(Blending.additive);
		Draw.rect(shield, player.x + Mathf.range(scl / 2), player.y + Mathf.range(scl / 2), player.rotation - 90);
		Draw.blend();
	},

	scld(player){
		return Mathf.clamp((player.velocity().len() - this.minV) / (this.maxV - this.minV));
	},
})

const 卫道士 = extendContent(Mech,"卫道士",{
	load(){
		this.weapon.load();
        if(!this.flying){
            this.legRegion = Core.atlas.find(this.name + "-leg");
            this.baseRegion = Core.atlas.find(this.name + "-base");
        }
		this.region = Core.atlas.find(this.name);
		this.weapon.region = Core.atlas.find("食品工厂-卫道士1");
	},	

})

维和.weapon = FFWeapons.重型磁轨炮;
维和.weaponOffsetX = 4;
维和.health = 400,
维和.mass = 1,
维和.variants = 160,
维和.speed = 0.14,
维和.boostSpeed = 0.25,
维和.drag = 0.09,
维和.buildPower = 1.5,
维和.itemCapacity = 40,
维和.engineOffset = 6.5,
维和.engineSize = 2.6,
维和.engineColor = Color.valueOf("#88a2fe"),
维和.flying = false,
维和.drillPower = 3,
维和.mineSpeed = 1.2,
维和.weaponOffsetX = 3.3

破晓.weapon = FFWeapons.破晓1;
//
破晓.minV = 3.9
破晓.maxV = 6
//
破晓.flying = true
破晓.drillPower = -0.1;
破晓.speed = 0.12;
破晓.drag = 0.01;
破晓.mass = 2;
破晓.health = 170;
破晓.engineColor = Color.valueOf("ea8878");
破晓.cellTrnsY = 1;


卫道士.weapon = FFWeapons.卫道士1;
卫道士.weaponOffsetX = 9;
卫道士.health = 400,
卫道士.mass = 2,
卫道士.variants = 160,
卫道士.speed = 0.14,
卫道士.boostSpeed = 0.25,
卫道士.drag = 0.05,
卫道士.buildPower = 3,
卫道士.itemCapacity = 100,
卫道士.engineOffset = 6.5,
卫道士.engineSize = 2.6,
卫道士.engineColor = Color.valueOf("84f490"),
卫道士.flying = true,
卫道士.drillPower = 4,
卫道士.mineSpeed = 2,
卫道士.weaponOffsetX = 4
卫道士.healRange = 60;
卫道士.healAmount = 10;
卫道士.healReload = 160;
卫道士.wasHealed= true;
