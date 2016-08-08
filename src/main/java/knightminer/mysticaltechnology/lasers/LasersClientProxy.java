package knightminer.mysticaltechnology.lasers;

import javax.annotation.Nonnull;

import knightminer.mysticaltechnology.core.ClientProxy;
import knightminer.mysticaltechnology.library.element.EnumElement;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;

public class LasersClientProxy extends ClientProxy {

	private static Minecraft minecraft = Minecraft.getMinecraft();

	@Override
	protected void registerModels() {
		ModelLoader.setCustomStateMapper(MystTechLasers.laser, (new StateMap.Builder()).ignore(EnumElement.PROPERTY).build());
		ModelLoader.setCustomStateMapper(MystTechLasers.laserBeam, (new StateMap.Builder()).ignore(EnumElement.PROPERTY).build());
		ModelLoader.setCustomStateMapper(MystTechLasers.lense, (new StateMap.Builder()).ignore(EnumElement.PROPERTY).build());

		registerItemModel(MystTechLasers.laser);
		registerItemModel(MystTechLasers.lense);
	}

	@Override
	public void init() {
		final BlockColors blockColors = minecraft.getBlockColors();

		// stained glass
		blockColors.registerBlockColorHandler(
				new IBlockColor() {
					@Override
					public int colorMultiplier(@Nonnull IBlockState state, IBlockAccess access, BlockPos pos, int tintIndex) {
						EnumElement type = state.getValue(EnumElement.PROPERTY);
						return type.getColor(tintIndex);
					}
				},
				MystTechLasers.laser, MystTechLasers.lense);
		blockColors.registerBlockColorHandler(
				new IBlockColor() {
					@Override
					public int colorMultiplier(@Nonnull IBlockState state, IBlockAccess access, BlockPos pos, int tintIndex) {
						EnumElement type = state.getValue(EnumElement.PROPERTY);
						return type.getColor();
					}
				},
				MystTechLasers.laserBeam);

		minecraft.getItemColors().registerItemColorHandler(
				new IItemColor() {
					@SuppressWarnings("deprecation")
					@Override
					public int getColorFromItemstack(@Nonnull ItemStack stack, int tintIndex) {
						IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
						return blockColors.colorMultiplier(iblockstate, null, null, tintIndex);
					}
				},
				MystTechLasers.laser, MystTechLasers.lense);

		super.init();
	}

}
