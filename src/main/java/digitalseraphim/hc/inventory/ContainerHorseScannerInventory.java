package digitalseraphim.hc.inventory;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ContainerHorseScannerInventory extends Container {

	EntityPlayer player;
	EntityLivingBase entity;
	ItemStack horseScannerIS;
	
	public ContainerHorseScannerInventory(EntityPlayer player,
			EntityLivingBase entity, ItemStack horseScannerIS) {
		super();
		this.player = player;
		this.entity = entity;
		this.horseScannerIS = horseScannerIS;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		if(this.entity.getDistanceSqToEntity(player) > 64 || !entity.isEntityAlive()){
			return false;
		}
		if(entity instanceof EntityHorse){
			EntityHorse horse = (EntityHorse) entity;
			if(horse.isChested()){
				IInventory inv = horse.horseChest;
			}
		}
		return true;
	}

	
	
}
