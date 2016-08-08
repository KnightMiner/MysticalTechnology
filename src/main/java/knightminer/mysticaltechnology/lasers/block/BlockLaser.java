package knightminer.mysticaltechnology.lasers.block;

import javax.annotation.Nullable;

import knightminer.mysticaltechnology.lasers.tileentity.TileLaser;
import knightminer.mysticaltechnology.library.MystTechRegistry;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLaser extends BlockElementFacing {

	public BlockLaser() {
		super(Material.IRON);

		setHardness(3.5f);
		setSoundType(SoundType.METAL);
		setCreativeTab(MystTechRegistry.tabCommons);
		setHarvestLevel("pickaxe", 1);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileLaser();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TileLaser) {
			((TileLaser)te).updateState();

			return true;
		}

		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}
