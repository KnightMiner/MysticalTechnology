package knightminer.mysticaltechnology.core.config;

import org.apache.logging.log4j.Logger;

import knightminer.mysticaltechnology.library.Util;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.mantle.pulsar.config.ForgeCFG;

public class Config {

	public static ForgeCFG pulseConfig = new ForgeCFG("mysttechModules", "Modules");
	public static Config instance = new Config();
	public static Logger log = Util.getLogger("Config");

	// don't construct my config yourself!
	private Config() {}

	static Configuration configFile;

	static ConfigCategory Modules;
	static ConfigCategory Gameplay;
	static ConfigCategory Worldgen;
	static ConfigCategory ClientSide;

	public static void load(FMLPreInitializationEvent event) {
		configFile = new Configuration(event.getSuggestedConfigurationFile(), "0.1", false);

		syncConfig();
	}

	// TODO: any other configuration needs
	private static void syncConfig() {}
}
