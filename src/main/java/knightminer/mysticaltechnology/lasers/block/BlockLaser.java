package knightminer.mysticaltechnology.lasers.block;

import knightminer.mysticaltechnology.library.EnumElement;
import knightminer.mysticaltechnology.library.MystTechRegistry;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import slimeknights.mantle.block.EnumBlock;

public class BlockLaser extends EnumBlock<EnumElement> {

	public BlockLaser() {
		super(Material.IRON, EnumElement.PROPERTY, EnumElement.class);

		setHardness(3.5f);
		setSoundType(SoundType.METAL);
		setCreativeTab(MystTechRegistry.tabCommons);
		setHarvestLevel("pickaxe", 1);
	}
}
