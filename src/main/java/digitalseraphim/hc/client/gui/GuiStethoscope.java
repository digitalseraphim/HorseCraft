package digitalseraphim.hc.client.gui;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.sun.xml.internal.stream.Entity;

import digitalseraphim.hc.HorseCraft;
import digitalseraphim.hc.inventory.ContainerStethoscopeInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.AchievementPage;

public class GuiStethoscope extends GuiContainer {
	protected static final ResourceLocation invBackground = new ResourceLocation(
			"hc:textures/guis/hc_horse.png");
	EntityPlayer player;
	World world;
	EntityLivingBase entityLiving;
	int count = 1000;
	int selectedTab = 0;

	final int MAIN_WIDTH = 176;
	final int TAB_WIDTH = 122;
	final int TAB_OVERLAP = 4;

	final int FULL_WIDTH = MAIN_WIDTH + TAB_WIDTH - TAB_OVERLAP;
	final int FULL_HEIGHT = 238;

	int relX = -1;
	int relY = -1;

	static final int numTabs = 4;

	public GuiStethoscope(EntityPlayer player, EntityLivingBase entity) {
		super(new ContainerStethoscopeInventory(player, entity));
		this.xSize = FULL_WIDTH;
		this.ySize = FULL_HEIGHT;
		this.player = player;
		this.world = player.worldObj;
		this.entityLiving = entity;
		this.count = 1000;
	}

	@Override
	protected void actionPerformed(GuiButton p_146284_1_) {
		System.out.println("actionPerformed - " + p_146284_1_.id);
		super.actionPerformed(p_146284_1_);
	}

	@Override
	public void updateScreen() {
		// TODO Auto-generated method stub
		super.updateScreen();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui() {
		super.initGui();
		System.out.println("width = " + this.width);
		System.out.println("height = " + this.height);

		this.buttonList.clear();
		// this.buttonList.add(new GuiOptionButton(1, this.width / 2 + 24,
		// this.height / 2 + 74, 80, 20, I18n.format("gui.done",
		// new Object[0])));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float ticks) {
		// int k = (this.width - FULL_WIDTH) / 2;
		// int l = (this.height - FULL_HEIGHT) / 2;

		// this.guiLeft = k;
		// this.guiTop = l;
		this.relX = mouseX - guiLeft;
		this.relY = mouseY - guiTop;

		super.drawScreen(mouseX, mouseY, ticks);
	}

	protected void drawGuiContainerBackgroundLayer(float p_146976_1_,
			int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.getTextureManager().bindTexture(invBackground);
		func_146110_a(guiLeft, guiTop, 0, 0, MAIN_WIDTH, this.ySize, 512, 512);

		func_146110_a(guiLeft + MAIN_WIDTH - TAB_OVERLAP, guiTop + 28,
				TAB_WIDTH * selectedTab, FULL_HEIGHT, TAB_WIDTH,
				FULL_HEIGHT - 28, 512, 512);

		for (int i = 0; i < numTabs; i++) {
			boolean f = i == selectedTab;
			func_146110_a(guiLeft + MAIN_WIDTH - TAB_OVERLAP + (28 * i),
					guiTop, (float) ((MAIN_WIDTH + (28 * i))), (float) (f ? 30
							: 0), 28, (f ? 32 : 30), 512, 512);
		}

		// had to split these apart to avoid visual glitch

		for (int i = 0; i < numTabs; i++) {
			ItemStack itemstack = null;
			String str = null;
			GL11.glDisable(GL11.GL_LIGHTING);
			switch (i) {
			case 0:// inv
			case 1:
				itemstack = new ItemStack(Item.getItemFromBlock(Blocks.chest),
						1, 0);
				str = (i == 0) ? "L" : "R";
				break;
			case 2:// stats
				itemstack = new ItemStack(HorseCraft.proxy.stethoscope, 1, 0);
				break;
			case 3:// adv
				itemstack = new ItemStack(Items.experience_bottle, 1, 0);
				break;
			}

			itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj,
					this.mc.getTextureManager(), itemstack, guiLeft
							+ MAIN_WIDTH - TAB_OVERLAP + (28 * i) + 6,
					guiTop + 7);
			itemRender.renderItemOverlayIntoGUI(this.fontRendererObj,
					this.mc.getTextureManager(), itemstack, guiLeft
							+ MAIN_WIDTH - TAB_OVERLAP + (28 * i) + 6,
					guiTop + 7, str);
		}

		// this.zLevel ++;

		switch (selectedTab) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		}

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int left = MAIN_WIDTH - TAB_OVERLAP + 7;
		int top = 29 + 6;

		super.drawGuiContainerForegroundLayer(mouseX, mouseY);

		GL11.glEnable(GL11.GL_DEPTH_TEST);
		// GuiInventory.func_147046_a(85, 130, 50, (float) (-relX + 85),
		// (float) (-relY + 25), this.entityLiving);

		switch (selectedTab) {
		case 0:
			// drawRect( left + 3*18, top, left + 6*18, top + 9*18, 0xffc6c6c6);
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			Tessellator t = Tessellator.instance;
			int offsetX = MAIN_WIDTH - TAB_OVERLAP + (TAB_WIDTH / 2);
			int offsetY = FULL_HEIGHT / 2;
			int radius = 60;
			double[] factors = {1, .5, .75};
			t.addTranslation(offsetX, offsetY, 0);
			
			this.mc.getTextureManager().bindTexture(invBackground);
			t.startDrawing(GL11.GL_TRIANGLES);

			for (int point = 0; point < 3; point++) {
				double angle = Math.toRadians(60 + (120 * point));
				double nextAngle = Math.toRadians(60 + (120 * (point + 1)));
				double deltaX =  radius * Math.sin(angle);
				double nextDeltaX = radius * Math.sin(nextAngle);
				double deltaY = radius * Math.cos(angle);
				double nextDeltaY = radius * Math.cos(nextAngle);
				double scaledDeltaX = factors[point] * deltaX;
				double nextScaledDeltaX = factors[(point)%3] * nextDeltaX;
				double scaledDeltaY = factors[point] * deltaY;
				double nextScaledDeltaY = factors[(point)%3] * nextDeltaY;

				t.setColorOpaque_I(0x7f << (point * 8));
				t.addVertexWithUV(0, 0, this.zLevel, 511./512., 511./512.);
				t.addVertexWithUV(deltaX, deltaY, this.zLevel, 511./512., 511./512.);
				t.addVertexWithUV(nextDeltaX, nextDeltaY, this.zLevel, 511./512., 511./512.);

				t.setColorOpaque_I(0xff << (point * 8));
				t.addVertexWithUV(0, 0, this.zLevel+1, 511./512., 511./512.);
				t.addVertexWithUV(scaledDeltaX, scaledDeltaY, this.zLevel+1, 511./512., 511./512.);
				t.addVertexWithUV(nextScaledDeltaX, nextScaledDeltaY, this.zLevel+1, 511./512., 511./512.);
			}

			t.draw();
			t.addTranslation(-offsetX, -offsetY, 0);
			break;
		}
	}

	/**
	 * Fired when a key is typed. This is the equivalent of
	 * KeyListener.keyTyped(KeyEvent e).
	 */
	// @Override
	// protected void keyTyped(char par1, int par2)
	// {
	// if (par2 == this.mc.gameSettings.keyBindInventory.getKeyCode())
	// {
	// this.mc.displayGuiScreen((GuiScreen)null);
	// this.mc.setIngameFocus();
	// }
	// else
	// {
	// super.keyTyped(par1, par2);
	// }
	// }

	@Override
	protected void mouseMovedOrUp(int mouseX, int mouseY, int which) {
		int a = relX - (MAIN_WIDTH - TAB_OVERLAP);
		if (which == 0 && a > 0 && a < 28 * numTabs && relY > 0 && relY < 30) {

			for (int i = 0; i < numTabs; i++) {
				if (a < 28 * (i + 1)) {
					selectedTab = i;
					return;
				}
			}
		}

		super.mouseMovedOrUp(mouseX, mouseY, which);
	}

}
