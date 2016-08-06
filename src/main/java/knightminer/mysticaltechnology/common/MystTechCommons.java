package knightminer.mysticaltechnology.common;

import javax.annotation.Nullable;

import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.Subscribe;

import knightminer.mysticaltechnology.common.block.BlockOre;
import knightminer.mysticaltechnology.common.block.BlockStorage;
import knightminer.mysticaltechnology.core.CommonProxy;
import knightminer.mysticaltechnology.core.MystTechPulse;
import knightminer.mysticaltechnology.library.MystTechRegistry;
import knightminer.mysticaltechnology.library.Util;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
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
	public static BlockStorage blockStorage;

	// items
	public static ItemMetaDynamic materials;

	// itemstacks
	public static ItemStack chargedTerrastone;
	public static ItemStack chargedBlazestone;
	public static ItemStack chargedAerstone;
	public static ItemStack chargedFroststone;
	public static ItemStack chargedLightstone;
	public static ItemStack chargedVoidstone;

	public static ItemStack heatstone;
	public static ItemStack windstone;
	public static ItemStack coldstone;

	public static ItemStack rodTerra;
	public static ItemStack rodAer;
	public static ItemStack rodFrost;
	public static ItemStack rodLight;
	public static ItemStack rodVoid;

	public static ItemStack powderTerra;
	public static ItemStack powderAer;
	public static ItemStack powderFrost;
	public static ItemStack powderLight;
	public static ItemStack powderVoid;

	public static ItemStack ingotVibranium;
	public static ItemStack ingotColdsilver;
	public static ItemStack ingotLightmetal;
	public static ItemStack ingotVoidmetal;

	public static ItemStack nuggetVibranium;
	public static ItemStack nuggetColdsilver;
	public static ItemStack nuggetLightmetal;
	public static ItemStack nuggetVoidmetal;
	public static ItemStack nuggetIron;

	@Subscribe
	public void preInit(FMLPreInitializationEvent event) {
		blockOre = registerEnumBlock(new BlockOre(), "ore");
		blockStorage = registerEnumBlock(new BlockStorage(), "storage_block");

		materials = registerItem(new ItemMetaDynamic(), "materials");
		materials.setCreativeTab(MystTechRegistry.tabCommons);

		// basic stones
		chargedTerrastone = materials.addMeta(0, "charged_terrastone");
		chargedBlazestone = materials.addMeta(1, "charged_blazestone");
		chargedAerstone = materials.addMeta(2, "charged_aerstone");
		chargedFroststone = materials.addMeta(3, "charged_froststone");
		chargedLightstone = materials.addMeta(4, "charged_lightstone");
		chargedVoidstone = materials.addMeta(5, "charged_voidstone");

		// ore stones
		heatstone = materials.addMeta(6, "heatstone");
		windstone = materials.addMeta(7, "windstone");
		coldstone = materials.addMeta(8, "coldstone");

		// rods
		rodTerra = materials.addMeta(10, "rod_terra");
		rodAer = materials.addMeta(11, "rod_aer");
		rodFrost = materials.addMeta(12, "rod_frost");
		rodLight = materials.addMeta(13, "rod_light");
		rodVoid = materials.addMeta(14, "rod_void");

		// powders
		powderTerra = materials.addMeta(15, "powder_terra");
		powderAer = materials.addMeta(16, "powder_aer");
		powderFrost = materials.addMeta(17, "powder_frost");
		powderLight = materials.addMeta(18, "powder_light");
		powderVoid = materials.addMeta(19, "powder_void");

		// ingots
		ingotVibranium = materials.addMeta(20, "ingot_vibranium");
		ingotColdsilver = materials.addMeta(21, "ingot_coldsilver");
		ingotLightmetal = materials.addMeta(22, "ingot_lightmetal");
		ingotVoidmetal = materials.addMeta(23, "ingot_voidmetal");

		// nuggets
		nuggetVibranium = materials.addMeta(25, "nugget_vibranium");
		nuggetColdsilver = materials.addMeta(26, "nugget_coldsilver");
		nuggetLightmetal = materials.addMeta(27, "nugget_lightmetal");
		nuggetVoidmetal = materials.addMeta(28, "nugget_voidmetal");
		nuggetIron = materials.addMeta(29, "nugget_iron");

		proxy.preInit();
	}

	@Subscribe
	public void init(FMLInitializationEvent event) {
		// recipes
		addRecipes();

		proxy.init();
	}

	private static String[] PAT_CHARGE = new String[]{" p ", "psp", " p "};
	private void addRecipes() {

		GameRegistry.addSmelting(new ItemStack(blockOre, 1, BlockOre.OreType.HEATSTONE.getMeta()), heatstone.copy(), 1f);
		GameRegistry.addSmelting(new ItemStack(blockOre, 1, BlockOre.OreType.WINDSTONE.getMeta()), windstone.copy(), 1f);
		GameRegistry.addSmelting(new ItemStack(blockOre, 1, BlockOre.OreType.COLDSTONE.getMeta()), coldstone.copy(), 1f);

		addMetalRecipes(new ItemStack(blockStorage, 1, BlockStorage.StorageType.HEATSTONE.getMeta()), heatstone, null);
		addMetalRecipes(new ItemStack(blockStorage, 1, BlockStorage.StorageType.WINDSTONE.getMeta()), windstone, null);
		addMetalRecipes(new ItemStack(blockStorage, 1, BlockStorage.StorageType.COLDSTONE.getMeta()), coldstone, null);

		// charging stones
		// TODO: oredict
		GameRegistry.addRecipe(new ShapedOreRecipe(chargedTerrastone.copy(), PAT_CHARGE, 'p', powderTerra, 's', Items.EMERALD));
		GameRegistry.addRecipe(new ShapedOreRecipe(chargedBlazestone.copy(), PAT_CHARGE, 'p', Items.BLAZE_POWDER, 's', heatstone));
		GameRegistry.addRecipe(new ShapedOreRecipe(chargedAerstone.copy(), PAT_CHARGE, 'p', powderAer, 's', windstone));
		GameRegistry.addRecipe(new ShapedOreRecipe(chargedFroststone.copy(), PAT_CHARGE, 'p', powderFrost, 's', coldstone));

		// GameRegistry.addRecipe(new ShapedOreRecipe(chargedLightstone.copy(), PAT_CHARGE, 'p', powderLight, 's', "gemElemental"));
		// GameRegistry.addRecipe(new ShapelessOreRecipe(chargedLightstone.copy(), powderTerra, Items.BLAZE_POWDER, powderAer, powderFrost, powderFrost, "gemElemental"));

		// GameRegistry.addRecipe(new ShapedOreRecipe(chargedVoidstone.copy(), PAT_CHARGE, 'p', powderVoid, 's', "gemElemental"));

		// breaking down rods
		addPowderRecipe(powderTerra, rodTerra);
		addPowderRecipe(powderAer, rodAer);
		addPowderRecipe(powderFrost, rodFrost);
		addPowderRecipe(powderLight, rodLight);
		addPowderRecipe(powderVoid, rodVoid);

		// metals
		GameRegistry.addSmelting(new ItemStack(blockOre, 1, BlockOre.OreType.VIBRANIUM.getMeta()), ingotVibranium.copy(), 1f);
		GameRegistry.addSmelting(new ItemStack(blockOre, 1, BlockOre.OreType.COLDSILVER.getMeta()), ingotColdsilver.copy(), 0.7f);
		addMetalRecipes(new ItemStack(blockStorage, 1, BlockStorage.StorageType.VIBRANIUM.getMeta()), ingotVibranium, nuggetVibranium);
		addMetalRecipes(new ItemStack(blockStorage, 1, BlockStorage.StorageType.COLDSILVER.getMeta()), ingotColdsilver, nuggetColdsilver);
		addMetalRecipes(new ItemStack(blockStorage, 1, BlockStorage.StorageType.LIGHTMETAL.getMeta()), ingotLightmetal, nuggetLightmetal);
		addMetalRecipes(new ItemStack(blockStorage, 1, BlockStorage.StorageType.VOIDMETAL.getMeta()), ingotVoidmetal, nuggetVoidmetal);
		addMetalRecipes(null, new ItemStack(Items.IRON_INGOT), nuggetIron);
	}

	private void addPowderRecipe(ItemStack powder, ItemStack rod) {
		powder = powder.copy();
		powder.stackSize = 2;
		GameRegistry.addShapelessRecipe(powder, rod);
	}

	private static String[] PAT_FULL = new String[]{"###", "###", "###"};
	private void addMetalRecipes(@Nullable ItemStack block, ItemStack ingot, @Nullable ItemStack nugget) {
		if(nugget != null) {
			ItemStack nuggetOut = nugget.copy();
			nuggetOut.stackSize = 9;
			GameRegistry.addShapelessRecipe(nuggetOut, ingot);
			GameRegistry.addShapedRecipe(ingot.copy(), PAT_FULL, '#', nugget);
		}

		if(block != null) {
			ItemStack ingotOut = ingot.copy();
			ingotOut.stackSize = 9;
			GameRegistry.addShapelessRecipe(ingotOut, block);
			GameRegistry.addShapedRecipe(block.copy(), PAT_FULL, '#', ingot);
		}
	}
}
