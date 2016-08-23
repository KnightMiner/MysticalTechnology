package knightminer.mysticaltechnology.common.client;

import knightminer.mysticaltechnology.common.entity.EntityElementalFireball;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderElementalFireball extends RenderFireball {
	public static final Factory FACTORY_Fireball = new Factory();

	public RenderElementalFireball(RenderManager renderManagerIn) {
		super(renderManagerIn, 0.5f);
	}

	private static class Factory implements IRenderFactory<EntityElementalFireball> {
		@Override
		public Render<? super EntityElementalFireball> createRenderFor(RenderManager manager) {
			return new RenderElementalFireball(manager);
		}
	}
}
