package digitalseraphim.hc.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import digitalseraphim.hc.HorseCraft;
import digitalseraphim.hc.entity.EntityWheelbarrow;

public class ItemWheelbarrow extends Item {

	public ItemWheelbarrow() {
		super();
		setCreativeTab(HorseCraft.tabsHC);
		setTextureName("hc:wheelbarrow");
	}

	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y,
			int z, int side, float dx, float dy, float dz) {

		if (itemStack.stackSize == 0) {
			return false;
		} else {
			EntityWheelbarrow wheelbarrow = new EntityWheelbarrow(world);
			Block block = world.getBlock(x,y,z);
			MovingObjectPosition mop = player.rayTrace(20, 0);
			
			
			float angle = player.rotationYaw;//(float)/*Math.toDegrees*/(Math.atan2(dz, dx));
			HorseCraft.proxy.printMessageToPlayer("anglea = " + angle);
			world.spawnEntityInWorld(wheelbarrow);
			//wheelbarrow.setPositionAndRotation2((double)x+.5, (double)y + 1.5, (double)z + .5, -angle+90, 0, 0);
			wheelbarrow.setPositionAndRotation2(mop.hitVec.xCoord, mop.hitVec.yCoord+.5, mop.hitVec.zCoord, -angle+90, 0, 0);
			itemStack.stackSize --;
			return true;
		}
	}

}
