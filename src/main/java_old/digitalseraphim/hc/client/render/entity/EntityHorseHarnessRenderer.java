package digitalseraphim.hc.client.render.entity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import digitalseraphim.hc.client.model.ModelHorseHarness;

public class EntityHorseHarnessRenderer extends Render {

	ModelHorseHarness harnessModel = new ModelHorseHarness();
	
	@Override
	public void doRender(Entity entity, double x, double y, double z,
			float yaw, float pitch) {
		
		GL11.glPushMatrix();
		GL11.glTranslated(x, y+.5, z);
		GL11.glRotatef(180-yaw, 0, 1, 0);
		GL11.glRotatef(pitch, 0, 0, 1);
		//System.out.printf("%f %f %f\n",x,y,z);
		//Tessellator.instance.setTranslation(entity.posX, entity.posY, entity.posZ);
		float f6 = 0.0625F;
		GL11.glScaled(f6,f6,f6);
		this.bindEntityTexture(entity);
		harnessModel.render(entity, 0,0,0, 0, 0, 1);
		Tessellator.instance.setTranslation(0,0,0);
		
		GL11.glPopMatrix();

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("hc:textures/horse_walker.png");
	}

}
