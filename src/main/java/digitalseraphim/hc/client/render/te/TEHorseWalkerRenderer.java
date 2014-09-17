package digitalseraphim.hc.client.render.te;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import digitalseraphim.hc.client.model.ModelHorseWalker;
import digitalseraphim.hc.tileEntity.TEHorseWalker;

public class TEHorseWalkerRenderer extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y,
			double z, float scale) {
		if (tileEntity instanceof TEHorseWalker) {
			TEHorseWalker horseWalker = (TEHorseWalker)tileEntity;
			
			GL11.glPushMatrix();
			//GL11.glDisable(GL11.GL_LIGHTING);

			// Scale, Translate, Rotate
			// scaleTranslateRotate(x, y, z, horseWalker.getOrientation());
			GL11.glTranslated(x+.5, y, z+.5);
			GL11.glScaled(.5, .5, .5);

			GL11.glPushMatrix();
			GL11.glRotated(horseWalker.getRot()+30., 0, 1, 0);

			// Bind texture
			FMLClientHandler.instance().getClient().renderEngine
					.bindTexture(ModelHorseWalker.TEXTURE);

			// Render
			ModelHorseWalker.instance.renderWalker();
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
			GL11.glTranslated(0,.7,0);
			GL11.glRotated(horseWalker.getRot()*2+30., 0, 0, 1);
			GL11.glTranslated(0,-.7,0);

			// Render
			ModelHorseWalker.instance.renderSideGear();
			GL11.glPopMatrix();
			
			
			ModelHorseWalker.instance.renderBase();
			
			
			
			GL11.glPopMatrix();

		}else{
			System.out.println("te is not a horse walker " + tileEntity.getClass().getCanonicalName());
		}
	}

}
