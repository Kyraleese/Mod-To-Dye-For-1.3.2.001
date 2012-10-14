package ModToDyeFor;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
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
	public final static Block dyedPlanks = new BlockDyedPlanks(500, 0, Material.wood);
	public final static Block dyedCobble = new BlockDyedCobblestone(501, 1, Material.rock);
	public final static Block dyedStone  = new BlockDyedStone(502, 2, Material.rock);
	public final static Block dyedGlass  = new BlockDyedGlass(503,4,Material.glass);
	
	
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
		MinecraftForge.setBlockHarvestLevel(dyedCobble, "pickaxe", 0);
		GameRegistry.registerBlock(dyedCobble, ItemDyedCobblestone.class);
		MinecraftForge.setBlockHarvestLevel(dyedStone, "pickaxe", 0);
		GameRegistry.registerBlock(dyedStone, ItemDyedStone.class);
		MinecraftForge.setBlockHarvestLevel(dyedGlass, "pickaxe", 0);
		GameRegistry.registerBlock(dyedGlass, ItemDyedGlass.class);
		
		//Recipes
		for(int i = 0; i < 16; i++) {
			Object isPlanks = Block.planks;
			Object isCobble	= Block.cobblestone;
			Object isStone	= Block.stone;
			Object isGlass	= Block.glass;
			ItemStack isDyedPlanks = new ItemStack(dyedPlanks, 6, i);
			ItemStack isDyedCobble = new ItemStack(dyedCobble, 6, i);
			ItemStack isDyedStone  = new ItemStack(dyedStone, 6, i);
			ItemStack isDyedGlass  = new ItemStack(dyedGlass, 6, i);
			ItemStack isDye	= new ItemStack(Item.dyePowder, 1, i);
			
			GameRegistry.addRecipe(isDyedCobble, "xyx", "xyx", "xyx", 'x', isCobble, 'y', isDye);
			GameRegistry.addRecipe(isDyedCobble, "xxx", "yyy", "xxx", 'x', isCobble, 'y', isDye);
			GameRegistry.addRecipe(isDyedStone, "xyx", "xyx", "xyx", 'x', isStone, 'y', isDye);
			GameRegistry.addRecipe(isDyedStone, "xxx", "yyy", "xxx", 'x', isStone, 'y', isDye);
			GameRegistry.addRecipe(isDyedPlanks, "xyx", "xyx", "xyx", 'x', isPlanks, 'y', isDye);
			GameRegistry.addRecipe(isDyedPlanks, "xxx", "yyy", "xxx", 'x', isPlanks, 'y', isDye);
			GameRegistry.addRecipe(isDyedGlass, "xyx", "xyx", "xyx", 'x', isGlass, 'y', isDye);
			GameRegistry.addRecipe(isDyedGlass, "xxx", "yyy", "xxx", 'x', isGlass, 'y', isDye);
				
			String dyedPlanksName = (new StringBuilder(DyeableBlock.dyeColorNames[DyeableBlock.getDyeFromBlock(i)]).append(" Planks").toString());
			String dyedCobbleName = (new StringBuilder(DyeableBlock.dyeColorNames[DyeableBlock.getDyeFromBlock(i)]).append(" Cobblestone").toString());
			String dyedStoneName  = (new StringBuilder(DyeableBlock.dyeColorNames[DyeableBlock.getDyeFromBlock(i)]).append(" Stone").toString());
			String dyedGlassName  = (new StringBuilder(DyeableBlock.dyeColorNames[DyeableBlock.getDyeFromBlock(i)]).append(" Glass").toString());

			LanguageRegistry.addName(isDyedPlanks, dyedPlanksName);
			LanguageRegistry.addName(isDyedCobble, dyedCobbleName);
			LanguageRegistry.addName(isDyedStone, dyedStoneName);
			LanguageRegistry.addName(isDyedGlass, dyedGlassName);
		}
		
		//Finish
		proxy.registerRenderers();
		
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}