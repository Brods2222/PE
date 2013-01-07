package pe;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class StackUtils {

	public static NBTTagCompound getNBT(ItemStack stack) {
		NBTTagCompound compound = stack.getTagCompound();

		if (compound == null) {
			compound = new NBTTagCompound();
			stack.setTagCompound(compound);
		}

		return compound;
	}
}
