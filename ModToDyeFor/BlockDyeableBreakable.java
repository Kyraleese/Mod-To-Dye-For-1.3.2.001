package ModToDyeFor;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;

public class BlockDyeableBreakable extends DyeableBlock {
    private boolean localFlag;
    
	public BlockDyeableBreakable(int id, int texture, Material material, boolean localFlag) {
		super(id, texture, material);
		this.localFlag = localFlag;
	}

    public boolean isOpaqueCube() {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        int var6 = par1IBlockAccess.getBlockId(par2, par3, par4);
        return !this.localFlag && var6 == this.blockID ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }
}
