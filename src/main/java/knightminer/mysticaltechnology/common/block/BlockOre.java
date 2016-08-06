package knightminer.mysticaltechnology.common.block;

import java.util.Locale;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import knightminer.mysticaltechnology.common.MystTechCommons;
import knightminer.mysticaltechnology.library.MystTechRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.mantle.block.EnumBlock;

public class BlockOre extends EnumBlock<BlockOre.OreType> {

	public static final PropertyEnum<OreType> TYPE = PropertyEnum.create("type", OreType.class);
	public BlockOre() {
		super(Material.ROCK, TYPE, OreType.class);

		setHardness(5f);
		setCreativeTab(MystTechRegistry.tabCommons);

		// harvest levels
		setHarvestLevel(OreType.HEATSTONE, 2);
		setHarvestLevel(OreType.WINDSTONE, 3);
		setHarvestLevel(OreType.COLDSTONE, 2);
		setHarvestLevel(OreType.VIBRANIUM, 3);
		setHarvestLevel(OreType.COLDSILVER, 2);
		//setHarvestLevel(OreType.?, 1);
	}

	/**
	 * Sets the pickaxe mining level from the OreType enum
	 * @param type Ore type value for state
	 * @param level Mining level. 0 is wood, 1 stone, 2 iron, 3 diamond
	 */
	private void setHarvestLevel(OreType type, int level) {
		setHarvestLevel("pickaxe", level, getDefaultState().withProperty(TYPE, type));
	}

	// drop the item for gemstone ores
	@Override
	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		switch(state.getValue(TYPE)) {
			case HEATSTONE: case WINDSTONE: case COLDSTONE:
				return MystTechCommons.materials;
		}

		return Item.getItemFromBlock(this);
	}

	// and apply the proper damage for it too
	@Override
	public int damageDropped(IBlockState state) {
		switch(state.getValue(TYPE)) {
			case HEATSTONE:
				return MystTechCommons.heatstone.getItemDamage();
			case WINDSTONE:
				return MystTechCommons.windstone.getItemDamage();
			case COLDSTONE:
				return MystTechCommons.coldstone.getItemDamage();
		}

		return state.getValue(TYPE).getMeta();
	}

	// boost drops if mined with fortune
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(state, random, fortune)) {
			int i = random.nextInt(fortune + 2) - 1;

			if (i < 0) {
				i = 0;
			}

			return this.quantityDropped(random) * (i + 1);
		}
		else {
			return this.quantityDropped(random);
		}
	}

	// restore pickblock behavior, as otherwise it returns the special drop
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(this);
	}

	// drop exp
	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		Random rand = world instanceof World ? ((World)world).rand : new Random();

		// don't do anything if silktouch or metal
		if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)) {
			return MathHelper.getRandomIntegerInRange(rand, 3, 7);
		}

		return 0;
	}

	// I don't actually use netherrack/end stone in the texture, just an overlay
	@Nonnull
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	public enum OreType implements IStringSerializable, EnumBlock.IEnumMeta {
		HEATSTONE,
		WINDSTONE,
		COLDSTONE,
		VIBRANIUM,
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

