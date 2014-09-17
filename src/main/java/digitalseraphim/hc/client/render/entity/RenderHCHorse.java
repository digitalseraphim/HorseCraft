package digitalseraphim.hc.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.entity.passive.EntityHorse;

import org.lwjgl.opengl.GL11;

import digitalseraphim.hc.client.model.ModelHorseHarness;

public class RenderHCHorse extends RenderHorse {

	ModelHorseHarness harnessModel = new ModelHorseHarness();
	

	public RenderHCHorse(ModelBase p_i1256_1_, float p_i1256_2_) {
		super(p_i1256_1_, p_i1256_2_);
	}

	
	@Override
	protected void renderModel(EntityHorse horse, float x,
			float y, float z, float yaw,
			float pitch, float scale) {

		float f6 = 0.0625F;
		
		GL11.glPushMatrix();

		GL11.glScaled(f6,f6,f6);
		this.bindEntityTexture(horse);
		harnessModel.render(horse, 0,0,0, 0, 0, scale);
		
		GL11.glPopMatrix();
		

		super.renderModel(horse, x, y, z, yaw, pitch, scale);
	}
	
}
