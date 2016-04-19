package digitalseraphim.hc.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class ItemSugarCubes extends ItemHCBase {
	public ItemSugarCubes() {
		super(64, "sugarCubes");
	}
	

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target,
			EnumHand hand) {
		// TODO Auto-generated method stub
		return super.itemInteractionForEntity(stack, playerIn, target, hand);
	}
}
