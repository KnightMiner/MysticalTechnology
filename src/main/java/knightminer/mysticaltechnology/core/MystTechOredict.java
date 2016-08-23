package knightminer.mysticaltechnology.core;

import com.google.common.eventbus.Subscribe;

import knightminer.mysticaltechnology.common.MystTechCommons;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.mantle.pulsar.pulse.Pulse;

/**
 * oredicts ALL the things in TConstruct.
 * Also all in one place!
 */
@Pulse(id = MystTechOredict.pulseID, forced = true)
public class MystTechOredict {

	public static final String pulseID = "MystTechOredict";


	@Subscribe
	public static void dictionaryTime(FMLPreInitializationEvent event) {
		ensureOredict();
		registerCommon();
	}

	// things from other mods that should be oredicted here
	private static void ensureOredict() {
		oredict(Items.EMERALD, "gemTerra", "gemElemental");
	}

	// commons!
	private static void registerCommon() {
		oredict(MystTechCommons.heatstone, "gemBlaze", "gemElemental");
		oredict(MystTechCommons.icestone, "gemFrost", "gemElemental");
		oredict(MystTechCommons.windstone, "gemAer", "gemElemental");

		oredict(MystTechCommons.ingotFlamesteel, "ingotFlamesteel");
		oredict(MystTechCommons.ingotVibranium, "ingotVibranium");
		oredict(MystTechCommons.ingotColdsilver, "ingotColdsilver");
		oredict(MystTechCommons.ingotLightmetal, "ingotLightmetal");
		oredict(MystTechCommons.ingotVoidmetal, "ingotVoidmetal");

		oredict(MystTechCommons.nuggetFlamesteel, "nuggetFlamesteel");
		oredict(MystTechCommons.nuggetVibranium, "nuggetVibranium");
		oredict(MystTechCommons.nuggetColdsilver, "nuggetColdsilver");
		oredict(MystTechCommons.nuggetLightmetal, "nuggetLightmetal");
		oredict(MystTechCommons.nuggetVoidmetal, "nuggetVoidmetal");
		oredict(MystTechCommons.nuggetIron, "nuggetIron");
	}


	/* Helper functions */

	public static void oredict(Item item, String... name) {
		oredict(item, OreDictionary.WILDCARD_VALUE, name);
	}

	public static void oredict(Block block, String... name) {
		oredict(block, OreDictionary.WILDCARD_VALUE, name);
	}

	public static void oredict(Item item, int meta, String... name) {
		oredict(new ItemStack(item, 1, meta), name);
	}

	public static void oredict(Block block, int meta, String... name) {
		oredict(new ItemStack(block, 1, meta), name);
	}

	public static void oredict(ItemStack stack, String... names) {
		if(stack != null && stack.getItem() != null) {
			for(String name : names) {
				OreDictionary.registerOre(name, stack);
			}
		}
	}
}
