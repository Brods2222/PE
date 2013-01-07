package pe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IWatchable {

	public void watch(EntityPlayer player, int slotWatch);

	public void unWatch(EntityPlayer player);

	public boolean onTick(EntityPlayer player);
}
