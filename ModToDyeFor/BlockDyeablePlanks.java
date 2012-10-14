package ModToDyeFor;

import java.util.Random;
import net.minecraft.src.Material;
import net.minecraft.src.Block;

public class BlockDyeablePlanks extends DyeableBlock {
	public BlockDyeablePlanks(int i, int j, Material material) {
		super(i, j, material);
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		return 0;
	}

	public int idDropped(int i, Random random) {
		return Block.planks.blockID;
	}
}
