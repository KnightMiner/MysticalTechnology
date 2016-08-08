package knightminer.mysticaltechnology.lasers.tileentity;

import javax.annotation.Nonnull;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileFacing extends TileEntity {

	public static final String TAG_FACING = "facing";

	public void setFacing(EnumFacing facing) {
		getTileData().setInteger(TAG_FACING, facing.getIndex());
	}

	@Nonnull
	public EnumFacing getFacing() {
		return EnumFacing.getFront(getTileData().getInteger(TAG_FACING));
	}


	/* Client sync stuff */
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		// note that this sends all of the tile data. you should change this if you use additional tile data
		NBTTagCompound tag = getTileData().copy();
		writeToNBT(tag);
		return new SPacketUpdateTileEntity(this.getPos(), this.getBlockMetadata(), tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.getNbtCompound();
		getTileData().setInteger(TAG_FACING, tag.getInteger(TAG_FACING));
		readFromNBT(tag);
	}

}
