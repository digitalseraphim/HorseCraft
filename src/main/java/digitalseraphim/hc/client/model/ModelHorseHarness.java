package digitalseraphim.hc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHorseHarness extends ModelBase {
	ModelRenderer harness;
	
	
	public ModelHorseHarness() {
        this.harness = new ModelRenderer(this, 0, 34);
        this.harness.addBox(-2.5F, -7.0F, -7.0F, 5, 1, 1);
        this.harness.setRotationPoint(0.0F, 4.0F, -10.0F);
	}
    
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
		this.harness.render(par7);
    }
}
