package knightminer.mysticaltechnology.lasers.block;

import javax.annotation.Nonnull;

import knightminer.mysticaltechnology.lasers.tileentity.TileLaserBeam;
import knightminer.mysticaltechnology.library.EnumElement;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import slimeknights.mantle.block.EnumBlock;

public class BlockLaserBeam extends EnumBlock<EnumElement> implements ITileEntityProvider {

	public BlockLaserBeam() {
		super(Material.VINE, EnumElement.PROPERTY, EnumElement.class);

		setHardness(-1f);
		setResistance(100f);
		setSoundType(SoundType.METAL);
		this.isBlockContainer = true;
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

}
