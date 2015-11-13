package digitalseraphim.hc.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import digitalseraphim.hc.block.ModBlocks;
import digitalseraphim.hc.lib.RenderIds;

public class HayPileRenderer implements ISimpleBlockRenderingHandler {
	private static final int NUM_POINTS = 3;
	// private static final int HALF_POINT = 2;
	private static final boolean DEBUG = false;

	private void renderHP(IBlockAccess world, float x, float y, float z,
			float scale) {
		IIcon i = Blocks.hay_block.getIcon(0, 0);
		float minU = i.getMinU();
		float maxU = i.getMaxU();
		float minV = i.getMinV();
		float maxV = i.getMaxV();

		// per sub section
		float dU = (maxU - minU) / (NUM_POINTS - 1);
		float dV = (maxV - minV) / (NUM_POINTS - 1);

		float dx = 1.f / (float) (NUM_POINTS - 1);
		float dy = 1.f / (float) (NUM_POINTS - 1);

		// Render
		Tessellator tessellator = Tessellator.instance;
		tessellator.setColorOpaque_I(0xffffff);// par1Block.getBlockColor()
		tessellator.setBrightness(world.getBlock((int) x,
				(int) y, (int) z).getMixedBrightnessForBlock(world, (int) x,
				(int) y, (int) z));
		// float[][] points = new float[NUM_POINTS][NUM_POINTS];
		float[][] controls = new float[3][3];
		Block[][][] neighborBlocks = new Block[3][3][3];
		int[][][] neighborBlockHeights = new int[3][3][3];

		float thisHeight;

		if(DEBUG){
			System.out.println("dU = " + dU);
			System.out.println("dV = " + dV);
		}
		for (int ii = -1; ii < 2; ii++) {
			for (int kk = 0; kk < 2; kk++) {
				for (int jj = -1; jj < 2; jj++) {
					if (DEBUG) {
						System.out.printf(
								"HayPileRenderer.renderHP() %d %d %d\n",
								(int) (x) + ii, (int) (y) + kk, (int) (z) + jj);
					}
					neighborBlocks[ii + 1][kk][jj + 1] = world.getBlock(
							(int) (x) + ii, (int) (y) + kk, (int) (z) + jj);
					neighborBlockHeights[ii + 1][kk][jj + 1] = (world
							.getBlockMetadata((int) (x) + ii, (int) (y) + kk,
									(int) (z) + jj) & 7) + 1;
					if (DEBUG) {
						System.out.printf(
								"HayPileRenderer.renderHP() got  %s %d\n",
								neighborBlocks[ii + 1][kk][jj + 1].getLocalizedName(),
								neighborBlockHeights[ii + 1][kk][jj + 1]);
					}
				}
			}
		}

		thisHeight = controls[1][1] = neighborBlockHeights[1][0][1];
		
		//System.out.printf("thisHeight = %x %x\n", neighborBlockHeights[1][0][1], neighborBlockHeights[1][0][1]);
		
		for (int ii = 0; ii < 3; ii++) {
			for (int jj = 0; jj < 3; jj++) {
				if (ii != 1 || jj != 1) {
					controls[ii][jj] = calcControl(thisHeight, ii, jj,
							neighborBlocks, neighborBlockHeights);
				}
			}
		}

		tessellator.addTranslation(x, y, z);

		for (int ii = 0; ii < NUM_POINTS - 1; ii++) {
			for (int jj = 0; jj < NUM_POINTS - 1; jj++) {
				if (ii == jj) {
					addVertexWithUV(ii * dx, 0.125 * controls[ii + 0][jj + 0],
							jj * dy, minU + ((ii + 0) * dU), minV
									+ ((jj + 0) * dV));
					addVertexWithUV(ii * dx, 0.125 * controls[ii + 0][jj + 1],
							(jj + 1) * dy, minU + ((ii + 0) * dU), minV
									+ ((jj + 1) * dV));
					addVertexWithUV((ii + 1) * dx,
							0.125 * controls[ii + 1][jj + 1], (jj + 1) * dy,
							minU + ((ii + 1) * dU), minV + ((jj + 1) * dV));
					addVertexWithUV((ii + 1) * dx,
							0.125 * controls[ii + 1][jj + 0], jj * dy, minU
									+ ((ii + 1) * dU), minV + ((jj + 0) * dV));
				} else {
					addVertexWithUV(ii * dx, 0.125 * controls[ii + 0][jj + 1],
							(jj + 1) * dy, minU + ((ii + 0) * dU), minV
									+ ((jj + 1) * dV));
					addVertexWithUV((ii + 1) * dx,
							0.125 * controls[ii + 1][jj + 1], (jj + 1) * dy,
							minU + ((ii + 1) * dU), minV + ((jj + 1) * dV));
					addVertexWithUV((ii + 1) * dx,
							0.125 * controls[ii + 1][jj + 0], jj * dy, minU
									+ ((ii + 1) * dU), minV + ((jj + 0) * dV));
					addVertexWithUV(ii * dx, 0.125 * controls[ii + 0][jj + 0],
							jj * dy, minU + ((ii + 0) * dU), minV
									+ ((jj + 0) * dV));
				}
			}
		}

		tessellator.addTranslation(-x, -y, -z);
	}

	private void addVertexWithUV(double x, double y, double z, double u,
			double v) {
		Tessellator tess = Tessellator.instance;

		if (DEBUG) {
			System.out.printf(
					"HayPileRenderer.addVertexWithUV() %f, %f, %f  %f, %f\n",
					x, y, z, u, v);
		}
		tess.addVertexWithUV(x, y, z, u, v);
	}

	private float calcControl(float thisHeight, int ii, int jj,
			Block[][][] neighborBlocks, int[][][] neighborBlockHeights) {

		if (DEBUG) {
			System.out.printf("HayPileRenderer.calcPoint() - %f %d %d\n",
					thisHeight, ii, jj);
		}

		if (ii == 1) {
			if (neighborBlocks[1][0][jj] == ModBlocks.hayPile) {
				return ((float) (neighborBlockHeights[1][0][jj]) + thisHeight) / 2f;
			} else {
				return 0;
			}
		} else if (jj == 1) {
			if (neighborBlocks[ii][0][1] == ModBlocks.hayPile) {
				return ((float) (neighborBlockHeights[ii][0][1]) + thisHeight) / 2f;
			} else {
				return 0;
			}
		} else {
			float f = 0;
			int num = 0;
			for (int iii = -1; iii < 2; iii++) {
				for (int jjj = -1; jjj < 2; jjj++) {
					if (ii + iii < 0 || ii + iii > 2) {
						continue;
					}
					if (jj + jjj < 0 || jj + jjj > 2) {
						continue;
					}
					if (neighborBlocks[ii + iii][0][jj + jjj] != ModBlocks.hayPile) {
						return 0;
					}
					f += (float) (neighborBlockHeights[ii + iii][0][jj + jjj]);
					num++;
				}
			}

			if (num > 0) {
				return f / (float) num;
			}
		}

		if (DEBUG) {
			System.out.println("HayPileRenderer.calcPoint() - return 0");
		}
		return 0;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		IIcon i = Blocks.hay_block.getIcon(0, 0);

		float minU = i.getMinU();
		float maxU = i.getMaxU();
		float minV = i.getMinV();
		float maxV = i.getMaxV();

		// Render
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();

		tessellator.addVertexWithUV(0, 0, 0, minU, minV);
		tessellator.addVertexWithUV(0, 0.125, 1, minU, maxV);
		tessellator.addVertexWithUV(1, 0, 1, maxU, maxV);
		tessellator.addVertexWithUV(1, 0.125, 0, maxU, minV);

		tessellator.draw();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {

		if (modelId == RenderIds.hayPileRenderId) {
			renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
			renderHP(world, x, y, z, 1.0F);
		}

		return false;
	}

	
	
	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return RenderIds.hayPileRenderId;
	}

}
