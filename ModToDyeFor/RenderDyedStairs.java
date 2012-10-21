package ModToDyeFor;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.EntityRenderer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderDyedStairs implements ISimpleBlockRenderingHandler {
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		Tessellator var4 = Tessellator.instance;
	    int var6;
	    float var7;
	    float var8;
	    float var9;
        int var14;
        float y = 1.0F;

	    if (renderer.useInventoryTint) {
	        var6 = block.getRenderColor(metadata);
	        var7 = (float)(var6 >> 16 & 255) / 255.0F;
	        var8 = (float)(var6 >> 8 & 255) / 255.0F;
	        var9 = (float)(var6 & 255) / 255.0F;
	        GL11.glColor4f(var7 * y, var8 * y, var9 * y, 1.0F);
	    }
	    
        for (var14 = 0; var14 < 2; ++var14) {
            if (var14 == 0) {
                block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
            }

            if (var14 == 1) {
                block.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F);
            }

            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            var4.startDrawingQuads();
            var4.setNormal(0.0F, -1.0F, 0.0F);
            renderer.renderBottomFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(0));
            var4.draw();
            var4.startDrawingQuads();
            var4.setNormal(0.0F, 1.0F, 0.0F);
            renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(1));
            var4.draw();
            var4.startDrawingQuads();
            var4.setNormal(0.0F, 0.0F, -1.0F);
            renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(2));
            var4.draw();
            var4.startDrawingQuads();
            var4.setNormal(0.0F, 0.0F, 1.0F);
            renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(3));
            var4.draw();
            var4.startDrawingQuads();
            var4.setNormal(-1.0F, 0.0F, 0.0F);
            renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(4));
            var4.draw();
            var4.startDrawingQuads();
            var4.setNormal(1.0F, 0.0F, 0.0F);
            renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(5));
            var4.draw();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }
    }

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return 50;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
       	TileEntityBlockDyeableStairs ourEntity = (TileEntityBlockDyeableStairs)world.getBlockTileEntity(x, y, z);
		int var5 = ourEntity.stairOrientation;
	    int var6 = var5 & 3;
	    float var7 = 0.0F;
	    float var8 = 0.5F;
	    float var9 = 0.5F;
	    float var10 = 1.0F;

	    if ((var5 & 4) != 0) {
	        var7 = 0.5F;
	        var8 = 1.0F;
	        var9 = 0.0F;
	        var10 = 0.5F;
	    }

	    block.setBlockBounds(0.0F, var7, 0.0F, 1.0F, var8, 1.0F);
	    renderer.renderStandardBlock(block, x, y, z);

	    if (var6 == 0) {
	        block.setBlockBounds(0.5F, var9, 0.0F, 1.0F, var10, 1.0F);
	        renderer.renderStandardBlock(block, x, y, z);
	    } else if (var6 == 1) {
	        block.setBlockBounds(0.0F, var9, 0.0F, 0.5F, var10, 1.0F);
	        renderer.renderStandardBlock(block, x, y, z);
	    } else if (var6 == 2) {
	        block.setBlockBounds(0.0F, var9, 0.5F, 1.0F, var10, 1.0F);
	        renderer.renderStandardBlock(block, x, y, z);
	    } else if (var6 == 3) {
	        block.setBlockBounds(0.0F, var9, 0.0F, 1.0F, var10, 0.5F);
	        renderer.renderStandardBlock(block, x, y, z);
	    }

	    block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	    return true;
	}
}
