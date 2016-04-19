package digitalseraphim.hc.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabHC extends CreativeTabs {

    public CreativeTabHC(int index, String label) {
        super(index, label);
    }

    @SideOnly(Side.CLIENT)
    /**
     * the itemID for the item to be displayed on the tab
     */
    public Item getTabIconItem() {
        return Item.getItemFromBlock(Blocks.HAY_BLOCK);
    }

}
