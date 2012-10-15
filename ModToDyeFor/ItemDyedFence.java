package ModToDyeFor;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class ItemDyedFence extends ItemDyed {

	public ItemDyedFence(int i) {
		super(i);
		setMaxStackSize(64);
		setItemName("Dyed Fence");
	}

	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int i) {
		return 0;
	}
}
