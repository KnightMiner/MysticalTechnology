package knightminer.mysticaltechnology.common;

import knightminer.mysticaltechnology.core.ClientProxy;

public class CommonsClientProxy extends ClientProxy {

	@Override
	protected void registerModels() {
		MystTechCommons.materials.registerItemModels();

		registerItemBlockMeta(MystTechCommons.blockOre);
		registerItemBlockMeta(MystTechCommons.blockStorage);
	}
}
