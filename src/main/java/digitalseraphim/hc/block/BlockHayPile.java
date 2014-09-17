package digitalseraphim.hc.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import digitalseraphim.hc.HorseCraft;
import digitalseraphim.hc.lib.RenderIds;

/* code copied and tweaked from BlockCarpet */

public class BlockHayPile extends Block {
	public static final String NAME = "hayPile";
	
	protected BlockHayPile() {
		super(new HayMaterial());
		this.setCreativeTab(HorseCraft.tabsHC);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.setBlockBoundsForSnowDepth(0);
	}

	
	@Override
	@SideOnly(Side.CLIENT)
	protected String getTextureName() {
		return "hay_block_top";
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return Blocks.hay_block.getIcon(0, 0);
	}
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4) & 7;
        float f = 0.125F;
        return AxisAlignedBB.getBoundingBox((double)par2 + this.minX, (double)par3 + this.minY, (double)par4 + this.minZ, (double)par2 + this.maxX, (double)((float)par3 + (float)l * f), (double)par4 + this.maxZ);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType() {
    	return RenderIds.hayPileRenderId;
    }
    
    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender()
    {
        this.setBlockBoundsForSnowDepth(0);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        this.setBlockBoundsForSnowDepth(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }

    /**
     * calls setBlockBounds based on the depth of the snow. Int is any values 0x0-0x7, usually this blocks metadata.
     */
    protected void setBlockBoundsForSnowDepth(int par1)
    {
        int j = par1 & 7;
        float f = (float)(2 * (1 + j)) / 16.0F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
    	Block block = par1World.getBlock(par2, par3 - 1, par4);
        if (block == null){
        	System.out.println("BlockHayPile.canPlaceBlockAt() - return false");
        	return false;
        }
        if (block == this && (par1World.getBlockMetadata(par2, par3 - 1, par4) & 7) == 7){
        	System.out.println("BlockHayPile.canPlaceBlockAt() - return true");
        	return true;
        }
        if (!block.isLeaves(par1World, par2, par3 - 1, par4) && !block.isOpaqueCube()){
        	System.out.println("BlockHayPile.canPlaceBlockAt() - return false 2");
        	return false;
        }
    	System.out.println("BlockHayPile.canPlaceBlockAt() - return blocksMovement()");
        return block.getMaterial().blocksMovement();
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        this.canHayStay(par1World, par2, par3, par4);
    }

    /**
     * Checks if this snow block can stay at this location.
     */
    private boolean canHayStay(World par1World, int par2, int par3, int par4)
    {
        if (!this.canPlaceBlockAt(par1World, par2, par3, par4))
        {
            par1World.setBlockToAir(par2, par3, par4);
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
        super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
        par1World.setBlockToAir(par3, par4, par5);
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par1World.getSavedLightValue(EnumSkyBlock.Block, par2, par3, par4) > 11)
        {
            par1World.setBlockToAir(par2, par3, par4);
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return par5 == 1 ? true : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        return (meta & 7) + 1;
    }

    /**
     * Determines if a new block can be replace the space occupied by this one,
     * Used in the player's placement code to make the block act like water, and lava.
     *
     * @param world The current world
     * @param x X Position
     * @param y Y position
     * @param z Z position
     * @return True if the block is replaceable by another block
     */
    
    @Override
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        System.out.println("BlockHayPile.isBlockReplaceable() " + meta + " " + blockMaterial.isReplaceable());
        return (meta >= 7 ? false : blockMaterial.isReplaceable());
    }

	private static class HayMaterial extends MaterialLogic{
		public HayMaterial() {
			super(MapColor.grassColor);
			setReplaceable();
		}
		
		@Override
		public boolean isOpaque() {
			return blocksMovement();
		}
		
		@Override
		public boolean isToolNotRequired() {
			return true;
		}
		
		@Override
		public int getMaterialMobility() {
			return 1;
		}
	}
}
