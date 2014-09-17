package digitalseraphim.hc.tileEntity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TESpokePath extends TileEntity{
	public static final String NAME = "SpokePath";
	
	
	private int parentX;
	private int parentY;
	private int parentZ;
	
	
	public void setParentLoc(int pX, int pY, int pZ) {
		this.parentX = pX;
		this.parentY = pY;
		this.parentZ = pZ;
	}
	
	public TEHorseWalker getParent() {
		return (TEHorseWalker)getWorldObj().getTileEntity(parentX, parentY, parentZ);
	}
	
	public void _writeToNBT(NBTTagCompound tagCompound) {	
		tagCompound.setInteger("parentX", parentX);
		tagCompound.setInteger("parentY", parentY);
		tagCompound.setInteger("parentZ", parentZ);	
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		_writeToNBT(tagCompound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		_readFromNBT(tagCompound);
	}
	
	public void _readFromNBT(NBTTagCompound tagCompound) {
		parentX = tagCompound.getInteger("parentX");
		parentY = tagCompound.getInteger("parentY");
		parentZ = tagCompound.getInteger("parentZ");	
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
	
}
