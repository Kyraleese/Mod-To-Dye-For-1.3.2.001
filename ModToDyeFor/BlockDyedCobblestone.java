package ModToDyeFor;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class BlockDyedCobblestone extends DyeableBlock {

	public BlockDyedCobblestone(int id, int texture, Material material) {
		super(id, texture, material);
		setHardness(2.0F);
		setStepSound(Block.soundStoneFootstep);
		setBlockName("Dyed Cobblestone");
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		return 1;
	}

	public int idDropped(int i, Random random) {
		return Block.cobblestone.blockID;
	}

}
