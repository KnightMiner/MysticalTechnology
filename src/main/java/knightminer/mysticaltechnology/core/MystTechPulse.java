package knightminer.mysticaltechnology.core;

import knightminer.mysticaltechnology.MysticalTechnology;
import knightminer.mysticaltechnology.lasers.MystTechLasers;
import knightminer.mysticaltechnology.library.Util;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import slimeknights.mantle.block.EnumBlock;
import slimeknights.mantle.item.ItemBlockMeta;

public class MystTechPulse {
	protected static boolean isLasersLoaded() {
		return MysticalTechnology.pulseManager.isPulseLoaded(MystTechLasers.pulseID);
	}

	protected static boolean isMystLoaded() {
		// TODO: incomplete, just here for compat so far
		// basically, a few things will act differently if the myst is not loaded to compensate
		//return MysticalTechnology.pulseManager.isPulseLoaded(MystTechMyst.pulseID);
		return false;
	}

	/* Blocks */
	protected static <T extends Block> T registerBlock(T block, String name) {
		ItemBlock itemBlock = new ItemBlockMeta(block);
		registerBlock(block, itemBlock, name);
		return block;
	}

	protected static <T extends Block> T registerBlock(T block, String name, IProperty<?> property) {
		ItemBlockMeta itemBlock = new ItemBlockMeta(block);
		registerBlock(block, itemBlock, name);
		ItemBlockMeta.setMappingProperty(block, property);
		return block;
	}

	@SuppressWarnings("unchecked") // we know the type of T
	protected static <T extends Block> T registerBlock(ItemBlock itemBlock, String name) {
		Block block = itemBlock.getBlock();
		return (T) registerBlock(block, itemBlock, name);
	}

	protected static <T extends Block> T registerBlock(T block, ItemBlock itemBlock, String name) {
		String prefixedName = Util.prefix(name);
		block.setUnlocalizedName(prefixedName);
		itemBlock.setUnlocalizedName(prefixedName);

		register(block, name);
		register(itemBlock, name);
		return block;
	}

	protected static <T extends Block> T registerBlockNoItem(T block, String name) {
		block.setUnlocalizedName(Util.prefix(name));
		register(block, name);
		return block;
	}

	protected static <T extends EnumBlock<?>> T registerEnumBlock(T block, String name) {
		registerBlock(block, new ItemBlockMeta(block), name);
		ItemBlockMeta.setMappingProperty(block, block.prop);
		return block;
	}

	/* Items */
	protected static <T extends Item> T registerItem(T item, String name) {
		item.setUnlocalizedName(Util.prefix(name));
		register(item, name);
		return item;
	}

	/* TE */
	protected static void registerTileEntity(Class<? extends TileEntity> teClazz, String name) {
		GameRegistry.registerTileEntity(teClazz, Util.prefix(name));
	}

	/* Misc */
	protected static <T extends IForgeRegistryEntry<?>> T register(T entry, String name) {
		entry.setRegistryName(Util.getResource(name));
		GameRegistry.register(entry);
		return entry;
	}

}
