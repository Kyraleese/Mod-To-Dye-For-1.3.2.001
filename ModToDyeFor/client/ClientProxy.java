package ModToDyeFor.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;
import ModToDyeFor.CommonProxy;
import ModToDyeFor.RenderDyedStairs;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
		MinecraftForgeClient.preloadTexture(ITEMS_PNG);
		MinecraftForgeClient.preloadTexture(BLOCK_PNG);
		RenderingRegistry.registerBlockHandler(new RenderDyedStairs());
	}
	
}
