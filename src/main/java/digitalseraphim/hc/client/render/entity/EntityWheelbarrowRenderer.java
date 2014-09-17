package digitalseraphim.hc.client.render.entity;

import org.lwjgl.opengl.GL11;

import digitalseraphim.hc.client.model.ModelWheelbarrow;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.entity.item.EntityMinecartEmpty;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class EntityWheelbarrowRenderer extends Render {
	ModelWheelbarrow wb = new ModelWheelbarrow();

	@Override
	public void doRender(Entity entity, double x, double y, double z,
			float yaw, float partialTickTime) {
		GL11.glPushMatrix();
		this.bindEntityTexture(entity);

		GL11.glTranslatef((float)x,(float)y,(float)z);
//		System.out.println("entity.rotationYaw = " + entity.rotationYaw*180);
		GL11.glRotatef(entity.rotationYaw,0,1,0);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.wb.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F,
				0.0625F);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return new ResourceLocation("textures/entity/minecart.png");
	}

}
