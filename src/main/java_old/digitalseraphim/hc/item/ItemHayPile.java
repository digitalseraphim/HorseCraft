package digitalseraphim.hc.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import digitalseraphim.hc.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHayPile extends ItemBlockWithMetadata {
	public ItemHayPile(Block block) {
		super(block, block);
		System.out.println("ItemHayPile constructor! - 1");
		setTextureName("hay_block_top");
	}

	public ItemHayPile() {
		this(ModBlocks.hayPile);
		System.out.println("ItemHayPile constructor! - 2");
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		System.out.println("ItemHayPile - onItemUse");

		if (par1ItemStack.stackSize == 0) {
			return false;
		} else if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7,
				par1ItemStack)) {
			return false;
		} else {
			Block block = par3World.getBlock(par4, par5, par6);

			if (block == ModBlocks.hayPile) {
				int j1 = par3World.getBlockMetadata(par4, par5, par6);
				int k1 = j1 & 7;

				System.out.println("old meta = " + k1);
				
				if (k1 <= 6
						&& par3World.checkNoEntityCollision(block
								.getCollisionBoundingBoxFromPool(par3World,
										par4, par5, par6))
						&& par3World.setBlockMetadataWithNotify(par4, par5,
								par6, k1 + 1 | j1 & -8, 2)) {
					par3World.playSoundEffect((double) ((float) par4 + 0.5F),
							(double) ((float) par5 + 0.5F),
							(double) ((float) par6 + 0.5F),
							block.stepSound.func_150496_b(),
							(block.stepSound.getVolume() + 1.0F) / 2.0F,
							block.stepSound.getPitch() * 0.8F);
					--par1ItemStack.stackSize;
					return true;
				}
			}

			return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World,
					par4, par5, par6, par7, par8, par9, par10);
		}
	}

}
