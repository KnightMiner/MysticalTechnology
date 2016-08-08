package knightminer.mysticaltechnology.lasers;

import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.Subscribe;

import knightminer.mysticaltechnology.core.CommonProxy;
import knightminer.mysticaltechnology.core.MystTechPulse;
import knightminer.mysticaltechnology.lasers.block.BlockLaser;
import knightminer.mysticaltechnology.lasers.block.BlockLaserBeam;
import knightminer.mysticaltechnology.lasers.block.BlockLense;
import knightminer.mysticaltechnology.lasers.tileentity.TileLaser;
import knightminer.mysticaltechnology.lasers.tileentity.TileLaserBeam;
import knightminer.mysticaltechnology.lasers.tileentity.TileLense;
import knightminer.mysticaltechnology.library.Util;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.mantle.pulsar.pulse.Pulse;

@Pulse(id = MystTechLasers.pulseID, description = "The laser power transfer system and infusion crafting")
public class MystTechLasers extends MystTechPulse {
	public static final String pulseID = "MystTechLasers";

	static final Logger log = Util.getLogger(pulseID);

	@SidedProxy(clientSide = "knightminer.mysticaltechnology.lasers.LasersClientProxy", serverSide = "knightminer.mysticaltechnology.core.CommonProxy")
	public static CommonProxy proxy;

	// Blocks
	public static Block laser;
	public static Block laserBeam;
	public static Block lense;

	@Subscribe
	public void preInit(FMLPreInitializationEvent event) {
		laser = registerEnumBlock(new BlockLaser(), "laser");
		laserBeam = registerBlockNoItem(new BlockLaserBeam(), "laser_beam");
		lense = registerEnumBlock(new BlockLense(), "lense");

		registerTileEntity(TileLaser.class, "laser");
		registerTileEntity(TileLaserBeam.class, "laser_beam");
		registerTileEntity(TileLense.class, "lense");

		proxy.preInit();
	}

	@Subscribe
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}
}
