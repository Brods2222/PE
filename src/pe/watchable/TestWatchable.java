package pe.watchable;

import pe.IWatchable;
import pe.PE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;


public class TestWatchable implements IWatchable {

	ItemStack stack;

	@Override
	public void watch(EntityPlayer player, int slotWatch) {
		PE.log.info("watch " + player.worldObj.isRemote);
		player.setHasActivePotion(true);
		this.stack = player.inventory.getStackInSlot(slotWatch);
		if (this.stack != null) {
			this.stack.getItem().setIconCoord(0, 2);
		}
	}

	@Override
	public void unWatch(EntityPlayer player) {
		PE.log.info("unWatch");
		player.setHasActivePotion(false);
		if (this.stack != null) {
			this.stack.getItem().setIconCoord(0, 0);
		}
	}

	@Override
	public boolean onTick(EntityPlayer player) {
		player.setHasActivePotion(true);
		return true;
	}

}
