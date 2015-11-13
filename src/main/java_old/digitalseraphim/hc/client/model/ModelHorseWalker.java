package digitalseraphim.hc.client.model;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelHorseWalker {
	public static ModelHorseWalker instance = new ModelHorseWalker();
	private IModelCustom modelBase;
	private IModelCustom modelWalker;
	private IModelCustom modelSideGear;
	public static final ResourceLocation MODEL_BASE = new ResourceLocation("hc:models/horse_walker_base.obj");
	public static final ResourceLocation MODEL_WALKER = new ResourceLocation("hc:models/horse_walker.obj");
	public static final ResourceLocation MODEL_SIDE_GEAR = new ResourceLocation("hc:models/horse_walker_side_gear.obj");
	public static final ResourceLocation TEXTURE = new ResourceLocation("hc:textures/horse_walkerrrr.png");


	private ModelHorseWalker() {
		modelBase = AdvancedModelLoader.loadModel(MODEL_BASE);
		modelWalker = AdvancedModelLoader.loadModel(MODEL_WALKER);
		modelSideGear = AdvancedModelLoader.loadModel(MODEL_SIDE_GEAR);
	}

	public void render() {
		modelWalker.renderAll();
	}
	
	public void renderBase(){
		modelBase.renderAll();
	}
	
	public void renderWalker(){
		modelWalker.renderAll();
	}
	
	public void renderSideGear(){
		modelSideGear.renderAll();
	}
}
