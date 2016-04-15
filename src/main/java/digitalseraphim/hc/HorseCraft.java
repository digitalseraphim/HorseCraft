package digitalseraphim.hc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import digitalseraphim.hc.block.ModBlocks;
import digitalseraphim.hc.core.proxy.CommonProxy;
import digitalseraphim.hc.core.util.Strings;
import digitalseraphim.hc.creativetab.CreativeTabHC;

@Mod(modid=Strings.MOD_ID, name=Strings.MOD_NAME, version=Strings.VERSION)
//@NetworkMod(channels={Strings.MOD_NAME}, clientSideRequired=true, serverSideRequired=false /*, packetHandler=PacketHandler.class*/)
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
//		proxy.replaceCreatureSpawn(EntityHorse.class, EntityHCHorse.class);
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		proxy.initItems();
		ModelResourceLocation res = new ModelResourceLocation("hc:horseScanner","inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(proxy.horseScanner, 0, res);
	}

	@EventHandler
	public void modsLoaded(FMLPostInitializationEvent event) {
	}	
}
