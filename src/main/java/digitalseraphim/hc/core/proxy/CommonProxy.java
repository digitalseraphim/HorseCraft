package digitalseraphim.hc.core.proxy;

import java.util.List;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import digitalseraphim.hc.HorseCraft;
import digitalseraphim.hc.block.ModBlocks;
import digitalseraphim.hc.entity.EntityHCHorse;
import digitalseraphim.hc.entity.EntityHorseHarness;
import digitalseraphim.hc.entity.EntityWheelbarrow;
import digitalseraphim.hc.item.ItemHorseHarness;
import digitalseraphim.hc.item.ItemStethoscope;
import digitalseraphim.hc.item.ItemWheelbarrow;
import digitalseraphim.hc.tileEntity.TEHorseWalker;
import digitalseraphim.hc.tileEntity.TESpokePath;

public class CommonProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}

	public void initTileEntities() {
		GameRegistry
				.registerTileEntity(TEHorseWalker.class, TEHorseWalker.NAME);
		GameRegistry.registerTileEntity(TESpokePath.class, TESpokePath.NAME);
	}

	public void initEntities() {
		EntityRegistry.registerModEntity(EntityHorseHarness.class,
				"hc.horseHarness", 0, HorseCraft.instance, 60, 1, true);
		EntityRegistry.registerModEntity(EntityWheelbarrow.class,
				"hc.wheelbarrow", 1, HorseCraft.instance, 60, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityHCHorse.class,
				"hc.hcHorse", EntityRegistry.findGlobalUniqueEntityId(),
				0x009999ff, 0x00ffff99);
	}

	public void initItems() {
		GameRegistry.registerItem(new ItemHorseHarness(),
				"hc.item.horseHarness");
		GameRegistry.registerItem(new ItemStethoscope(), "hc.item.stethoscope");
		GameRegistry.registerItem(new ItemWheelbarrow(), "hc.item.wheelbarrow");
	}

	public void initRenderingAndTextures() {

	}

	public void initEventHandlers() {
		if (ModBlocks.spokePath != null) {
			MinecraftForge.EVENT_BUS.register(ModBlocks.spokePath);
		}
	}

	public void printMessageToPlayer(String msg) {
		System.out.println("tried to send message to client, but on server \""
				+ msg + "\"");
	}

	public boolean isClient() {
		return FMLCommonHandler.instance().getEffectiveSide().isClient();
	}

	public void replaceCreatureSpawn(Class<?> toReplace, Class<?> replaceWith) {
		String name = (String) EntityList.classToStringMapping.get(toReplace);
		Integer id = (Integer) EntityList.classToIDMapping.get(toReplace);

		if (name == null || id == null) {
			System.out.println("Could not find " + toReplace.getCanonicalName()
					+ " to replace with " + replaceWith.getCanonicalName());
			return;
		}

		EntityList.stringToClassMapping.put(name, replaceWith);
		EntityList.classToStringMapping.put(replaceWith, name);
		EntityList.classToStringMapping.remove(toReplace);
		EntityList.IDtoClassMapping.put(id, replaceWith);
		EntityList.classToIDMapping.put(replaceWith, id);
		EntityList.classToIDMapping.remove(toReplace);

		for (BiomeGenBase bgb : BiomeGenBase.getBiomeGenArray()) {
			if (bgb != null) {
				List l = bgb.getSpawnableList(EnumCreatureType.creature);
				if (l != null) {
					for (Object o : l) {
						BiomeGenBase.SpawnListEntry sle = (BiomeGenBase.SpawnListEntry) o;

						if (sle.entityClass.equals(toReplace)) {
							sle.entityClass = replaceWith;
						}

					}
				}
			}

		}

	}

}
