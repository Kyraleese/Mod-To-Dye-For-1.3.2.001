package ModToDyeFor;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class ItemDyedSand extends ItemDyed {

	public ItemDyedSand(int i) {
		super(i);
		setMaxStackSize(64);
		setIconIndex(6);
		setItemName("Dyed Sand");
	}

	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int i) {
		return 6;
	}
}
