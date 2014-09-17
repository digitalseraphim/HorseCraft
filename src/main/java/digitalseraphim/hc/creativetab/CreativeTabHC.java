package digitalseraphim.hc.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import digitalseraphim.hc.block.BlockHayPile;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class CreativeTabHC extends CreativeTabs {

    public CreativeTabHC(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @SideOnly(Side.CLIENT)
    /**
     * the itemID for the item to be displayed on the tab
     */
    public Item getTabIconItem() {
        return Item.getItemFromBlock(Blocks.hay_block);
    }

}
