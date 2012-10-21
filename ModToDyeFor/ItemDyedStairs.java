package ModToDyeFor;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class ItemDyedStairs extends ItemDyed {

	public ItemDyedStairs(int i) {
		super(i);
		setMaxStackSize(64);
		setIconIndex(2);
		setItemName("Dyed Stairs");
	}

	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int i) {
		return 0;
	}

}
