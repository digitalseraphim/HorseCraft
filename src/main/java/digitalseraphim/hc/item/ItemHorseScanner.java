package digitalseraphim.hc.item;

import digitalseraphim.hc.HorseCraft;
import digitalseraphim.hc.client.gui.GUI_IDS;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class ItemHorseScanner extends ItemHCBase {

	public ItemHorseScanner() {
		super(8,"horseScanner");
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target,
			EnumHand hand) {

		if (playerIn.worldObj.isRemote) {
			playerIn.openGui(HorseCraft.instance, GUI_IDS.HORSESCANNER_ID,
					playerIn.worldObj, target.getEntityId(), 0, 0);
		}
		return true;
	}
}
