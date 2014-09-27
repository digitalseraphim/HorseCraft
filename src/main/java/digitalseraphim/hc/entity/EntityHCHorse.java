package digitalseraphim.hc.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityHCHorse extends EntityHorse {

	private static final  IAttribute endurance = (new RangedAttribute("horse.endurance", 0.7D, 0.0D, 2.0D)).setDescription("Endurance").setShouldWatch(true);
	private static final  IAttribute tiredness = (new RangedAttribute("horse.tiredness", 0.7D, 0.0D, 2.0D)).setDescription("Tiredness").setShouldWatch(true);
	private static final  IAttribute strength = (new RangedAttribute("horse.strength", 0.7D, 0.0D, 2.0D)).setDescription("Strength").setShouldWatch(true);
	
	public EntityHCHorse(World p_i1685_1_) {
		super(p_i1685_1_);
	}

	public EntityHCHorse(World p_i1685_1_, EntityHorse copyMe) {
		this(p_i1685_1_);
		copyDataFrom(copyMe, true);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		return super.createChild(p_90011_1_);
	}
	
	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_) {
		IEntityLivingData p_110161_1_1 = super.onSpawnWithEgg(p_110161_1_);
		 
		return p_110161_1_1;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(endurance);
		this.getAttributeMap().registerAttribute(tiredness);
		this.getAttributeMap().registerAttribute(strength);
	}
	
	boolean wasAirborne = false;
	boolean wasRearing = false;
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
				
//		String particleType = "snowballpoof";
		//String particleType = "flame";
		String particleType = "explode";
		//String particleType = "blockdust_"+ Block.getIdFromBlock(Blocks.water) + "_0";
		double mult = 1;
		
		
		if(this.rand.nextInt(this.riddenByEntity!=null?2:20) == 0){
			this.worldObj.spawnParticle(particleType, this.posX + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, this.boundingBox.minY + 0.1D, this.posZ + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, -this.motionX * 4.0D, .1D, -this.motionZ * 4.0D);
		}
		//this.getRearingAmount(0);
		if((this.wasRearing && !this.isRearing()) || (this.wasAirborne && !this.isAirBorne)){
			double d3 = 10;//(double)Math.min(0.2F + (float)p_72706_6_ / 15.0F, 10.0F);

            if (d3 > 2.5D)
            {
                d3 = 2.5D;
            }
           
            int l1 = (int)(150.0D * d3);
            
            for (int i2 = 0; i2 < l1; ++i2)
            {
                float f3 = MathHelper.randomFloatClamp(this.rand, 0.0F, ((float)Math.PI * 2F));
                double d5 = (double)MathHelper.randomFloatClamp(this.rand, 0.75F, 1.0F);
                double d6 = 0.20000000298023224D + d3 / 100.0D;
                double d7 = (double)(MathHelper.cos(f3) * 0.2F) * d5 * d5 * (d3 + 0.2D);
                double d8 = (double)(MathHelper.sin(f3) * 0.2F) * d5 * d5 * (d3 + 0.2D);
                this.worldObj.spawnParticle(particleType, (double)((float)this.posX), (double)((float)this.posY), (double)((float)this.posZ), d7*mult, d6*mult, d8*mult);
            }

//			for (int i = 0; i < 20; ++i)
//	        {
//	            double d0 = this.rand.nextGaussian() * 0.02D;
//	            double d1 = this.rand.nextGaussian() * 0.02D;
//	            double d2 = this.rand.nextGaussian() * 0.02D;
//	            double d3 = 10.0D;
//	            this.worldObj.spawnParticle("lava", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width - d0 * d3, this.posY + (double)(this.rand.nextFloat() * this.height) - d1 * d3, this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width - d2 * d3, d0, d1, d2);
//	        }
		}
		this.wasRearing = this.isRearing();
		
		this.wasAirborne = this.isAirBorne;
		
		
	}
	
}
