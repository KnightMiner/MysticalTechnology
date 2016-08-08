package knightminer.mysticaltechnology.lasers.tileentity;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileLaserBeam extends TileFacing {

	public static final String TAG_MASTER_X = "masterX";
	public static final String TAG_MASTER_Y = "masterY";
	public static final String TAG_MASTER_Z = "masterZ";

	@Nullable
	private BlockPos masterPos;

	public void notifyMaserOfChange() {
		if(masterPos != null) {
			TileEntity te = this.worldObj.getTileEntity(masterPos);
			if(te instanceof TileLaserBeamProvider) {
				((TileLaserBeamProvider) te).notifyChange(worldObj.getBlockState(pos), pos);
			}
		}
	}

	/**
	 * Sets the position of the master laser provider
	 */
	public void setMasterPos(BlockPos pos) {
		masterPos = pos;
	}

	@Nullable
	public BlockPos getMasterPos() {
		return masterPos;
	}

	@Override
	public void readFromNBT(NBTTagCompound tags) {
		super.readFromNBT(tags);

		if(tags.hasKey(TAG_MASTER_X)) {
			int x = tags.getInteger(TAG_MASTER_X);
			int y = tags.getInteger(TAG_MASTER_Y);
			int z = tags.getInteger(TAG_MASTER_Z);
			this.masterPos = new BlockPos(x, y, z);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tags) {
		tags = super.writeToNBT(tags);

		if(masterPos != null) {
			tags.setInteger(TAG_MASTER_X, masterPos.getX());
			tags.setInteger(TAG_MASTER_Y, masterPos.getY());
			tags.setInteger(TAG_MASTER_Z, masterPos.getZ());
		}

		return tags;
	}
}
