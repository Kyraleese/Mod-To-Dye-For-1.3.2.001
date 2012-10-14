package ModToDyeFor;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.CreativeTabs;

public class ItemDyedGlass extends ItemDyed {

	public ItemDyedGlass(int i) {
		super(i);
		setMaxStackSize(64);
		setIconIndex(4);
		setItemName("Dyed Glass");
	}

	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int i) {
		return 4;
	}

}
