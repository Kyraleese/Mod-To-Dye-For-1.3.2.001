package ModToDyeFor;

import java.util.List;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.BlockFence;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockDyedFence extends BlockFence {

	public BlockDyedFence(int par1, int par2, Material par3Material) {
		super(par1, par2, par3Material);
		
	}

	//Yup, this is a direct copy of DyeableBlock.  I suppose I could just copy the render routine
	//from BlockFence, but eh...  Yay Java and its single-inheritance lack of mixins.  *sigh*
	//I considered an interface, but I'd still have to dump all this here, so it sort of defeats the purpose in my eyes.
	//I considered an abstract class, but then I'd still have the problem with the renderer 
	//As it wants a BlockFence object.
	@Override
	public String getTextureFile () {
		return CommonProxy.BLOCK_PNG;
	}
	
    /**
     * Ignores the side and metadata and figures it out based on blockIDs.
     * Not terribly elegant but it gets the job done.
     */
    public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
    	int texture = 0;
    	if (this.blockID == ModToDyeFor.dyedWoodFence.blockID){
    		texture = 0;
    	}
    	if (this.blockID == ModToDyeFor.dyedCobbleFence.blockID){
    		texture = 1;
    	}
    	if (this.blockID == ModToDyeFor.dyedStoneFence.blockID) {
    		texture = 2;
    	}
    	return texture;
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
    
    /**
     * Returns true if the specified block can be connected by a fence
     */
    public boolean canConnectFenceTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    	int blockId	 = par1IBlockAccess.getBlockId(par2,par3,par4);
        boolean var5 = isIdAFence(blockId);

        if (!var5 && blockId != Block.fenceGate.blockID) {
            Block var6 = Block.blocksList[blockId];
            return var6 != null && var6.blockMaterial.isOpaque() && var6.renderAsNormalBlock() ? var6.blockMaterial != Material.pumpkin : false;
        }
        else {
            return true;
        }
    }
    
    public static boolean isIdAFence(int par0)  {
        return par0 == Block.fence.blockID || par0 == Block.netherFence.blockID || par0 == ModToDyeFor.dyedWoodFence.blockID || par0 == ModToDyeFor.dyedCobbleFence.blockID || par0 == ModToDyeFor.dyedStoneFence.blockID;
    }
}
