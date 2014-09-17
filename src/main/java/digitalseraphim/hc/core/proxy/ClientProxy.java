package digitalseraphim.hc.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import digitalseraphim.hc.client.model.ModelHCHorse;
import digitalseraphim.hc.client.render.HayPileRenderer;
import digitalseraphim.hc.client.render.entity.EntityHorseHarnessRenderer;
import digitalseraphim.hc.client.render.entity.EntityWheelbarrowRenderer;
import digitalseraphim.hc.client.render.entity.RenderHCHorse;
import digitalseraphim.hc.client.render.te.TEHorseWalkerRenderer;
import digitalseraphim.hc.entity.EntityHCHorse;
import digitalseraphim.hc.entity.EntityHorseHarness;
import digitalseraphim.hc.entity.EntityWheelbarrow;
import digitalseraphim.hc.lib.RenderIds;
import digitalseraphim.hc.tileEntity.TEHorseWalker;

public class ClientProxy extends CommonProxy {
	@Override
	public void initRenderingAndTextures() {
		RenderIds.init();
		//MinecraftForgeClient.registerItemRenderer(, new HorseWalkerRenderer());
		RenderingRegistry.registerBlockHandler(new HayPileRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityHorseHarness.class, new EntityHorseHarnessRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityHCHorse.class, new RenderHCHorse(new ModelHCHorse(), 0.75F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWheelbarrow.class, new EntityWheelbarrowRenderer());
	}
	
	@Override
	public void initTileEntities() {
		super.initTileEntities();
		ClientRegistry.bindTileEntitySpecialRenderer(TEHorseWalker.class,
				new TEHorseWalkerRenderer());
	}
	
	@Override
    public void printMessageToPlayer(String msg) {
		System.out.println("sending message to player: \""+msg+"\"");
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(msg));
    }

	@Override
    public boolean isClient(){
    	return true;
    }

}
