package knightminer.mysticaltechnology.lasers.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileLaserBeam extends TileEntity {

	private BlockPos masterPos;

	public void notifyMaserOfChange() {
		TileEntity te = this.worldObj.getTileEntity(masterPos);
		if(te instanceof TileLaserBeamProvider) {
			((TileLaserBeamProvider) te).notifyChange(worldObj.getBlockState(pos), pos);
		}
		// TODO: need to store the master position

	}

}
