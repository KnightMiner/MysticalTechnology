package knightminer.mysticaltechnology.library;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.util.ResourceLocation;

public class Util {
	public static final String MODID = "mysttech";

	/* Mod ID prefixes */
	public static ResourceLocation getResource(String res) {
		return new ResourceLocation(MODID, res);
	}

	public static String prefix(String name) {
		return String.format("%s.%s", MODID, name.toLowerCase(Locale.US));
	}

	public static Logger getLogger(String type) {
		return LogManager.getLogger(MODID + "-" + type);
	}
}
