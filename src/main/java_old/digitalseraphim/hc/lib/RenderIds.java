package digitalseraphim.hc.lib;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class RenderIds {

	public static int horseWalkerBaseRenderId;
	public static int horseWalkerRenderId;
	public static int hayPileRenderId;
	public static int thaumSprinklerRenderId;
	public static int hitchingPostRenderId;
	
	
	public static void init(){
		horseWalkerBaseRenderId = RenderingRegistry.getNextAvailableRenderId();
		horseWalkerRenderId = RenderingRegistry.getNextAvailableRenderId();
		hayPileRenderId = RenderingRegistry.getNextAvailableRenderId();
		thaumSprinklerRenderId = RenderingRegistry.getNextAvailableRenderId();
		hitchingPostRenderId = RenderingRegistry.getNextAvailableRenderId();
	}
}
