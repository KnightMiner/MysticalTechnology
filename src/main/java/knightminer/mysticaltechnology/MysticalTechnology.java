package knightminer.mysticaltechnology;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import knightminer.mysticaltechnology.common.MystTechCommons;
import knightminer.mysticaltechnology.core.MystTechOredict;
import knightminer.mysticaltechnology.core.config.Config;
import knightminer.mysticaltechnology.lasers.MystTechLasers;
import knightminer.mysticaltechnology.library.Util;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import slimeknights.mantle.common.GuiHandler;
import slimeknights.mantle.pulsar.control.PulseManager;

/**
 * Mystical Technology
 * <br>
 * A Minecraft mod about harnessing the power of magic using technology
 * @author KnightMiner
 *
 */
@Mod(modid = MysticalTechnology.modID,
name = MysticalTechnology.modName,
version = MysticalTechnology.modVersion,
dependencies = "required-after:Forge@[12.18.0.1993,);"
		+ "required-after:mantle@[1.10-0.10.3,)",
		acceptedMinecraftVersions = "[1.10.2, 1.11)")
public class MysticalTechnology {
	public static final String modID = Util.MODID;
	public static final String modName = "Mystical Technology";
	public static final String modVersion = "${version}";

	public static final Logger log = LogManager.getLogger(modID);

	@Mod.Instance(modID)
	public static MysticalTechnology instance;

	public static PulseManager pulseManager = new PulseManager(Config.pulseConfig);
	public static GuiHandler guiHandler = new GuiHandler();

	// Pulses
	static {
		pulseManager.registerPulse(new MystTechCommons());
		pulseManager.registerPulse(new MystTechLasers());
		pulseManager.registerPulse(new MystTechOredict());
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.load(event);

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, guiHandler);

		// setup network stuff later if needed
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {}

	@Mod.EventHandler
	public void onMissingMapping(FMLMissingMappingsEvent event) {}

}
