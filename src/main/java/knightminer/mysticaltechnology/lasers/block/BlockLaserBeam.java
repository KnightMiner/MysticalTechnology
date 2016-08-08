package knightminer.mysticaltechnology.lasers.block;

import javax.annotation.Nonnull;

import knightminer.mysticaltechnology.lasers.MystTechLasers;
import knightminer.mysticaltechnology.lasers.tileentity.TileLaserBeam;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLaserBeam extends BlockElementFacing {

	public static final PropertyBool BACK = PropertyBool.create("back");

	public BlockLaserBeam() {
		super(Material.VINE);

		setHardness(-1f);
		setResistance(100f);
		setSoundType(SoundType.METAL);

		this.setDefaultState(this.getBlockState().getBaseState().withProperty(FACING, EnumFacing.DOWN)
				.withProperty(BACK, Boolean.FALSE));
	}

	@Nonnull
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, TYPE, FACING, BACK);
	}

	/**
	 * Get the actual Block state of this Block at the given position. This applies properties not visible in the
	 * metadata, such as fence connections.
	 */
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		state = super.getActualState(state, worldIn, pos);

		IBlockState back = worldIn.getBlockState(pos.offset(state.getValue(FACING).getOpposite()));
		if(!back.getBlock().equals(MystTechLasers.laserBeam)) {
			state = state.withProperty(BACK, Boolean.TRUE);
		}

		return state;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileLaserBeam();
	}

	@Override
	public void breakBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TileLaserBeam) {
			((TileLaserBeam) te).notifyMaserOfChange();
		}

		super.breakBlock(worldIn, pos, state);
		worldIn.removeTileEntity(pos);
	}

	// no selection box
	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0,0,0,0,0,0);

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return BOUNDING_BOX;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	// TODO: damage entities inside
	// need to apply damage based on the element type and send a message to the TE to remove power

}
