package knightminer.mysticaltechnology.common;

import knightminer.mysticaltechnology.common.client.RenderElemental;
import knightminer.mysticaltechnology.common.client.RenderElementalFireball;
import knightminer.mysticaltechnology.common.entity.EntityElementalFireball;
import knightminer.mysticaltechnology.common.entity.EntityVenom;
import knightminer.mysticaltechnology.core.ClientProxy;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class CommonsClientProxy extends ClientProxy {

	@Override
	public void preInit() {
		RenderingRegistry.registerEntityRenderingHandler(EntityVenom.class, RenderElemental.FACTORY_Venom);
		RenderingRegistry.registerEntityRenderingHandler(EntityElementalFireball.class, RenderElementalFireball.FACTORY_Fireball);
	}

	@Override
	protected void registerModels() {
		MystTechCommons.materials.registerItemModels();

		registerItemBlockMeta(MystTechCommons.blockOre);
		registerItemBlockMeta(MystTechCommons.blockStorage);
	}
}
