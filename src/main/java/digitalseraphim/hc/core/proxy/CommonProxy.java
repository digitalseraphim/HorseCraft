package digitalseraphim.hc.core.proxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import digitalseraphim.hc.client.gui.GUI_IDS;
import digitalseraphim.hc.client.gui.GuiStethoscope;
import digitalseraphim.hc.core.helper.LogHelper;
import digitalseraphim.hc.inventory.ContainerStethoscopeInventory;
import digitalseraphim.hc.item.ItemStethoscope;


public class CommonProxy implements IGuiHandler {
	public ItemStethoscope stethoscope;
//	public ItemHorseHarness horseHarness;
//	public ItemWheelbarrow wheelBarrow;
//	
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch (ID) {

		case GUI_IDS.STETHOSCOPE_ID:
			Entity e = world.getEntityByID(x);

			if (e instanceof EntityLivingBase) {
				return new ContainerStethoscopeInventory(player, (EntityLivingBase) e);
			}
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {

		LogHelper.info("get client gui element " + ID);
		LogHelper.info("stethoscope id = " + GUI_IDS.STETHOSCOPE_ID);
		
		switch (ID) {

		case GUI_IDS.STETHOSCOPE_ID:
			Entity e = world.getEntityByID(x);

			if (e instanceof EntityLivingBase) {
				return new GuiStethoscope(player, (EntityLivingBase) e);
			}
		}

		
		return null;
	}
//
//	public void initTileEntities() {
//		GameRegistry
//				.registerTileEntity(TEHorseWalker.class, TEHorseWalker.NAME);
//		GameRegistry.registerTileEntity(TESpokePath.class, TESpokePath.NAME);
//	}
//
//	public void initEntities() {
//		EntityRegistry.registerModEntity(EntityHorseHarness.class,
//				"hc.horseHarness", 0, HorseCraft.instance, 60, 1, true);
//		EntityRegistry.registerModEntity(EntityWheelbarrow.class,
//				"hc.wheelbarrow", 1, HorseCraft.instance, 60, 1, true);
//		EntityRegistry.registerGlobalEntityID(EntityHCHorse.class,
//				"hc.hcHorse", EntityRegistry.findGlobalUniqueEntityId(),
//				0x009999ff, 0x00ffff99);
//	}
//
	public void initItems() {
//		GameRegistry.registerItem(horseHarness = new ItemHorseHarness(),
//				"hc.item.horseHarness");
		GameRegistry.registerItem(stethoscope = new ItemStethoscope(), "hc.item.stethoscope");
//		GameRegistry.registerItem(wheelBarrow = new ItemWheelbarrow(), "hc.item.wheelbarrow");
	}
//
//	public void initRenderingAndTextures() {
//
//	}
//
//	public void initEventHandlers() {
//		if (ModBlocks.spokePath != null) {
//			MinecraftForge.EVENT_BUS.register(ModBlocks.spokePath);
//		}
//	}
//
//	public void printMessageToPlayer(String msg) {
//		System.out.println("tried to send message to client, but on server \""
//				+ msg + "\"");
//	}
//
//	public boolean isClient() {
//		return FMLCommonHandler.instance().getEffectiveSide().isClient();
//	}
//
//	public void replaceCreatureSpawn(Class<?> toReplace, Class<?> replaceWith) {
//		String name = (String) EntityList.classToStringMapping.get(toReplace);
//		Integer id = (Integer) EntityList.classToIDMapping.get(toReplace);
//
//		if (name == null || id == null) {
//			System.out.println("Could not find " + toReplace.getCanonicalName()
//					+ " to replace with " + replaceWith.getCanonicalName());
//			return;
//		}
//
//		EntityList.stringToClassMapping.put(name, replaceWith);
//		EntityList.classToStringMapping.put(replaceWith, name);
//		EntityList.classToStringMapping.remove(toReplace);
//		EntityList.IDtoClassMapping.put(id, replaceWith);
//		EntityList.classToIDMapping.put(replaceWith, id);
//		EntityList.classToIDMapping.remove(toReplace);
//
//		for (BiomeGenBase bgb : BiomeGenBase.getBiomeGenArray()) {
//			if (bgb != null) {
//				List l = bgb.getSpawnableList(EnumCreatureType.creature);
//				if (l != null) {
//					for (Object o : l) {
//						BiomeGenBase.SpawnListEntry sle = (BiomeGenBase.SpawnListEntry) o;
//
//						if (sle.entityClass.equals(toReplace)) {
//							sle.entityClass = replaceWith;
//						}
//
//					}
//				}
//			}
//
//		}
//
//	}

}
