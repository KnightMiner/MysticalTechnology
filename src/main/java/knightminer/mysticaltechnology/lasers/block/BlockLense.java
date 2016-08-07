package knightminer.mysticaltechnology.lasers.block;

import knightminer.mysticaltechnology.lasers.tileentity.TileLense;
import knightminer.mysticaltechnology.library.EnumElement;
import knightminer.mysticaltechnology.library.MystTechRegistry;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import slimeknights.mantle.block.EnumBlock;

public class BlockLense extends EnumBlock<EnumElement> implements ITileEntityProvider {

	public BlockLense() {
		super(Material.IRON, EnumElement.PROPERTY, EnumElement.class);

		setHardness(3.5f);
		setSoundType(SoundType.GLASS);
		setCreativeTab(MystTechRegistry.tabCommons);
		setHarvestLevel("pickaxe", 1);
		this.isBlockContainer = true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileLense();
	}
}
