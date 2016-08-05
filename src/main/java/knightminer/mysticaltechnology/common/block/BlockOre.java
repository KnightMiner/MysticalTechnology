package knightminer.mysticaltechnology.common.block;

import java.util.Locale;

import javax.annotation.Nonnull;

import knightminer.mysticaltechnology.library.MystTechRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.mantle.block.EnumBlock;

public class BlockOre extends EnumBlock<BlockOre.OreType> {

	public static final PropertyEnum<OreType> TYPE = PropertyEnum.create("type", OreType.class);
	public BlockOre() {
		super(Material.ROCK, TYPE, OreType.class);

		setHardness(10f);
		setCreativeTab(MystTechRegistry.tabCommons);

		// harvest levels
		setHarvestLevel(OreType.HEATSTONE, 2);
		setHarvestLevel(OreType.WINDSTONE, 3);
		setHarvestLevel(OreType.COLDSTONE, 2);
		setHarvestLevel(OreType.UNNAMED_END_METAL, 3);
		setHarvestLevel(OreType.COLDSILVER, 2);
		//setHarvestLevel(OreType.?, 1);
	}

	// TODO: item drops and silk touch/experience

	/**
	 * Sets the pickaxe mining level from the OreType enum
	 * @param type Ore type value for state
	 * @param level Mining level. 0 is wood, 1 stone, 2 iron, 3 diamond
	 */
	private void setHarvestLevel(OreType type, int level) {
		setHarvestLevel("pickaxe", level, getDefaultState().withProperty(TYPE, type));
	}

	// I don't actually use netherrack/end stone in the texture, just an overlay
	@Nonnull
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	public enum OreType implements IStringSerializable, EnumBlock.IEnumMeta {
		// terrastone is emerald
		HEATSTONE,
		WINDSTONE,
		COLDSTONE,
		// terra metal is iron
		// blaze metal is gold
		UNNAMED_END_METAL,
		COLDSILVER;
		// TODO: extra myst ores
		// basically, something similar to quartz, only most likely non-redstone functions
		// jasper?
		// or maybe my old idea greenstone

		private final int meta;
		OreType() {
			meta = ordinal();
		}
		@Override
		public int getMeta() {
			return meta;
		}
		@Override
		public String getName() {
			return this.toString().toLowerCase(Locale.US);
		}
	}
}

