package digitalseraphim.hc.inventory;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerHorseScannerInventory extends Container {

	EntityPlayer player;
	EntityLivingBase entity;
	ItemStack horseScannerIS;

	public ContainerHorseScannerInventory(EntityPlayer player, EntityLivingBase entity, ItemStack horseScannerIS) {
		super();
		this.player = player;
		this.entity = entity;
		this.horseScannerIS = horseScannerIS;
		int j, k;
		int base = 156;

		for (j = 0; j < 3; ++j) {
			for (k = 0; k < 9; ++k) {
				this.addSlotToContainer(new Slot(player.inventory, k + j * 9 + 9, 8 + k * 18, base + j * 18));
			}
		}

		for (j = 0; j < 9; ++j) {
			this.addSlotToContainer(new Slot(player.inventory, j, 8 + j * 18, base + 58));
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		if (this.entity.getDistanceSqToEntity(player) > 64 || !entity.isEntityAlive()) {
			return false;
		}
//		if (entity instanceof EntityHorse) {
//			EntityHorse horse = (EntityHorse) entity;
//			if (horse.isChested()) {
//				IInventory inv = horse.horseChest;
//			}
//		}
		return true;
	}

}
