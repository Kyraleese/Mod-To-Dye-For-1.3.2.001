package ModToDyeFor;

import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class BlockDyedGlass extends BlockDyeableBreakable {

	public BlockDyedGlass(int id, int texture, Material material) {
		super(id, texture, material, false);
		setHardness(0.3F);
		setStepSound(Block.soundGlassFootstep);
		setBlockName("Dyed Glass");
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
    public int quantityDropped(Random par1Random) {
        return 0;
    }
    
	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		return 4;
	}
	
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
    }
      
    public boolean isOpaqueCube() {
        return false;
    }
    
    public boolean renderAsNormalBlock() {
        return false;
    }

    protected boolean canSilkHarvest() {
        return true;
    }
}
