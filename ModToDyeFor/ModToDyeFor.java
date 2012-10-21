package ModToDyeFor;

import net.minecraft.src.Block;
import net.minecraft.src.BlockStairs;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntitySign;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="ModToDyeFor", name="Mod To Dye For", version="1.3.2.001")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class ModToDyeFor {
	//BLOCKS
	public final static Block dyedPlanks 	 	= new BlockDyedPlanks(500, 0, Material.wood);
	public final static Block dyedCobble 	 	= new BlockDyedCobblestone(501, 1, Material.rock);
	public final static Block dyedStone  	 	= new BlockDyedStone(502, 2, Material.rock);
	public final static Block dyedGlass  	 	= new BlockDyedGlass(503,4,Material.glass);
	public final static Block dyedWoodFence  	= new BlockDyedFence(504,0,Material.wood);
	public final static Block dyedCobbleFence	= new BlockDyedFence(505,1, Material.rock);
	public final static Block dyedStoneFence	= new BlockDyedFence(506,2, Material.rock);
	public static final Block stairDyedPlanks   = (new BlockDyeableStairs(507, TileEntityBlockDyeableStairs.class, dyedPlanks)).setBlockName("stairsWood");

	
    // The instance of your mod that Forge uses.
	@Instance("ModToDyeFor")
	public static ModToDyeFor instance;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="ModToDyeFor.client.ClientProxy", serverSide="ModToDyeFor.CommonProxy")
	public static CommonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// Stub Method
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		//Register Blocks
		
		MinecraftForge.setBlockHarvestLevel(dyedPlanks, "axe", 0);
		GameRegistry.registerBlock(dyedPlanks, ItemDyedPlanks.class);
		MinecraftForge.setBlockHarvestLevel(dyedWoodFence, "axe", 0);
		GameRegistry.registerBlock(dyedWoodFence, ItemDyedFence.class);
		MinecraftForge.setBlockHarvestLevel(dyedCobble, "pickaxe", 0);
		GameRegistry.registerBlock(dyedCobble, ItemDyedCobblestone.class);
		MinecraftForge.setBlockHarvestLevel(dyedStone, "pickaxe", 0);
		GameRegistry.registerBlock(dyedStone, ItemDyedStone.class);
		MinecraftForge.setBlockHarvestLevel(dyedGlass, "pickaxe", 0);
		GameRegistry.registerBlock(dyedGlass, ItemDyedGlass.class);
		MinecraftForge.setBlockHarvestLevel(dyedCobbleFence, "axe", 0);
		GameRegistry.registerBlock(dyedCobbleFence, ItemDyedFence.class);
		MinecraftForge.setBlockHarvestLevel(dyedStoneFence, "axe", 0);
		GameRegistry.registerBlock(dyedStoneFence, ItemDyedFence.class);
		MinecraftForge.setBlockHarvestLevel(stairDyedPlanks, "axe", 0);
		GameRegistry.registerBlock(stairDyedPlanks, ItemDyedStairs.class);
		GameRegistry.registerTileEntity(TileEntityBlockDyeableStairs.class, "Dyed Plank Stairs");

		
		
		//Recipes
		for(int i = 0; i < 16; i++) {
			Object isPlank = Block.planks;
			Object isCobble	= Block.cobblestone;
			Object isStone	= Block.stone;
			Object isGlass	= Block.glass;
			ItemStack isDyedPlanks = new ItemStack(dyedPlanks, 6, i);
			ItemStack isDyedCobble = new ItemStack(dyedCobble, 6, i);
			ItemStack isDyedStone  = new ItemStack(dyedStone, 6, i);
			ItemStack isDyedWFence = new ItemStack(dyedWoodFence, 4, i);
			ItemStack isDyedCFence = new ItemStack(dyedCobbleFence, 4, i);
			ItemStack isDyedSFence  = new ItemStack(dyedStoneFence, 4, i);
			ItemStack isDyedPlanksM = new ItemStack(dyedPlanks, 1, i);
			ItemStack isDyedCobbleM = new ItemStack(dyedCobble, 1, i);
			ItemStack isDyedStoneM  = new ItemStack(dyedStone, 1, i);
			ItemStack isDyedGlass  = new ItemStack(dyedGlass, 6, i);
			ItemStack isDye	= new ItemStack(Item.dyePowder, 1, i);
			ItemStack isDyedPlankStairs	= new ItemStack(stairDyedPlanks, 4, i);
			
			GameRegistry.addRecipe(isDyedCobble, "xyx", "xyx", "xyx", 'x', isCobble, 'y', isDye);
			GameRegistry.addRecipe(isDyedCobble, "xxx", "yyy", "xxx", 'x', isCobble, 'y', isDye);
			GameRegistry.addRecipe(isDyedStone, "xyx", "xyx", "xyx", 'x', isStone, 'y', isDye);
			GameRegistry.addRecipe(isDyedStone, "xxx", "yyy", "xxx", 'x', isStone, 'y', isDye);
			GameRegistry.addRecipe(isDyedPlanks, "xyx", "xyx", "xyx", 'x', isPlank, 'y', isDye);
			GameRegistry.addRecipe(isDyedPlanks, "xxx", "yyy", "xxx", 'x', isPlank, 'y', isDye);
			GameRegistry.addRecipe(isDyedGlass, "xyx", "xyx", "xyx", 'x', isGlass, 'y', isDye);
			GameRegistry.addRecipe(isDyedGlass, "xxx", "yyy", "xxx", 'x', isGlass, 'y', isDye);
			GameRegistry.addRecipe(isDyedWFence,"xxx","xxx","   ",'x',isDyedPlanksM);
			GameRegistry.addRecipe(isDyedPlankStairs,"  x"," xx","xxx",'x',isDyedPlanksM);
			GameRegistry.addRecipe(isDyedCFence,"xxx","xxx","   ",'x',isDyedCobbleM);
			GameRegistry.addRecipe(isDyedSFence,"xxx","xxx","   ",'x',isDyedStoneM);
				
			String dyedPlanksName 		= (new StringBuilder(DyeableBlock.dyeColorNames[DyeableBlock.getDyeFromBlock(i)]).append(" Planks").toString());
			String dyedCobbleName 		= (new StringBuilder(DyeableBlock.dyeColorNames[DyeableBlock.getDyeFromBlock(i)]).append(" Cobblestone").toString());
			String dyedStoneName  		= (new StringBuilder(DyeableBlock.dyeColorNames[DyeableBlock.getDyeFromBlock(i)]).append(" Stone").toString());
			String dyedGlassName 		= (new StringBuilder(DyeableBlock.dyeColorNames[DyeableBlock.getDyeFromBlock(i)]).append(" Glass").toString());
			String dyedFenceName  		= (new StringBuilder(DyeableBlock.dyeColorNames[DyeableBlock.getDyeFromBlock(i)]).append(" Fence").toString());
			String dyedPlankStairsName	= (new StringBuilder(DyeableBlock.dyeColorNames[DyeableBlock.getDyeFromBlock(i)]).append(" Plank Stairs").toString());
			
			LanguageRegistry.addName(isDyedPlanks, dyedPlanksName);
			LanguageRegistry.addName(isDyedCobble, dyedCobbleName);
			LanguageRegistry.addName(isDyedStone, dyedStoneName);
			LanguageRegistry.addName(isDyedGlass, dyedGlassName);
			LanguageRegistry.addName(isDyedWFence, dyedFenceName);
			LanguageRegistry.addName(isDyedPlankStairs,dyedPlankStairsName);

		}
		
		//Finish
		proxy.registerRenderers();
		
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}