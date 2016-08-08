package knightminer.mysticaltechnology.lasers.block;

import knightminer.mysticaltechnology.lasers.tileentity.TileLense;
import knightminer.mysticaltechnology.library.MystTechRegistry;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLense extends BlockElementFacing {

	public BlockLense() {
		super(Material.IRON);

		setHardness(3.5f);
		setSoundType(SoundType.GLASS);
		setCreativeTab(MystTechRegistry.tabCommons);
		setHarvestLevel("pickaxe", 1);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileLense();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks for render
	 */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	/**
	 * Checks if an IBlockState represents a block that is opaque and a full cube.
	 */
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
}
