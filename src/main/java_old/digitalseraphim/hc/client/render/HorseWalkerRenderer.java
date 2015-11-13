package digitalseraphim.hc.client.render;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import digitalseraphim.hc.client.model.ModelHorseWalker;

public class HorseWalkerRenderer implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		switch (type) {
		case ENTITY: {
			renderHW(-0.5F, 0.0F, 0.5F, 1.0F / 9.0F);
			return;
		}
		case EQUIPPED: {
			renderHW(0.0F, 0.0F, 1.0F, 1.0F / 9.0F);
			return;
		}
		case INVENTORY: {
			renderHW(-1.0F, 0.0F, 1.0F, 1.0F / 9.0F);
			return;
		}
		default:
			return;
		}

	}

	private void renderHW(float x, float y, float z, float scale) {

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);

		// Scale, Translate, Rotate
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(x, y, z);

		// Bind texture
		FMLClientHandler.instance().getClient().renderEngine
				.bindTexture(ModelHorseWalker.TEXTURE);
		// Render
		ModelHorseWalker.instance.render();

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

}
