package knightminer.mysticaltechnology.common;

import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.Subscribe;

import knightminer.mysticaltechnology.common.block.BlockOre;
import knightminer.mysticaltechnology.core.CommonProxy;
import knightminer.mysticaltechnology.core.MystTechPulse;
import knightminer.mysticaltechnology.library.MystTechRegistry;
import knightminer.mysticaltechnology.library.Util;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.mantle.item.ItemMetaDynamic;
import slimeknights.mantle.pulsar.pulse.Pulse;

@Pulse(id = MystTechCommons.pulseID, forced = true)
public class MystTechCommons extends MystTechPulse {
	public static final String pulseID = "MystTechCommons";

	static final Logger log = Util.getLogger(pulseID);

	@SidedProxy(clientSide = "knightminer.mysticaltechnology.common.CommonsClientProxy", serverSide = "knightminer.mysticaltechnology.core.CommonProxy")
	public static CommonProxy proxy;

	// blocks
	public static BlockOre blockOre;

	// items
	public static ItemMetaDynamic materials;

	// itemstacks
	public static ItemStack chargedLightstone;
	public static ItemStack chargedVoidstone;
	public static ItemStack chargedTerrastone;
	public static ItemStack chargedBlazestone;
	public static ItemStack chargedAerstone;
	public static ItemStack chargedFroststone;

	public static ItemStack heatstone;
	public static ItemStack windstone;
	public static ItemStack coldstone;

	@Subscribe

	public void preInit(FMLPreInitializationEvent event) {
		blockOre = registerEnumBlock(new BlockOre(), "ore");

		materials = registerItem(new ItemMetaDynamic(), "materials");
		materials.setCreativeTab(MystTechRegistry.tabCommons);

		// basic stones
		materials.addMeta(0, "charged_lightstone");
		materials.addMeta(1, "charged_voidstone");
		materials.addMeta(2, "charged_terrastone");
		materials.addMeta(3, "charged_blazestone");
		materials.addMeta(4, "charged_aerstone");
		materials.addMeta(5, "charged_froststone");

		// ore stones
		materials.addMeta(6, "heatstone");
		materials.addMeta(7, "windstone");
		materials.addMeta(8, "coldstone");

		proxy.preInit();
	}

	public void init(FMLInitializationEvent event) {
		// recipes
		proxy.init();
	}
}
