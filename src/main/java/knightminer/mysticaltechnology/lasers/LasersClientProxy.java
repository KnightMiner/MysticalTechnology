package knightminer.mysticaltechnology.lasers;

import knightminer.mysticaltechnology.core.ClientProxy;

public class LasersClientProxy extends ClientProxy {

	@Override
	protected void registerModels() {
		// TODO: models
		registerItemBlockMeta(MystTechLasers.laser);
		registerItemBlockMeta(MystTechLasers.laserBeam);
		registerItemBlockMeta(MystTechLasers.lense);
	}

}
