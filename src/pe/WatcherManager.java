package pe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;

public class WatcherManager {

	private HashMap<String, IWatchable[]> watchable = new HashMap<String, IWatchable[]>();

	public WatcherManager() {
	}

	public void watch(EntityPlayer player, int currentSlot, String watchUpgrade) {
		if (!isWatch(player, currentSlot)) {
			IWatchable[] watchables = watchable.get(player.username);
			if (watchables == null) {
				watchables = new IWatchable[40];
			}
			EntitySlotWatcher slotWatcher = (EntitySlotWatcher) watchables[currentSlot];
			if (slotWatcher == null) {
				slotWatcher = new EntitySlotWatcher(player.username,
						currentSlot);
			}
			Iterable<String> iterable = Splitter.on(';').split(watchUpgrade);
			for (String watch : iterable) {
				if (!slotWatcher.has(watch)) {
					IWatchable watchable = WatchFactory.getWatchable(watch);
					if (watchable != null) {
						slotWatcher.put(watch, watchable);
					}
				}
			}
			slotWatcher.watch(player, currentSlot);
			watchables[currentSlot] = slotWatcher;
			watchable.put(player.username, watchables);
			if (PE.platform.isClient()) {
				PE.network.watch(currentSlot, slotWatcher.toString());
			}
		}
	}

	public void unWatch(EntityPlayer player, int slotWatch) {
		if (isWatch(player, slotWatch)) {
			IWatchable[] watchables = watchable.get(player.username);
			watchables[slotWatch].unWatch(player);
			watchables[slotWatch] = null;
			if (PE.platform.isClient()) {
				PE.network.unWatch(slotWatch);
			}
		}
	}

	public boolean onTick(EntityPlayer player, int slotWatch) {
		boolean b = false;
		if (isWatch(player, slotWatch)) {
			IWatchable[] watchables = watchable.get(player.username);
			watchables[slotWatch].onTick(player);
			b = true;

		}
		return b;
	}

	public boolean isWatch(EntityPlayer player, int slotWatch) {
		if (watchable.containsKey(player.username)) {
			IWatchable[] watchables = watchable.get(player.username);
			if (watchables[slotWatch] == null) {
				return false;
			}
			return true;
		}
		return false;
	}
}
