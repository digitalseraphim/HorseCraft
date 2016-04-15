package digitalseraphim.hc.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;

public class ClientProxy extends CommonProxy {

	@Override
	public void initItems() {
		super.initItems();

		ModelResourceLocation res = new ModelResourceLocation("hc:horseScanner","inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(horseScanner, 0, res);
	}
}
