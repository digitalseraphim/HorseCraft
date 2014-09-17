package digitalseraphim.hc.item;

import digitalseraphim.hc.HorseCraft;
import digitalseraphim.hc.entity.EntityHCHorse;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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

		if(living instanceof EntityHCHorse){
			EntityHCHorse hcHorse = (EntityHCHorse)living;
			StringBuilder sb = new StringBuilder();
			
			HorseCraft.proxy.printMessageToPlayer("It's a "+ hcHorse.getCommandSenderName() + "!");
			HorseCraft.proxy.printMessageToPlayer("Attributes:");
			HorseCraft.proxy.printMessageToPlayer(" Age: "+hcHorse.getAge());
			HorseCraft.proxy.printMessageToPlayer(" Speed: " + hcHorse.getAIMoveSpeed());
			HorseCraft.proxy.printMessageToPlayer(" Jump Strength: " + hcHorse.getHorseJumpStrength());
			
			return true;
		}else if(living instanceof EntityHorse){
			EntityHorse hcHorse = (EntityHorse)living;
			HorseCraft.proxy.printMessageToPlayer("I think it's a " + hcHorse.getCommandSenderName() + ", but can't tell much more" );
			return true;
		}else{
			HorseCraft.proxy.printMessageToPlayer("What is that?!? ");
			return false;
		}
	}
}
