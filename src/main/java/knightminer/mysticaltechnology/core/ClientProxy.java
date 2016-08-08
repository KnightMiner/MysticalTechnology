package knightminer.mysticaltechnology.core;

import javax.annotation.Nonnull;

import knightminer.mysticaltechnology.MysticalTechnology;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import slimeknights.mantle.item.ItemBlockMeta;

public class ClientProxy extends CommonProxy {

	protected void registerItemBlockMeta(Block block) {
		if(block != null) {
			((ItemBlockMeta) Item.getItemFromBlock(block)).registerItemModels();
		}
		else {
			MysticalTechnology.log.error("Its null bro");
		}
	}

	public ResourceLocation registerItemModel(Item item) {
		ResourceLocation itemLocation = getItemLocation(item);
		if(itemLocation == null) {
			// no set means no loaded
			return null;
		}

		return registerIt(item, itemLocation);
	}

	public ResourceLocation registerItemModel(Block block) {
		return registerItemModel(Item.getItemFromBlock(block));
	}

	public void registerItemModel(Item item, int meta, String variant) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), variant));
	}

	private static ResourceLocation registerIt(Item item, final ResourceLocation location) {
		ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition() {
			@Nonnull
			@Override
			public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
				return new ModelResourceLocation(location, "inventory");
			}
		});

		ModelLoader.registerItemVariants(item, location);

		return location;
	}

	protected ResourceLocation getItemLocation(Item item) {
		return item.getRegistryName();
	}
}
