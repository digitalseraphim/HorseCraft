package digitalseraphim.hc.item;

import digitalseraphim.hc.HorseCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

abstract class ItemHCBase extends Item {
	
	public ItemHCBase(int maxStackSize, String unlocalizedName) {
		setCreativeTab(HorseCraft.tabsHC);
		setUnlocalizedName(unlocalizedName);
		setMaxStackSize(maxStackSize);
	
		init(unlocalizedName);
		if(HorseCraft.proxy.isClient()){
			initClient(unlocalizedName);
		}
	}
	
	void init(String unlocalizedName){
		GameRegistry.register(this);
	}
	
	void initClient(String unlocalizedName){
		ItemModelMesher imm = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		ModelResourceLocation res = new ModelResourceLocation("hc:" + unlocalizedName, "inventory");
		imm.register(this, 0, res);
	}
}
