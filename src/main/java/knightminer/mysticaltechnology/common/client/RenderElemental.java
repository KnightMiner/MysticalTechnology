package knightminer.mysticaltechnology.common.client;

import javax.annotation.Nonnull;

import knightminer.mysticaltechnology.library.Util;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBlaze;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderElemental extends RenderBlaze {

	public static final ResourceLocation venomTexture = Util.getResource("textures/entity/elemental/venom.png");
	public static final Factory FACTORY_Venom = new Factory(venomTexture);

	private final ResourceLocation texture;

	public RenderElemental(RenderManager renderManager) {
		this(renderManager, venomTexture);
	}

	public RenderElemental(RenderManager renderManager, ResourceLocation texture) {
		super(renderManager);
		this.texture = texture;
	}

	@Nonnull
	@Override
	protected ResourceLocation getEntityTexture(EntityBlaze entity) {
		return this.texture;
	}

	private static class Factory implements IRenderFactory<EntityBlaze> {

		private final ResourceLocation texture;

		public Factory(ResourceLocation texture) {
			this.texture = texture;
		}

		@Override
		public Render<? super EntityBlaze> createRenderFor(RenderManager manager) {
			return new RenderElemental(manager, texture);
		}
	}
}
