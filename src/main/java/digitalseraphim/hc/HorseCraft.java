package digitalseraphim.hc;

import digitalseraphim.hc.block.ModBlocks;
import digitalseraphim.hc.core.proxy.CommonProxy;
import digitalseraphim.hc.core.util.Strings;
import digitalseraphim.hc.creativetab.CreativeTabHC;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid=Strings.MOD_ID, name=Strings.MOD_NAME, version=Strings.VERSION)
public class HorseCraft {
	@Instance(Strings.MOD_ID)
	public static HorseCraft instance;
	
	@SidedProxy(clientSide = "digitalseraphim.hc.core.proxy.ClientProxy", serverSide="digitalseraphim.hc.core.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs tabsHC = new CreativeTabHC(CreativeTabs.getNextID(), Strings.MOD_ID);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		ModBlocks.init();
//		proxy.initTileEntities();
//		proxy.initEntities();
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		proxy.initItems();
	}

	@EventHandler
	public void modsLoaded(FMLPostInitializationEvent event) {
	}	
}
