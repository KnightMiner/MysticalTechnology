package knightminer.mysticaltechnology.lasers.tileentity;

import javax.annotation.Nonnull;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileFacing extends TileEntity {

	public static final String TAG_FACING = "facing";

	public void setFacing(EnumFacing facing) {
		getTileData().setInteger(TAG_FACING, facing.getIndex());
	}

	@Nonnull
	public EnumFacing getFacing() {
		return EnumFacing.getFront(getTileData().getInteger(TAG_FACING));
	}

	// TODO: sync facing data with clients
}
