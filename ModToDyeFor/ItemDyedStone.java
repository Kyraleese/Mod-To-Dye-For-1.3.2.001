package ModToDyeFor;

import net.minecraft.src.CreativeTabs;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class ItemDyedStone extends ItemDyed {

	public ItemDyedStone(int i) {
		super(i);
		setMaxStackSize(64);
		setIconIndex(2);
		setItemName("Dyed Stone");
	}

	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int i) {
		return 2;
	}

}
