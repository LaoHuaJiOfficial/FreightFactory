package extra;

import arc.func.Cons2;
import arc.struct.ObjectMap;
import mindustry.content.Blocks;
import mindustry.content.Planets;
import mindustry.content.UnitTypes;
import mindustry.ctype.UnlockableContent;
import mindustry.gen.Building;
import mindustry.type.SectorPreset;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.meta.BuildVisibility;

public class FVanillaChange{

    public static void init(){
        //Units
        UnitTypes.dagger.hidden = true;
        UnitTypes.mace.hidden = true;
        UnitTypes.fortress.hidden = true;
        UnitTypes.scepter.hidden = true;
        UnitTypes.reign.hidden = true;

        UnitTypes.nova.hidden = true;
        UnitTypes.pulsar.hidden = true;
        UnitTypes.quasar.hidden = true;
        UnitTypes.vela.hidden = true;
        UnitTypes.corvus.hidden = true;

        UnitTypes.crawler.hidden = true;
        UnitTypes.atrax.hidden = true;
        UnitTypes.spiroct.hidden = true;
        UnitTypes.arkyid.hidden = true;
        UnitTypes.toxopid.hidden = true;

        UnitTypes.flare.hidden = true;
        UnitTypes.horizon.hidden = true;
        UnitTypes.zenith.hidden = true;
        UnitTypes.antumbra.hidden = true;
        UnitTypes.eclipse.hidden = true;

        UnitTypes.mono.hidden = true;
        UnitTypes.poly.hidden = true;
        UnitTypes.mega.hidden = true;
        UnitTypes.quad.hidden = true;
        UnitTypes.oct.hidden = true;

        UnitTypes.risso.hidden = true;
        UnitTypes.minke.hidden = true;
        UnitTypes.bryde.hidden = true;
        UnitTypes.sei.hidden = true;
        UnitTypes.omura.hidden = true;

        UnitTypes.retusa.hidden = true;
        UnitTypes.oxynoe.hidden = true;
        UnitTypes.cyerce.hidden = true;
        UnitTypes.aegires.hidden = true;
        UnitTypes.navanax.hidden = true;

        UnitTypes.stell.hidden = true;
        UnitTypes.locus.hidden = true;
        UnitTypes.precept.hidden = true;
        UnitTypes.vanquish.hidden = true;
        UnitTypes.conquer.hidden = true;

        UnitTypes.merui.hidden = true;
        UnitTypes.cleroi.hidden = true;
        UnitTypes.anthicus.hidden = true;
        UnitTypes.tecta.hidden = true;
        UnitTypes.collaris.hidden = true;

        UnitTypes.elude.hidden = true;
        UnitTypes.avert.hidden = true;
        UnitTypes.obviate.hidden = true;
        UnitTypes.quell.hidden = true;
        UnitTypes.disrupt.hidden = true;

        UnitTypes.alpha.hidden = true;
        UnitTypes.beta.hidden = true;
        UnitTypes.gamma.hidden = true;

        UnitTypes.evoke.hidden = true;
        UnitTypes.incite.hidden = true;
        UnitTypes.emanate.hidden = true;

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
            Blocks.cliffCrusher, Blocks.plasmaBore, Blocks.largePlasmaBore, Blocks.impactDrill,
            Blocks.eruptionDrill,

            //todo current don't change this

            //transport
            Blocks.conveyor, /*Blocks.titaniumConveyor,*/ Blocks.plastaniumConveyor, Blocks.armoredConveyor,
            /*Blocks.junction, Blocks.itemBridge, Blocks.phaseConveyor, Blocks.sorter,
            Blocks.invertedSorter, Blocks.router, Blocks.distributor, Blocks.overflowGate,
            Blocks.underflowGate, */Blocks.massDriver, Blocks.duct, Blocks.armoredDuct,
            Blocks.ductRouter, Blocks.overflowDuct, Blocks.underflowDuct, Blocks.ductBridge,
            Blocks.ductUnloader, Blocks.surgeConveyor, Blocks.surgeRouter, Blocks.unitCargoLoader,
            Blocks.unitCargoUnloadPoint,

            //liquid
            Blocks.mechanicalPump, Blocks.rotaryPump, Blocks.impulsePump, Blocks.conduit,
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
            Blocks.carbideCrucible,Blocks.surgeCrucible, Blocks.cyanogenSynthesizer, Blocks.phaseSynthesizer,
            Blocks.heatSource,

            //units
            Blocks.groundFactory, Blocks.airFactory, Blocks.navalFactory, Blocks.additiveReconstructor,
            Blocks.multiplicativeReconstructor, Blocks.exponentialReconstructor, Blocks.tetrativeReconstructor, Blocks.repairPoint,
            Blocks.repairTurret,
            Blocks.tankFabricator, Blocks.shipFabricator, Blocks.mechFabricator, Blocks.tankRefabricator,
            Blocks.shipRefabricator, Blocks.mechRefabricator, Blocks.primeRefabricator,Blocks.tankAssembler,
            Blocks.shipAssembler, Blocks.mechAssembler, Blocks.basicAssemblerModule, Blocks.unitRepairTower,
            Blocks.payloadConveyor, Blocks.payloadRouter, Blocks.reinforcedPayloadConveyor, Blocks.reinforcedPayloadRouter,
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

        Planets.serpulo.accessible = false;
        Planets.erekir.accessible = false;

    }

    public static void BlockSetHidden(Block...blocks){
        for (Block block: blocks){
            block.buildVisibility = BuildVisibility.hidden;
        }
    }

}
