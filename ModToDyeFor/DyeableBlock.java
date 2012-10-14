package ModToDyeFor;

import java.util.List;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Block;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;

public class DyeableBlock extends Block {
	public DyeableBlock (int id, int texture, Material material) {
		super(id, texture, material);
	}
	
	@Override
	public String getTextureFile () {
		return CommonProxy.BLOCK_PNG;
	}
	
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
        if (par2 == 0) {
            return this.blockIndexInTexture;
        } else {
            par2 = ~(par2 & 15);
            return 113 + ((par2 & 8) >> 3) + (par2 & 7) * 16;
        }
    }

    protected int damageDropped(int i) {
        return i;
    }

    public static int getBlockFromDye(int i) {
        return ~i & 0xf;
    }

    public static int getDyeFromBlock(int i) {
        return ~i & 0xf;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k) {
      int metadata  = iblockaccess.getBlockMetadata(i,j,k);
      return dyeColors[getDyeFromBlock(metadata)];
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(int i) {
      int dye = getDyeFromBlock(i);
      return dyeColors[dye];
    }

    public static final String dyeColorNames[] = {
    	"White","Orange","Magenta","Light Blue","Yellow","Lime","Pink","Gray","Silver",
    	"Cyan","Purple","Blue","Brown","Green","Red","Black"
    };

    public static final int dyeColors[] = {
    	0xffffff, 0xeb8844, 0xc354cd, 0x6689d3, 0xdecf2a, 0x41cd34, 0xd88198, 0x434343, 0xd0d4d6, 0x287697,
    	0x7b2fb3, 0x253192, 0x51301a, 0x3b511a, 0xb3312c, 0x1e1b1b
    };

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int var4 = 0; var4 < 16; ++var4) {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }
   
}
