package digitalseraphim.hc.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import digitalseraphim.hc.HorseCraft;
import digitalseraphim.hc.lib.RenderIds;
import digitalseraphim.hc.tileEntity.TEHorseWalker;
import digitalseraphim.hc.tileEntity.TESpokePath;

public class BlockHorseWalker extends BlockContainer {
	public static final String NAME = "horse_walker";

	private byte allowed[][][] = //
	{ //
	{ // ground level
			{ 0, 0, 1, 1, 1, 1, 1, 0, 0 }, //
					{ 0, 1, 1, 1, 1, 1, 1, 1, 0 }, //
					{ 1, 1, 1, 0, 0, 0, 1, 1, 1 }, //
					{ 1, 1, 0, 0, 0, 0, 0, 1, 1 }, //
					{ 1, 1, 0, 0, 0, 0, 0, 1, 1 }, //
					{ 1, 1, 0, 0, 0, 0, 0, 1, 1 }, //
					{ 1, 1, 1, 0, 0, 0, 1, 1, 1 }, //
					{ 0, 1, 1, 1, 1, 1, 1, 1, 0 }, //
					{ 0, 0, 1, 1, 1, 1, 1, 0, 0 }, //
			},

			{ // base level
			{ 0, 0, 3, 3, 3, 3, 3, 0, 0 }, //
					{ 0, 3, 3, 3, 3, 3, 3, 3, 0 }, //
					{ 3, 3, 3, 0, 0, 0, 3, 3, 3 }, //
					{ 3, 3, 0, 0, 0, 0, 0, 3, 3 }, //
					{ 3, 3, 0, 0, 0, 0, 0, 3, 3 }, //
					{ 3, 3, 0, 0, 0, 0, 0, 3, 3 }, //
					{ 3, 3, 3, 0, 0, 0, 3, 3, 3 }, //
					{ 0, 3, 3, 3, 3, 3, 3, 3, 0 }, //
					{ 0, 0, 3, 3, 3, 3, 3, 0, 0 }, //
			}, { // base + 2
			{ 0, 0, 3, 3, 3, 3, 3, 0, 0 }, //
					{ 0, 3, 3, 3, 3, 3, 3, 3, 0 }, //
					{ 3, 3, 3, 0, 0, 0, 3, 3, 3 }, //
					{ 3, 3, 0, 0, 0, 0, 0, 3, 3 }, //
					{ 3, 3, 0, 0, 0, 0, 0, 3, 3 }, //
					{ 3, 3, 0, 0, 0, 0, 0, 3, 3 }, //
					{ 3, 3, 3, 0, 0, 0, 3, 3, 3 }, //
					{ 0, 3, 3, 3, 3, 3, 3, 3, 0 }, //
					{ 0, 0, 3, 3, 3, 3, 3, 0, 0 }, //
			}, { // base + 2
			{ 0, 0, 3, 3, 3, 3, 3, 0, 0 }, //
					{ 0, 3, 3, 3, 3, 3, 3, 3, 0 }, //
					{ 3, 3, 3, 3, 3, 3, 3, 3, 3 }, //
					{ 3, 3, 3, 3, 3, 3, 3, 3, 3 }, //
					{ 3, 3, 3, 3, 3, 3, 3, 3, 3 }, //
					{ 3, 3, 3, 3, 3, 3, 3, 3, 3 }, //
					{ 3, 3, 3, 3, 3, 3, 3, 3, 3 }, //
					{ 0, 3, 3, 3, 3, 3, 3, 3, 0 }, //
					{ 0, 0, 3, 3, 3, 3, 3, 0, 0 }, //
			}, { // base + 3
			{ 0, 0, 4, 4, 4, 4, 4, 0, 0 }, //
					{ 0, 4, 3, 3, 3, 3, 3, 4, 0 }, //
					{ 4, 3, 3, 3, 3, 3, 3, 3, 4 }, //
					{ 4, 3, 3, 3, 3, 3, 3, 3, 4 }, //
					{ 4, 3, 3, 3, 0, 3, 3, 3, 4 }, //
					{ 4, 3, 3, 3, 3, 3, 3, 3, 4 }, //
					{ 4, 3, 3, 3, 3, 3, 3, 3, 4 }, //
					{ 0, 4, 3, 3, 3, 3, 3, 4, 0 }, //
					{ 0, 0, 4, 4, 4, 4, 4, 0, 0 }, //
			},

	};

	public BlockHorseWalker() {
		super(Material.iron);
		this.setCreativeTab(HorseCraft.tabsHC);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		for (int dx = -4; dx <= 4; dx++) {
			for (int dy = -1; dy <= 3; dy++) {
				for (int dz = -4; dz <= 4; dz++) {
					int xx = x + dx;
					int yy = y + dy;
					int zz = z + dz;
					Block block = world.getBlock(xx, yy, zz);
					int allowedType = allowed[dy + 1][dx + 4][dz + 4];

					switch (allowedType) {
					case 0:
						continue;
					case 1:
						if (block == null
								|| !block.isBlockSolid(world, xx, yy,
										zz, 1)) {
							HorseCraft.proxy
									.printMessageToPlayer(String
											.format("You feel the track is not level at (%d %d %d)",
													xx, yy, zz));
							System.out.println("reason 1");
							return false;
						}
						break;
					case 2:
						// old unused case
						break;
					case 3:
					case 4:

						if (block == null) {
							continue;
						}

						if (!block.isReplaceable(world,
								xx, yy, zz)) {
							HorseCraft.proxy
									.printMessageToPlayer(String
											.format("There doesn't seem to be room for this here (%d %d %d)",
													xx, yy, zz));
							return false;
						}
					}
				}
			}
		}

		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TEHorseWalker();
	}

	@Override
	public int getRenderType() {
		return RenderIds.horseWalkerRenderId;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side,
			float hitX, float hitY, float hitZ, int meta) {
		for (int dx = -4; dx <= 4; dx++) {
			for (int dy = 0; dy <= 3; dy++) {
				for (int dz = -4; dz <= 4; dz++) {
					int xx = x + dx;
					int yy = y + dy;
					int zz = z + dz;

					int allowedType = allowed[dy + 1][dx + 4][dz + 4];

					if (allowedType == 3) {
						world.setBlock(xx, yy, zz, ModBlocks.fakeAir);
					} else if (allowedType == 4) {
						TESpokePath spokePath = new TESpokePath();
						spokePath.setParentLoc(x, y, z);
						world.setBlock(xx, yy, zz, ModBlocks.spokePath);
						world.setTileEntity(xx, yy, zz, spokePath);
					}
				}
			}
		}

		return meta;
	}

	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
		super.onBlockPreDestroy(world, x, y, z, meta);
		for (int dx = -4; dx <= 4; dx++) {
			for (int dy = 0; dy <= 3; dy++) {
				for (int dz = -4; dz <= 4; dz++) {
					int xx = x + dx;
					int yy = y + dy;
					int zz = z + dz;

					int allowedType = allowed[dy + 1][dx + 4][dz + 4];

					if (allowedType == 3) {
						world.setBlockToAir(xx, yy, zz);
					} else if (allowedType == 4) {
						world.setBlockToAir(xx, yy, zz);
						world.removeTileEntity(xx, yy, zz);
					}
				}
			}
		}
	}
}
