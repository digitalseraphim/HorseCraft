package digitalseraphim.hc.block;

import cpw.mods.fml.common.registry.GameRegistry;
import digitalseraphim.hc.item.ItemHayPile;

public class ModBlocks {
	public static BlockHorseWalker horseWalker;
	public static BlockFakeAir fakeAir;
	public static BlockSpokePath spokePath;
	public static BlockHayPile hayPile;
	
	public static void init() {
//		horseWalker = new BlockHorseWalker();
//		fakeAir = new BlockFakeAir();
//		spokePath = new BlockSpokePath();
		horseWalker = null;
		fakeAir = null;
		spokePath = null;
		hayPile = new BlockHayPile();
		
//		GameRegistry.registerBlock(horseWalker, BlockHorseWalker.NAME);
//		GameRegistry.registerBlock(fakeAir, BlockFakeAir.NAME);
//		GameRegistry.registerBlock(spokePath, BlockSpokePath.NAME);
		GameRegistry.registerBlock(hayPile, ItemHayPile.class, BlockHayPile.NAME);
	}
	
}
