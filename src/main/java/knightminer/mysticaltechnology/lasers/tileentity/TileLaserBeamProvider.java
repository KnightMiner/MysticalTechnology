package knightminer.mysticaltechnology.lasers.tileentity;

import javax.annotation.Nullable;

import knightminer.mysticaltechnology.MysticalTechnology;
import knightminer.mysticaltechnology.lasers.MystTechLasers;
import knightminer.mysticaltechnology.library.element.EnumElement;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public abstract class TileLaserBeamProvider extends TileFacing {

	public static final String TAG_TARGET_X = "targetX";
	public static final String TAG_TARGET_Y = "targetY";
	public static final String TAG_TARGET_Z = "targetZ";

	public static int maxDistance = 16;
	public EnumElement type;
	private BlockPos targetPos;

	public void notifyChange(IBlockState blockState, BlockPos pos) {
		removeLaser(getFacing(), pos, this.getLaserEnd(getFacing()));
	}

	/**
	 * Marks an area with the laser blocks
	 * @param direction
	 * @param to
	 */
	public void addLaser(EnumFacing direction) {
		setTarget(direction, null);
		BlockPos pos = this.pos;
		for(int i = 0; i < maxDistance; i++) {
			pos = pos.offset(direction);

			// if its air, place a laser there with the relevant data
			if(worldObj.isAirBlock(pos)) {
				worldObj.setBlockState(pos, MystTechLasers.laserBeam.getDefaultState().withProperty(EnumElement.PROPERTY, getType(direction)));
				TileEntity te = worldObj.getTileEntity(pos);
				if(te instanceof TileLaserBeam) {
					TileLaserBeam beam = (TileLaserBeam)te;
					beam.setMasterPos(this.pos);
					beam.setFacing(direction);
				}
			}

			// otherwise store the ending block position and stop the loop
			else {
				setTarget(direction, pos);
				break;
			}
		}

		if(this.getTarget(direction) != null) {
			TileEntity te = worldObj.getTileEntity(getTarget(direction));
			if(te instanceof TileLense) {
				((TileLense)te).setLaser(true);
			}
		}
	}

	public void removeLaser(EnumFacing direction, BlockPos startPos, BlockPos endPos) {
		TileEntity te = worldObj.getTileEntity(endPos);
		if(te instanceof TileLense) {
			MysticalTechnology.log.info("Updating lense");
			((TileLense)te).setLaser(false);
		}

		BlockPos pos = startPos;
		for(int i = 0; i < maxDistance; i++) {

			// we double check that its air since a likely cause for removing a laser is a block placed in the way
			if(worldObj.getBlockState(pos).getBlock() == MystTechLasers.laserBeam) {
				MysticalTechnology.log.info("Not air");
				worldObj.setBlockToAir(pos);
			}
			pos = pos.offset(direction);
			if(pos.equals(endPos)) {
				MysticalTechnology.log.info("Equal position");
				break;
			}
		}

		setTarget(direction, null);
	}

	/**
	 * Determines if the laser has a target block on the specified side
	 * @return True if one exists, false otherwise
	 */
	public boolean hasTarget(EnumFacing side) {
		return targetPos != null;
	}

	/**
	 * Gets the target position for the specified side
	 */
	public BlockPos getTarget(EnumFacing side) {
		return targetPos;
	}

	/**
	 * Gets the final block of the current laser, or at least where it should be
	 */
	public BlockPos getLaserEnd(EnumFacing direction) {
		if(hasTarget(direction)) {
			return getTarget(direction);
		}

		return this.pos.offset(direction, maxDistance);
	}

	public void setTarget(EnumFacing direction, @Nullable BlockPos pos) {
		targetPos = pos;
	}

	/**
	 * Gets the element output type of the local side
	 */
	public EnumElement getType(EnumFacing side) {
		return worldObj.getBlockState(pos).getValue(EnumElement.PROPERTY);
	};


	@Override
	public void readFromNBT(NBTTagCompound tags) {
		super.readFromNBT(tags);

		// the facing is read directly when requested

		if(tags.hasKey(TAG_TARGET_X)) {
			int x = tags.getInteger(TAG_TARGET_X);
			int y = tags.getInteger(TAG_TARGET_Y);
			int z = tags.getInteger(TAG_TARGET_Z);
			this.targetPos = new BlockPos(x, y, z);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tags) {
		tags = super.writeToNBT(tags);

		// the facing is stored directly when set

		if(targetPos != null) {
			tags.setInteger(TAG_TARGET_X, targetPos.getX());
			tags.setInteger(TAG_TARGET_Y, targetPos.getY());
			tags.setInteger(TAG_TARGET_Z, targetPos.getZ());
		}

		return tags;
	}

}
