package knightminer.mysticaltechnology.common.block;

import java.util.Locale;

import knightminer.mysticaltechnology.library.MystTechRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;
import slimeknights.mantle.block.EnumBlock;

public class BlockStorage extends EnumBlock<BlockStorage.StorageType> {

	public static final PropertyEnum<StorageType> TYPE = PropertyEnum.create("type", StorageType.class);
	public BlockStorage() {
		super(Material.ROCK, TYPE, StorageType.class);

		setHardness(5f);
		setCreativeTab(MystTechRegistry.tabCommons);

		// harvest levels
		setHarvestLevel("pickaxe", 2);
	}

	public enum StorageType implements IStringSerializable, EnumBlock.IEnumMeta {
		HEATSTONE,
		WINDSTONE,
		COLDSTONE,
		VIBRANIUM,
		COLDSILVER,
		LIGHTMETAL,
		VOIDMETAL;

		private final int meta;
		StorageType() {
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
