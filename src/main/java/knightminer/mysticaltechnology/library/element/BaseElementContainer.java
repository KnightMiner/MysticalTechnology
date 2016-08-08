package knightminer.mysticaltechnology.library.element;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class BaseElementContainer implements IElementInput, IElementOutput, INBTSerializable<NBTTagCompound> {

	private EnumElement type;
	
	/** Amount of energy stored */
	int stored;
	/** Maximum amount to store */
	int capacity;
	/** Max input in a tick */
	int inputRate;
	/** Max output in a tick */
	int outputRate;
	
	public BaseElementContainer() {
		this(EnumElement.LIGHT);
	}
	
	public BaseElementContainer(EnumElement type) {
		this.type = type;
	}

	@Override
	public int outputPower(EnumElement type, int power, boolean simulated) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inputPower(EnumElement type, int power, boolean simulated) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public NBTTagCompound serializeNBT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		// TODO Auto-generated method stub
		
	}

}
