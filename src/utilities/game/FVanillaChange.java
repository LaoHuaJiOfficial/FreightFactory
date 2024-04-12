package utilities.game;

import mindustry.content.*;
import mindustry.type.SectorPreset;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.meta.BuildVisibility;

public class FVanillaChange {

    public static void init() {

        /*
        UnitSetHidden(
            UnitTypes.dagger, UnitTypes.mace, UnitTypes.fortress, UnitTypes.scepter, UnitTypes.reign,
            UnitTypes.nova, UnitTypes.pulsar, UnitTypes.quasar, UnitTypes.vela, UnitTypes.corvus,
            UnitTypes.crawler, UnitTypes.atrax, UnitTypes.spiroct, UnitTypes.arkyid, UnitTypes.toxopid,
            UnitTypes.flare, UnitTypes.horizon, UnitTypes.zenith, UnitTypes.antumbra, UnitTypes.eclipse,
            UnitTypes.mono, UnitTypes.poly, UnitTypes.mega, UnitTypes.quad, UnitTypes.oct,
            UnitTypes.risso, UnitTypes.minke, UnitTypes.bryde, UnitTypes.sei, UnitTypes.omura,
            UnitTypes.retusa, UnitTypes.oxynoe, UnitTypes.cyerce, UnitTypes.aegires, UnitTypes.navanax,
            UnitTypes.stell, UnitTypes.locus, UnitTypes.precept, UnitTypes.vanquish, UnitTypes.conquer,
            UnitTypes.merui, UnitTypes.cleroi, UnitTypes.anthicus, UnitTypes.tecta, UnitTypes.collaris,
            UnitTypes.elude, UnitTypes.avert, UnitTypes.obviate, UnitTypes.quell, UnitTypes.disrupt,
            UnitTypes.alpha, UnitTypes.beta, UnitTypes.gamma,
            UnitTypes.evoke, UnitTypes.incite, UnitTypes.emanate
        );

         */


        BlockSetHidden(

            //turrets
            Blocks.duo, Blocks.scatter, Blocks.scorch, Blocks.hail,
            Blocks.wave, Blocks.lancer, Blocks.arc, Blocks.parallax,
            Blocks.swarmer, Blocks.salvo, Blocks.segment, Blocks.tsunami,
            Blocks.fuse, Blocks.ripple, Blocks.cyclone, Blocks.foreshadow,
            Blocks.spectre, Blocks.meltdown,
            Blocks.breach, Blocks.diffuse, Blocks.sublimate, Blocks.titan,
            Blocks.disperse, Blocks.afflict, Blocks.lustre, Blocks.scathe,
            Blocks.smite, Blocks.malign,

            //drills
            Blocks.mechanicalDrill, Blocks.pneumaticDrill, Blocks.laserDrill, Blocks.blastDrill,
            Blocks.waterExtractor, Blocks.cultivator, Blocks.oilExtractor, Blocks.ventCondenser,
            Blocks.cliffCrusher, Blocks.plasmaBore, Blocks.largePlasmaBore,
            //Blocks.impactDrill, Blocks.eruptionDrill,

            //todo current don't change this

            //transport
            Blocks.conveyor, Blocks.titaniumConveyor, Blocks.plastaniumConveyor, Blocks.armoredConveyor,
            Blocks.junction, Blocks.itemBridge, Blocks.phaseConveyor, Blocks.sorter,
            Blocks.invertedSorter, Blocks.router, Blocks.distributor, Blocks.overflowGate,
            Blocks.underflowGate, Blocks.massDriver,
            Blocks.duct,
            Blocks.armoredDuct,
            Blocks.ductRouter, Blocks.overflowDuct, Blocks.underflowDuct, Blocks.ductBridge,
            Blocks.ductUnloader, Blocks.surgeConveyor, Blocks.surgeRouter, Blocks.unitCargoLoader,
            Blocks.unitCargoUnloadPoint,

            //liquid
            //Blocks.mechanicalPump, Blocks.rotaryPump, Blocks.impulsePump,
            Blocks.conduit,
            /*Blocks.pulseConduit, */Blocks.armoredConveyor, /*Blocks.liquidRouter, Blocks.liquidContainer,
            Blocks.liquidTank, Blocks.liquidJunction, Blocks.bridgeConduit, */Blocks.phaseConduit,
            Blocks.reinforcedPump, Blocks.reinforcedConduit, Blocks.reinforcedLiquidJunction, Blocks.reinforcedBridgeConduit,
            Blocks.reinforcedLiquidRouter, Blocks.reinforcedLiquidContainer, Blocks.reinforcedLiquidTank,

            //power

            /*Blocks.powerNode, Blocks.powerNodeLarge, */Blocks.surgeTower, Blocks.diode,
            /*Blocks.battery, Blocks.batteryLarge, */Blocks.combustionGenerator, Blocks.thermalGenerator,
            Blocks.steamGenerator, Blocks.differentialGenerator, Blocks.rtgGenerator, Blocks.solarPanel,
            Blocks.largeSolarPanel, Blocks.thoriumReactor, Blocks.impactReactor, Blocks.beamNode,
            Blocks.beamTower, Blocks.turbineCondenser, Blocks.chemicalCombustionChamber, Blocks.pyrolysisGenerator,
            Blocks.fluxReactor, Blocks.neoplasiaReactor,

            //defense
            Blocks.copperWall, Blocks.copperWallLarge, Blocks.titaniumWall, Blocks.titaniumWallLarge,
            Blocks.plastaniumWall, Blocks.plastaniumWallLarge, Blocks.thoriumWall, Blocks.thoriumWallLarge,
            Blocks.phaseWall, Blocks.phaseWallLarge, Blocks.surgeWall, Blocks.surgeWallLarge,
            Blocks.door, Blocks.doorLarge, Blocks.scrapWall, Blocks.scrapWallLarge,
            Blocks.scrapWallHuge, Blocks.scrapWallGigantic, Blocks.thruster,
            Blocks.berylliumWall, Blocks.berylliumWallLarge, Blocks.tungstenWall, Blocks.tungstenWallLarge,
            Blocks.blastDoor, Blocks.reinforcedSurgeWall, Blocks.reinforcedSurgeWallLarge, Blocks.carbideWall,
            Blocks.carbideWallLarge, Blocks.shieldedWall,

            //factory
            Blocks.graphitePress, Blocks.multiPress, Blocks.siliconSmelter, Blocks.siliconCrucible,
            Blocks.kiln, Blocks.plastaniumCompressor, Blocks.phaseWeaver, Blocks.surgeSmelter,
            Blocks.cryofluidMixer, Blocks.pyratiteMixer, Blocks.blastMixer, Blocks.melter,
            Blocks.separator, Blocks.disassembler, Blocks.sporePress, Blocks.pulverizer,
            Blocks.coalCentrifuge, Blocks.incinerator, Blocks.siliconArcFurnace, Blocks.electrolyzer,
            Blocks.atmosphericConcentrator, Blocks.oxidationChamber, Blocks.electricHeater, Blocks.slagHeater,
            Blocks.phaseHeater, Blocks.heatRedirector, Blocks.heatRouter, Blocks.slagIncinerator,
            Blocks.carbideCrucible, Blocks.surgeCrucible, Blocks.cyanogenSynthesizer, Blocks.phaseSynthesizer,
            Blocks.heatSource,

            //units
            Blocks.groundFactory, Blocks.airFactory, Blocks.navalFactory, Blocks.additiveReconstructor,
            Blocks.multiplicativeReconstructor, Blocks.exponentialReconstructor, Blocks.tetrativeReconstructor, Blocks.repairPoint,
            Blocks.repairTurret,
            Blocks.tankFabricator, Blocks.shipFabricator, Blocks.mechFabricator, Blocks.tankRefabricator,
            Blocks.shipRefabricator, Blocks.mechRefabricator, Blocks.primeRefabricator, Blocks.tankAssembler,
            Blocks.shipAssembler, Blocks.mechAssembler, Blocks.basicAssemblerModule, Blocks.unitRepairTower,

            //Blocks.payloadConveyor, Blocks.payloadRouter,
            Blocks.reinforcedPayloadConveyor, Blocks.reinforcedPayloadRouter,
            Blocks.payloadMassDriver, Blocks.largePayloadMassDriver, Blocks.smallDeconstructor, Blocks.deconstructor,
            Blocks.constructor, Blocks.largeConstructor, Blocks.payloadLoader, Blocks.payloadUnloader,

            //special & storage
            Blocks.mender, Blocks.mendProjector, Blocks.overdriveProjector, Blocks.overdriveDome,
            Blocks.forceProjector, Blocks.shockMine, Blocks.buildTower, Blocks.regenProjector,
            Blocks.shockwaveTower, Blocks.coreShard, Blocks.coreFoundation, Blocks.coreNucleus,
            Blocks.coreBastion, Blocks.coreCitadel, Blocks.coreAcropolis, Blocks.container,
            Blocks.vault, Blocks.unloader, Blocks.reinforcedContainer, Blocks.reinforcedVault,

            //logic
            //I DON'T LIKE THIS BRUH
            Blocks.message, Blocks.switchBlock, Blocks.microProcessor, Blocks.logicProcessor,
            Blocks.hyperProcessor, Blocks.memoryCell, Blocks.memoryBank, Blocks.logicDisplay,
            Blocks.largeLogicDisplay, Blocks.canvas, Blocks.reinforcedMessage
        );

        SectorSetHidden(
            SectorPresets.groundZero, SectorPresets.saltFlats, SectorPresets.frozenForest,
            SectorPresets.biomassFacility, SectorPresets.craters, SectorPresets.ruinousShores,
            SectorPresets.windsweptIslands, SectorPresets.stainedMountains, SectorPresets.extractionOutpost,
            SectorPresets.coastline, SectorPresets.navalFortress, SectorPresets.fungalPass,
            SectorPresets.overgrowth, SectorPresets.tarFields, SectorPresets.impact0078,
            SectorPresets.desolateRift, SectorPresets.nuclearComplex, SectorPresets.planetaryTerminal,
            SectorPresets.onset, SectorPresets.aegis, SectorPresets.lake,
            SectorPresets.intersect, SectorPresets.atlas, SectorPresets.split,
            SectorPresets.basin, SectorPresets.marsh, SectorPresets.peaks,
            SectorPresets.ravine, SectorPresets.caldera, SectorPresets.stronghold,
            SectorPresets.crevice, SectorPresets.siege, SectorPresets.crossroads,
            SectorPresets.karst, SectorPresets.origin
        );

        StatusSetHidden(
            StatusEffects.burning, StatusEffects.freezing, StatusEffects.unmoving,
            StatusEffects.wet, StatusEffects.melting, StatusEffects.sapped,
            StatusEffects.electrified, StatusEffects.sporeSlowed, StatusEffects.tarred,
            StatusEffects.overdrive, StatusEffects.overclock, StatusEffects.boss,
            StatusEffects.shocked, StatusEffects.blasted
        );

        Planets.serpulo.accessible = false;
        Planets.erekir.accessible = false;
    }

    public static void UnitSetHidden(UnitType... units) {
        for (UnitType unit : units) {
            unit.hidden = true;
        }
    }

    public static void BlockSetHidden(Block... blocks) {
        for (Block block : blocks) {
            block.buildVisibility = BuildVisibility.hidden;
        }
    }

    public static void SectorSetHidden(SectorPreset... sectors) {
        for (SectorPreset sector : sectors) {
            sector.description = null;
        }
    }

    public static void StatusSetHidden(StatusEffect... statuses) {
        for (StatusEffect status : statuses) {
            status.show = false;
        }
    }

}
