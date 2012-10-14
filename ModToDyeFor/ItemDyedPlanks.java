package ModToDyeFor;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.CreativeTabs;

public class ItemDyedPlanks extends ItemDyed {
  public ItemDyedPlanks(int i) {
    super(i);
    setMaxStackSize(64);
    setCreativeTab(CreativeTabs.tabMisc);
    setIconIndex(0);
    setItemName("Dyed Planks");
  }
  
  @SideOnly(Side.CLIENT)
  public int getIconFromDamage(int i) {
    return 0;
  }
 
}
