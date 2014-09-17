package digitalseraphim.hc.tileEntity;

import java.util.List;

import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.vec.Cuboid6;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TEHorseWalker extends TileEntity {
	public static final String NAME = "HorseWalker";
	private static final String ROT_TAG = "rotation";
	private static final String ROT_RATE_TAG = "rotation_rate";

	private double rot = 0;
	private double rotRate = 0.3;
	private EntityLeashKnot knots[] = new EntityLeashKnot[6];
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(!getWorldObj().isRemote){
			rot += rotRate;
			getWorldObj().markBlockForUpdate(xCoord, yCoord, zCoord);
			
			for(int i = 0; i < 6; i++){
				if(knots[i] != null){
		    		double rad = 4.5;
		    		double angleD = (rot + (60.* (double)i)) ;
		    		double angle = -Math.toRadians(angleD);
		    		
		    		double dx = rad * Math.cos(angle);
		    		double dz = rad * Math.sin(angle);
	
		    		double px = xCoord+.5+dx;
		    		double pz = zCoord+.5+dz;
		    		
		    		dx = px - knots[i].posX;
		    		dz = pz - knots[i].posZ;
		    		
		    		System.out.println("px = " + px + " pz = " + pz);
		    		
		    		knots[i].setLocationAndAngles(px, knots[i].posY, pz, knots[i].rotationYaw, knots[i].rotationPitch); 
				}
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		_readFromNBT(tagCompound);
	}
	
	public void _readFromNBT(NBTTagCompound tagCompound) {
		rot = tagCompound.getDouble(ROT_TAG);
		rotRate = tagCompound.getDouble(ROT_RATE_TAG);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		_writeToNBT(tagCompound);
	}
	
	public void _writeToNBT(NBTTagCompound tagCompound) {
		tagCompound.setDouble(ROT_TAG, rot);
		tagCompound.setDouble(ROT_RATE_TAG, rotRate);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox((double)this.xCoord - 4, (double)this.yCoord - 1, (double)this.zCoord - 4, (double)this.xCoord + 4, (double)this.yCoord + 2, (double)this.zCoord + 4);
	}
	
    public void addTraceableCuboids(List<IndexedCuboid6> cuboids)
    {
    	for(int i = 0; i < 6; i++){
    		double rad = 4.5;
    		double angleD = (rot + (60.* (double)i)) ;
    		double angle = -Math.toRadians(angleD);
    		
    		double dx = rad * Math.cos(angle);
    		double dz = rad * Math.sin(angle);
    		
    		cuboids.add(new IndexedCuboid6(i, new Cuboid6(xCoord+.5+dx-.1, yCoord+3, zCoord+.5+dz-.1, xCoord+.5+dx+.1, yCoord+3.2, zCoord+.5+dz+.1)));
    	}
    }

    
    @Override
    public Packet getDescriptionPacket() {
    	NBTTagCompound data = new NBTTagCompound();
		_writeToNBT(data);
		return new S35PacketUpdateTileEntity( xCoord, yCoord, zCoord, 64, data );
    }
    
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    	if(pkt.func_148853_f() == 64){
			_readFromNBT(pkt.func_148857_g());
		}
    }
    
	public void setKnot(int idx, EntityLeashKnot knot){
		knots[idx] = knot;
	}

	public double getRot() {
		return rot;
	}
}
