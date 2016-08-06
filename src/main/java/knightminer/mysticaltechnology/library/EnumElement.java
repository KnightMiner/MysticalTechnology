package knightminer.mysticaltechnology.library;

import java.util.Locale;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;
import slimeknights.mantle.block.EnumBlock;

public enum EnumElement implements IStringSerializable, EnumBlock.IEnumMeta {
	TERRA(0x23f48b, 0x969696),
	BLAZE(0xffa000, 0xdede00),
	AER(0xccd7a8, 0xb193ad),
	FROST(0x00e7ec, 0xbedec4),
	LIGHT(0xf2f2f2, 0xe6e6e6),
	VOID(0x343434, 0x474747);

	public static final PropertyEnum<EnumElement> PROPERTY = PropertyEnum.create("type", EnumElement.class);

	private final int meta;
	private final int color;
	private final int metalColor;
	EnumElement(int color, int metalColor) {
		meta = ordinal();
		this.color = color;
		this.metalColor = metalColor;
	}

	@Override
	public int getMeta() {
		return meta;
	}

	@Override
	public String getName() {
		return this.toString().toLowerCase(Locale.US);
	}

	/**
	 * Gets the color multiplier for usage in BlockColors
	 * @param index index of 1 determines the metal color, 0 is element color
	 * @return
	 */
	public int getColor(int index) {
		if(index == 1) {
			return metalColor;
		}
		return color;
	}

	/**
	 * Gets the color multiplier for usage in BlockColors
	 * @return the element color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Converts this Enum to EnumElementCore
	 * @return The value as an EnumElementCore, or null if inapplicable
	 */
	public EnumElementCore asElementCore() {
		switch(this) {
			case TERRA:
				return EnumElementCore.TERRA;
			case BLAZE:
				return EnumElementCore.BLAZE;
			case AER:
				return EnumElementCore.AER;
			case FROST:
				return EnumElementCore.FROST;
		}

		return null;
	}

	/**
	 * Obtains an element from its metadata
	 */
	public static EnumElement fromMeta(int meta) {
		if(meta < 0 || meta >= values().length) {
			meta = 0;
		}

		return values()[meta];
	}


	public enum EnumElementCore implements IStringSerializable, EnumBlock.IEnumMeta {
		TERRA,
		BLAZE,
		AER,
		FROST;

		public static final PropertyEnum<EnumElement> PROPERTY = PropertyEnum.create("type", EnumElement.class);

		private final int meta;
		EnumElementCore() {
			meta = ordinal();
		}

		@Override
		public int getMeta() {
			return meta;
		}

		@Override
		public String getName() {
			return this.toString().toLowerCase(Locale.US);
		}

		/**
		 * Converts this Enum to EnumElement
		 * @return The value as an EnumElement
		 */
		public EnumElement asElement() {
			switch(this) {
				case TERRA:
					return EnumElement.TERRA;
				case BLAZE:
					return EnumElement.BLAZE;
				case AER:
					return EnumElement.AER;
				case FROST:
					return EnumElement.FROST;
			}

			return null;
		}

		public int getColor(int index) {
			return asElement().getColor(index);
		}

		public int getColor() {
			return asElement().getColor();
		}

		/**
		 * Obtains a core element from its metadata
		 */
		public static EnumElementCore fromMeta(int meta) {
			if(meta < 0 || meta >= values().length) {
				meta = 0;
			}

			return values()[meta];
		}
	}
}
