package knightminer.mysticaltechnology.library;

import knightminer.mysticaltechnology.library.element.BaseElementContainer;
import knightminer.mysticaltechnology.library.element.IElementInput;
import knightminer.mysticaltechnology.library.element.IElementOutput;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class MystTechCapabilities {

	@CapabilityInject(IElementInput.class)
	public static Capability<IElementInput> CAPABILITY_INPUT = null;

	@CapabilityInject(IElementOutput.class)
	public static Capability<IElementOutput> CAPABILITY_OUTPUT = null;

	public static void register() {
		CapabilityManager.INSTANCE.register(IElementInput.class, new CapabilityElementInput<IElementInput>(), BaseElementContainer.class);
		CapabilityManager.INSTANCE.register(IElementOutput.class, new CapabilityElementOutput<IElementOutput>(), BaseElementContainer.class);
	}

	public static class CapabilityElementInput<T extends IElementInput> implements IStorage<IElementInput> {

		@Override
		public NBTBase writeNBT(Capability<IElementInput> capability, IElementInput instance, EnumFacing side) {
			return null;
		}

		@Override
		public void readNBT(Capability<IElementInput> capability, IElementInput instance, EnumFacing side,
				NBTBase nbt) {}

	}

	public static class CapabilityElementOutput<T extends IElementOutput> implements IStorage<IElementOutput> {

		@Override
		public NBTBase writeNBT(Capability<IElementOutput> capability, IElementOutput instance, EnumFacing side) {
			return null;
		}

		@Override
		public void readNBT(Capability<IElementOutput> capability, IElementOutput instance, EnumFacing side,
				NBTBase nbt) {}

	}
}
