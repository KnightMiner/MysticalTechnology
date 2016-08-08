package knightminer.mysticaltechnology.lasers.block;

import javax.annotation.Nonnull;

import knightminer.mysticaltechnology.lasers.tileentity.TileFacing;
import knightminer.mysticaltechnology.library.element.EnumElement;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import slimeknights.mantle.block.EnumBlock;

/** A simple block that stores its facing in the TE */
public abstract class BlockElementFacing extends EnumBlock<EnumElement> implements ITileEntityProvider {

	public static final PropertyEnum<EnumElement> TYPE = EnumElement.PROPERTY;
	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	public BlockElementFacing(Material material) {
		super(material, EnumElement.PROPERTY, EnumElement.class);

		this.isBlockContainer = true;

		this.setDefaultState(this.getBlockState().getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

	@Nonnull
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, TYPE, FACING);
	}

	/**
	 * Get the actual Block state of this Block at the given position. This applies properties not visible in the
	 * metadata, such as fence connections.
	 */
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		state = getStateWithFacing(state, worldIn, pos);
		return state;
	}

	/**
	 * Gets the state facing without calling getActualState
	 * used to prevent from needing to call getActualState inside itself if checking the valid of a block next to this one
	 */
	public IBlockState getStateWithFacing(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TileFacing) {
			return state.withProperty(FACING, ((TileFacing)te).getFacing());
		}
		return state;
	}

	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
	 * IBlockstate
	 */
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing facing = BlockPistonBase.getFacingFromEntity(pos, placer);
		if(placer.isSneaking()) {
			facing = facing.getOpposite();
		}
		return this.getDefaultState().withProperty(TYPE, EnumElement.fromMeta(meta)).withProperty(FACING, facing);
	}

	/**
	 * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	 */
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TileFacing) {
			((TileFacing)te).setFacing(state.getValue(FACING));
		}
	}
}
