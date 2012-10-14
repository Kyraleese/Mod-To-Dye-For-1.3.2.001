package ModToDyeFor;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class BlockDyedStone extends DyeableBlock {

	public BlockDyedStone(int id, int texture, Material material) {
		super(id, texture, material);
		setHardness(1.0F);
		setStepSound(Block.soundStoneFootstep);
		setBlockName("Dyed Stone");
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		return 2;
	}

	public int idDropped(int i, Random random) {
		return Block.stone.blockID;
	}
}
