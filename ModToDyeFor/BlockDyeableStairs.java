package ModToDyeFor;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import java.util.List;
import java.util.Random;

import javax.swing.text.html.parser.Entity;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityChest;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;
import net.minecraft.src.WorldInfo;

public class BlockDyeableStairs extends BlockContainer {
    private static final int[][] field_72159_a = new int[][] {{2, 6}, {3, 7}, {2, 3}, {6, 7}, {0, 4}, {1, 5}, {0, 1}, {4, 5}};

    /** The block that is used as model for the stair. */
    private final Block modelBlock;
    private final int field_72158_c = 0;
    private boolean field_72156_cr = false;
    private int field_72160_cs = 0;
    
    private Class stairEntityClass;

    public BlockDyeableStairs(int par1, Class par2Class, Block par2Block) {
        super(par1, par2Block.blockIndexInTexture, par2Block.blockMaterial);
        this.modelBlock = par2Block;
        this.stairEntityClass = par2Class;
        if (par2Block.blockID == ModToDyeFor.dyedPlanks.blockID) {
    		setHardness(0.5F);
    		setResistance(5.0F);
        } else {
        	setHardness(2.0F);
        	setResistance(10.0F);
        }
        this.setStepSound(par2Block.stepSound);
        this.setLightOpacity(255);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
    
    public TileEntity createNewTileEntity(World par1World) {
        try {
            return new TileEntityBlockDyeableStairs();
        }
        catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }
    
    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        if (this.field_72156_cr) {
            this.setBlockBounds(0.5F * (float)(this.field_72160_cs % 2), 0.5F * (float)(this.field_72160_cs / 2 % 2), 0.5F * (float)(this.field_72160_cs / 4 % 2), 0.5F + 0.5F * (float)(this.field_72160_cs % 2), 0.5F + 0.5F * (float)(this.field_72160_cs / 2 % 2), 0.5F + 0.5F * (float)(this.field_72160_cs / 4 % 2));
        } else {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType() {
        return 50;
    }

    /**
     * if the specified block is in the given AABB, add its collision bounding box to the given list
     */
    public void addCollidingBlockToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, net.minecraft.src.Entity par7Entity) {
      	TileEntityBlockDyeableStairs ourEntity = (TileEntityBlockDyeableStairs)par1World.getBlockTileEntity(par2, par3, par4);       	
        int var8 = ourEntity.stairOrientation;
        int var9 = var8 & 3;
        float var10 = 0.0F;
        float var11 = 0.5F;
        float var12 = 0.5F;
        float var13 = 1.0F;

        if ((var8 & 4) != 0) {
            var10 = 0.5F;
            var11 = 1.0F;
            var12 = 0.0F;
            var13 = 0.5F;
        }

        this.setBlockBounds(0.0F, var10, 0.0F, 1.0F, var11, 1.0F);
        super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);

        if (var9 == 0) {
            this.setBlockBounds(0.5F, var12, 0.0F, 1.0F, var13, 1.0F);
            super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        } else if (var9 == 1) {
            this.setBlockBounds(0.0F, var12, 0.0F, 0.5F, var13, 1.0F);
            super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        } else if (var9 == 2) {
            this.setBlockBounds(0.0F, var12, 0.5F, 1.0F, var13, 1.0F);
            super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        } else if (var9 == 3) {
            this.setBlockBounds(0.0F, var12, 0.0F, 1.0F, var13, 0.5F);
            super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        }

        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @SideOnly(Side.CLIENT)

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        this.modelBlock.randomDisplayTick(par1World, par2, par3, par4, par5Random);
    }

    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
        this.modelBlock.onBlockClicked(par1World, par2, par3, par4, par5EntityPlayer);
    }

    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
    public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5) {
        this.modelBlock.onBlockDestroyedByPlayer(par1World, par2, par3, par4, par5);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Goes straight to getLightBrightnessForSkyBlocks for Blocks, does some fancy computing for Fluids
     */
    public int getMixedBrightnessForBlock(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        return this.modelBlock.getMixedBrightnessForBlock(par1IBlockAccess, par2, par3, par4);
    }

    @SideOnly(Side.CLIENT)

    /**
     * How bright to render this block based on the light its receiving. Args: iBlockAccess, x, y, z
     */
    public float getBlockBrightness(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        return this.modelBlock.getBlockBrightness(par1IBlockAccess, par2, par3, par4);
    }

    /**
     * Returns how much this block can resist explosions from the passed in entity.
     */
    public float getExplosionResistance(net.minecraft.src.Entity par1Entity) {
        return this.modelBlock.getExplosionResistance(par1Entity);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
        return this.modelBlock.getBlockTextureFromSideAndMetadata(par1, par2);
    }

    /**
     * Returns the block texture based on the side being looked at.  Args: side
     */
    public int getBlockTextureFromSide(int par1) {
        return this.modelBlock.getBlockTextureFromSideAndMetadata(par1, this.field_72158_c);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass() {
        return this.modelBlock.getRenderBlockPass();
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate() {
        return this.modelBlock.tickRate();
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return this.modelBlock.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    /**
     * Can add to the passed in vector for a movement vector to be applied to the entity. Args: x, y, z, entity, vec3d
     */
    public void velocityToAddToEntity(World par1World, int par2, int par3, int par4, net.minecraft.src.Entity par5Entity, Vec3 par6Vec3) {
        this.modelBlock.velocityToAddToEntity(par1World, par2, par3, par4, par5Entity, par6Vec3);
    }

    /**
     * Returns if this block is collidable (only used by Fire). Args: x, y, z
     */
    public boolean isCollidable() {
        return this.modelBlock.isCollidable();
    }

    /**
     * Returns whether this block is collideable based on the arguments passed in Args: blockMetaData, unknownFlag
     */
    public boolean canCollideCheck(int par1, boolean par2) {
        return this.modelBlock.canCollideCheck(par1, par2);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
        return this.modelBlock.canPlaceBlockAt(par1World, par2, par3, par4);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        this.onNeighborBlockChange(par1World, par2, par3, par4, 0);
        this.modelBlock.onBlockAdded(par1World, par2, par3, par4);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
        this.modelBlock.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    /**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
    public void onEntityWalking(World par1World, int par2, int par3, int par4, net.minecraft.src.Entity par5Entity) {
        this.modelBlock.onEntityWalking(par1World, par2, par3, par4, par5Entity);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        this.modelBlock.updateTick(par1World, par2, par3, par4, par5Random);
    }
    
	@Override
	public String getTextureFile () {
		return CommonProxy.BLOCK_PNG;
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
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        return this.modelBlock.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, 0, 0.0F, 0.0F, 0.0F);
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4) {
        this.modelBlock.onBlockDestroyedByExplosion(par1World, par2, par3, par4);
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving) {
        int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
       	TileEntityBlockDyeableStairs ourEntity = (TileEntityBlockDyeableStairs)par1World.getBlockTileEntity(par2, par3, par4);       	
        //int var7 = par1World.getBlockMetadata(par2, par3, par4);
       	
       	if (var6 == 0) {
        	ourEntity.stairOrientation = 2;
            par1World.setBlockTileEntity(par2, par3, par4, ourEntity);
            //par1World.setBlockMetadata(par2, par3, par4, 4);
        }

        if (var6 == 1) {
        	ourEntity.stairOrientation = 1;
            par1World.setBlockTileEntity(par2, par3, par4, ourEntity);
            //par1World.setBlockMetadata(par2, par3, par4, 4);
        }

        if (var6 == 2) {
        	ourEntity.stairOrientation = 3;
            par1World.setBlockTileEntity(par2, par3, par4, ourEntity);
            //par1World.setBlockMetadata(par2, par3, par4, 4);
        }

        if (var6 == 3) {
        	ourEntity.stairOrientation = 0;
            par1World.setBlockTileEntity(par2, par3, par4, ourEntity);
            //par1World.setBlockMetadata(par2, par3, par4, 4);
        }
        
    }

    /**
     * called before onBlockPlacedBy by ItemBlock and ItemReed
     */
    public void updateBlockMetadata(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8) {
        if (par5 == 0 || par5 != 1 && (double)par7 > 0.5D) {
            int var9 = par1World.getBlockMetadata(par2, par3, par4);
            par1World.setBlockMetadataWithNotify(par2, par3, par4, var9);
        }
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3) {
        MovingObjectPosition[] var7 = new MovingObjectPosition[8];
      	TileEntityBlockDyeableStairs ourEntity = (TileEntityBlockDyeableStairs)par1World.getBlockTileEntity(par2, par3, par4);       	
        int var8 = ourEntity.stairOrientation;
        int var9 = var8 & 3;
        boolean var10 = (var8 & 4) == 4;
        int[] var11 = field_72159_a[var9 + (var10 ? 4 : 0)];
        this.field_72156_cr = true;
        int var14;
        int var15;
        int var16;

        for (int var12 = 0; var12 < 8; ++var12) {
            this.field_72160_cs = var12;
            int[] var13 = var11;
            var14 = var11.length;

            for (var15 = 0; var15 < var14; ++var15) {
                var16 = var13[var15];

                if (var16 == var12) {
                    ;
                }
            }

            var7[var12] = super.collisionRayTrace(par1World, par2, par3, par4, par5Vec3, par6Vec3);
        }

        int[] var21 = var11;
        int var24 = var11.length;

        for (var14 = 0; var14 < var24; ++var14) {
            var15 = var21[var14];
            var7[var15] = null;
        }

        MovingObjectPosition var23 = null;
        double var22 = 0.0D;
        MovingObjectPosition[] var25 = var7;
        var16 = var7.length;

        for (int var17 = 0; var17 < var16; ++var17) {
            MovingObjectPosition var18 = var25[var17];

            if (var18 != null) {
                double var19 = var18.hitVec.squareDistanceTo(par6Vec3);

                if (var19 > var22) {
                    var23 = var18;
                    var22 = var19;
                }
            }
        }

        return var23;
    }
}
