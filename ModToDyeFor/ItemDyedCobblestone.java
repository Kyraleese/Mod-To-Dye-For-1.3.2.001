package ModToDyeFor;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.CreativeTabs;

public class ItemDyedCobblestone extends ItemDyed {

	public ItemDyedCobblestone(int i) {
		super(i);
		setMaxStackSize(64);
		setIconIndex(1);
		setItemName("Dyed Cobblestone");
	}

	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int i) {
		return 1;
	}
}
