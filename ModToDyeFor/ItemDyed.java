package ModToDyeFor;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Block;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ItemDyed extends ItemBlock {
	
	public ItemDyed(int i) {
		super(i);
	    setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}

	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return (new StringBuilder()).append(super.getItemName()).append(" ").append(DyeableBlock.dyeColorNames[DyeableBlock.getBlockFromDye(itemstack.getItemDamage())]).toString();
	}

    @SideOnly(Side.CLIENT)
	public int getColorFromDamage(int i) {
		int dye = DyeableBlock.getDyeFromBlock(i);
		return DyeableBlock.dyeColors[dye];
	}
    
    @SideOnly(Side.CLIENT)
	public String getTextureFile() {
		return CommonProxy.BLOCK_PNG;
	}

}
