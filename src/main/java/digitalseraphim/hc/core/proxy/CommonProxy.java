package digitalseraphim.hc.core.proxy;

import digitalseraphim.hc.client.gui.GUI_IDS;
import digitalseraphim.hc.client.gui.GuiHorseScanner;
import digitalseraphim.hc.core.helper.LogHelper;
import digitalseraphim.hc.inventory.ContainerHorseScannerInventory;
import digitalseraphim.hc.item.ItemHorseScanner;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {
	public ItemHorseScanner horseScanner;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {

		case GUI_IDS.HORSESCANNER_ID:
			Entity e = world.getEntityByID(x);

			if (e instanceof EntityLivingBase) {
				return new ContainerHorseScannerInventory(player, (EntityLivingBase) e,
						player.getActiveItemStack());
			}
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		LogHelper.info("get client gui element " + ID);
		LogHelper.info("stethoscope id = " + GUI_IDS.HORSESCANNER_ID);

		switch (ID) {

		case GUI_IDS.HORSESCANNER_ID:
			Entity e = world.getEntityByID(x);

			if (e instanceof EntityLivingBase) {
				return new GuiHorseScanner(player, (EntityLivingBase) e);
			}
		}

		return null;
	}

	public void initItems() {
		horseScanner = new ItemHorseScanner();
	}

	public boolean isClient() {
		return FMLCommonHandler.instance().getEffectiveSide().isClient();
	}

}
