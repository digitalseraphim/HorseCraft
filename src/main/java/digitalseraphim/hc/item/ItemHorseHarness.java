package digitalseraphim.hc.item;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import digitalseraphim.hc.HorseCraft;
import digitalseraphim.hc.block.ModBlocks;
import digitalseraphim.hc.entity.EntityHorseHarness;

public class ItemHorseHarness extends Item {
	public ItemHorseHarness() {
		super();
		setCreativeTab(HorseCraft.tabsHC);
		setTextureName("hc:horse_harness");
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack itemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		System.out.println("ItemHorseHarness - onItemUse");

		if (itemStack.stackSize == 0) {
			return false;
		} else {

			// } else if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6,
			// par7,
			// itemStack)) {
			// return false;
			Block block = par3World.getBlock(par4, par5, par6);

			if (block == ModBlocks.spokePath) {
				int j1 = par3World.getBlockMetadata(par4, par5, par6);
				int k1 = j1 & 7;

				System.out.println("old meta = " + k1);

				if (k1 <= 6
						&& par3World.checkNoEntityCollision(block.getCollisionBoundingBoxFromPool(par3World, par4,
								par5, par6))
						&& par3World.setBlockMetadataWithNotify(par4, par5, par6, k1 + 1 | j1 & -8, 2)) {
					par3World.playSoundEffect((double) ((float) par4 + 0.5F), (double) ((float) par5 + 0.5F),
							(double) ((float) par6 + 0.5F), block.stepSound.func_150496_b(),
							(block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
					--itemStack.stackSize;
					return true;
				}
			}

			return super.onItemUse(itemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
		}
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack harnesses, EntityPlayer player, EntityLivingBase other) {
		if (harnesses != null && harnesses.stackSize != 0 && other instanceof EntityHorse) {
			EntityHorse horse = (EntityHorse) other;

			if (horse.isRiding()) {
				if (horse.ridingEntity instanceof EntityHorseHarness) {
					player.worldObj.removeEntity(horse.ridingEntity);
					horse.setLeashedToEntity(null, true);
					return true;
				}
			} else {
				EntityHorseHarness harness = new EntityHorseHarness(player.worldObj);
				player.worldObj.spawnEntityInWorld(harness);
				horse.mountEntity(harness);
				harness.copyLocationAndAnglesFrom(horse);
				if (!player.worldObj.isRemote) {
					horse.setLeashedToEntity(player, true);
				}
				return true;
			}
		}
		return false;
	}

}
