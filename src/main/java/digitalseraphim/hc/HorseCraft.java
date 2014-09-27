package digitalseraphim.hc;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import digitalseraphim.hc.block.ModBlocks;
import digitalseraphim.hc.core.proxy.CommonProxy;
import digitalseraphim.hc.core.util.Strings;
import digitalseraphim.hc.creativetab.CreativeTabHC;
import digitalseraphim.hc.entity.EntityHCHorse;
import digitalseraphim.hc.worldgen.village.ComponentStable;
import digitalseraphim.hc.worldgen.village.VillageStableHandler;

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
		 VillagerRegistry.instance().registerVillageCreationHandler(new VillageStableHandler());
		 MapGenStructureIO.func_143031_a(ComponentStable.class, "HCraft:StableStructure");
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		ModBlocks.init();
		proxy.initTileEntities();
		proxy.initRenderingAndTextures();
		proxy.initEntities();
		proxy.initItems();
		proxy.replaceCreatureSpawn(EntityHorse.class, EntityHCHorse.class);
	}

	@EventHandler
	public void modsLoaded(FMLPostInitializationEvent event) {
	}	
}
