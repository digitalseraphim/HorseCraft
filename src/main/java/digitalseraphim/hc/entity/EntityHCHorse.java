package digitalseraphim.hc.entity;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityHCHorse extends EntityHorse {

	private int endurance;
	private int tiredness;

	public EntityHCHorse(World p_i1685_1_) {
		super(p_i1685_1_);
	}

	public EntityHCHorse(World p_i1685_1_, EntityHorse copyMe) {
		this(p_i1685_1_);
		copyDataFrom(copyMe, true);
	}

	
	
}
