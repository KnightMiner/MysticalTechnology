package knightminer.mysticaltechnology.lasers.tileentity;

import net.minecraft.util.EnumFacing;

public class TileLaser extends TileLaserBeamProvider {

	boolean temp;

	public TileLaser() {
		temp = false;
	}

	public void updateState() {
		temp = !temp;
		EnumFacing facing = getFacing();
		if(temp) {
			this.addLaser(facing);
		}
		else {
			this.removeLaser(facing, this.pos.offset(facing), this.getLaserEnd(facing));
		}
	}

}
