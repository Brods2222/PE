package pe.item;

import pe.PE;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

public class Items {

	public static ItemStack peItem;

	public static void itemsLoad(Configuration configuration) {
		Items.peItem = new ItemStack(
				new PEtItem(getItemIdFor(configuration, "peItem", 500),
						PE.platform.getArmorMaterial(), PE.platform.getArmorRenderIndex()).setCreativeTab(CreativeTabs.tabCombat).setItemName("peItem"));
		GameRegistry.addRecipe(peItem, new Object[] { "I ", "I ", 'I',
				Item.seeds });
	}

	private static int getItemIdFor(Configuration configuration,
			String itemName, int defaultItemId) {
		Integer itemId;
		if (configuration == null) {
			itemId = Integer.valueOf(defaultItemId);
		} else {
			try {
				itemId = new Integer(configuration.get("item", itemName,
						defaultItemId).value);
			} catch (Exception var5) {
				PE.log.warning("Error while trying to access ID-List, config wasn\'t loaded properly!");
				itemId = Integer.valueOf(defaultItemId);
			}
		}
		if (itemId.intValue() < 0 || itemId.intValue() > Item.itemsList.length) {
			PE.log.warning("Item with invalid ID: " + itemName + "\n"
					+ "Invalid ID: " + itemId);
		}
		return itemId.intValue();
	}
}
