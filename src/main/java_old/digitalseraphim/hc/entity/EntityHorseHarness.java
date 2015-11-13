package digitalseraphim.hc.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityHorseHarness extends Entity{

	public EntityHorseHarness(World world) {
		super(world);
	}

	@Override
	public void onEntityUpdate() {
		this.rotationYaw += 1;
		if(this.rotationYaw > 360f){
			this.rotationYaw -= 360f;
		}
	}
	
	@Override
	public void updateRiderPosition() {
		System.out.println("updateRiderPosition - " + this.riddenByEntity);
		if(this.riddenByEntity != null){
			this.riddenByEntity.setPositionAndRotation(posX, posY, posZ, rotationYaw, rotationPitch);

			this.riddenByEntity.prevPosX = posX;
			this.riddenByEntity.prevPosY = posY;
			this.riddenByEntity.prevPosZ = posZ;
			
			this.riddenByEntity.lastTickPosX = posX;
			this.riddenByEntity.lastTickPosY = posY;
			this.riddenByEntity.lastTickPosZ = posZ;
			
			this.riddenByEntity.motionX = 0;
			this.riddenByEntity.motionY = 0;
			this.riddenByEntity.motionZ = 0;
			
		}
	}

	@Override
	protected void entityInit() {

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {

	}

	@Override
	public double getMountedYOffset() {
		return 0;
	}

	
	
}
