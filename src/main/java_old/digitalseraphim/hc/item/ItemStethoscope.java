package digitalseraphim.hc.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import digitalseraphim.hc.HorseCraft;
import digitalseraphim.hc.client.gui.GUI_IDS;

public class ItemStethoscope extends Item {

	public ItemStethoscope() {
		this.maxStackSize = 8;
		this.setCreativeTab(HorseCraft.tabsHC);
		this.setTextureName("hc:stethoscope");
		this.setUnlocalizedName("stethoscope");
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack itemStack,
			EntityPlayer player, EntityLivingBase living) {

		System.out.println("open gui ");
//		(new Exception("")).printStackTrace();
		if (player.worldObj.isRemote) {
			player.openGui(HorseCraft.instance, GUI_IDS.STETHOSCOPE_ID,
					player.worldObj, living.getEntityId(), 0, 0);
		}
		return true;
	}
}
