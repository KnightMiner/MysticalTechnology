package knightminer.mysticaltechnology.lasers.tileentity;

public class TileLense extends TileLaserBeamProvider {

	public void setLaser(boolean laser) {
		if(laser) {
			addLaser(getFacing());
		}
		else {
			removeLaser(getFacing(), this.pos, this.getLaserEnd(getFacing()));
		}
	}

}
