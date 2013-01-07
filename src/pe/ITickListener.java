package pe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ITickListener {

	public boolean onTick(EntityPlayer player, ItemStack stack);
}
