package digitalseraphim.hc.client.model;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.MathHelper;

public class ModelHCHorse extends ModelHorse {

	ModelRenderer bridle;

	public ModelHCHorse() {
		super();

		this.bridle = new ModelRenderer(this, 0, 34);
		this.bridle.addBox(-3F, -7.0F, -1.0F, 6, 1, 1);
		this.bridle.setRotationPoint(0.0F, 4.0F, -10.0F);
		this.bridle.rotateAngleX = 0.5235988F;
		this.bridle.rotateAngleY = 0.0F;
		this.bridle.rotateAngleZ = 0.0F;
	}

	@Override
	public void setLivingAnimations(EntityLivingBase p_78086_1_,
			float p_78086_2_, float p_78086_3_, float p_78086_4_) {
		super.setLivingAnimations(p_78086_1_, p_78086_2_, p_78086_3_,
				p_78086_4_);

		this.bridle.rotateAngleX = this.head.rotateAngleX;
		this.bridle.rotateAngleY = this.head.rotateAngleY;
		this.bridle.rotationPointY = this.head.rotationPointY;
		this.bridle.rotationPointZ = this.head.rotationPointZ;
	}

	@Override
	public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_,
			float p_78088_4_, float p_78088_5_, float p_78088_6_,
			float p_78088_7_) {
		// TODO Auto-generated method stub
		bridle.render(p_78088_7_);
		super.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_,
				p_78088_5_, p_78088_6_, p_78088_7_);

	}

}
