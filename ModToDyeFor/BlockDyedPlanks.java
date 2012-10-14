package ModToDyeFor;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class BlockDyedPlanks extends DyeableBlock {

	public BlockDyedPlanks(int id, int texture, Material material) {
		super(id, texture, material);
		setHardness(0.5F);
		setStepSound(Block.soundWoodFootstep);
		setBlockName("Dyed Planks");
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		return 0;
	}

	public int idDropped(int i, Random random) {
		return Block.planks.blockID;
	}
}
