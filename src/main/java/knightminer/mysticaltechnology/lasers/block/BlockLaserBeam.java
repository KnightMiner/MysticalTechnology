package knightminer.mysticaltechnology.lasers.block;

import knightminer.mysticaltechnology.library.EnumElement;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import slimeknights.mantle.block.EnumBlock;

public class BlockLaserBeam extends EnumBlock<EnumElement> {

	public BlockLaserBeam() {
		super(Material.VINE, EnumElement.PROPERTY, EnumElement.class);

		setHardness(-1f);
		setResistance(100f);
		setSoundType(SoundType.METAL);
	}

}
