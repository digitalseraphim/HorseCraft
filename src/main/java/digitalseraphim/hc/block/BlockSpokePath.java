package digitalseraphim.hc.block;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemLead;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.raytracer.RayTracer;
import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Vector3;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import digitalseraphim.hc.tileEntity.TEHorseWalker;
import digitalseraphim.hc.tileEntity.TESpokePath;

public class BlockSpokePath extends BlockContainer {
	public static final String NAME = "spoke_path";
	private RayTracer rayTracer = new RayTracer();

	public BlockSpokePath() {
		super(Material.air);
		setBlockBounds(0, 0, 0, 0, 0, 0);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TESpokePath();
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isAir(IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean isNormalCube(IBlockAccess world, int x, int y, int z) {
		return false;
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
	}

	@Override
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 start, Vec3 end) {
		TESpokePath tile = (TESpokePath) world.getTileEntity(x, y, z);
		TEHorseWalker parent;
		List<IndexedCuboid6> cuboids = new LinkedList<IndexedCuboid6>();

		if (tile == null) {
			return null;
		}

		parent = tile.getParent();
		if (parent == null) {
			System.out.println("parent is null");
			return null;
		}
		parent.addTraceableCuboids(cuboids);
		// return rayTracer.rayTraceCuboids(new Vector3(start), new
		// Vector3(end), cuboids, new BlockCoord(parent.xCoord, parent.yCoord,
		// parent.zCoord), this);
		return rayTracer.rayTraceCuboids(new Vector3(start), new Vector3(end), cuboids, new BlockCoord(x, y, z), this);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onBlockHighlight(DrawBlockHighlightEvent event) {
		if (event.target.typeOfHit == MovingObjectType.BLOCK
				&& event.player.worldObj.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ) == ModBlocks.spokePath)
			RayTracer.retraceBlock(event.player.worldObj, event.player, event.target.blockX, event.target.blockY,
					event.target.blockZ);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7,
			float par8, float par9) {
		// TODO Auto-generated method stub
		TESpokePath tile = (TESpokePath) world.getTileEntity(x, y, z);
		MovingObjectPosition mop = RayTracer.retraceBlock(world, player, x, y, z);
		EntityLeashKnot entityleashknot = EntityLeashKnot.getKnotForBlock(world, x, y, z);
		

		if (mop != null && entityleashknot == null) {
			System.out.println("player clicked #" + mop.subHit + " with: " + player.getCurrentEquippedItem());
			if (player.getCurrentEquippedItem() == null || player.getCurrentEquippedItem().getItem() instanceof ItemLead) {
				double d0 = 7.0D;
				List<?> list = world.getEntitiesWithinAABB(EntityLiving.class,
						AxisAlignedBB.getBoundingBox((double) x - d0, (double) y - d0, (double) z - d0,
								(double) x + d0, (double) y + d0, (double) z + d0));
	
				if (list != null) {
					Iterator<?> iterator = list.iterator();
	
					while (iterator.hasNext()) {
						EntityLiving entityliving = (EntityLiving) iterator.next();
	
						if (entityliving.getLeashed() && entityliving.getLeashedToEntity() == player) {
							entityleashknot = EntityLeashKnot.func_110129_a(world, x, y, z);
							entityliving.setLeashedToEntity(entityleashknot, true);
							tile.getParent().setKnot(mop.subHit, entityleashknot);
						}
					}
				}
			}
		}else{

		}

		return super.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9);
	}

}
