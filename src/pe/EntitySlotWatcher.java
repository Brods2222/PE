package pe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.google.common.base.Joiner;

import net.minecraft.entity.player.EntityPlayer;

public class EntitySlotWatcher implements IWatchable {

	private int slot;
	private String username;
	private HashMap<String, IWatchable> watchables;

	public EntitySlotWatcher(String username, int slot,
			HashMap<String, IWatchable> watchables) {
		this.username = username;
		this.slot = slot;
		this.watchables = watchables;
	}

	public EntitySlotWatcher(String username, int slot) {
		this(username, slot, new HashMap<String, IWatchable>());
	}

	public int getSlot() {
		return slot;
	}

	public String getUsername() {
		return username;
	}

	public boolean has(String upgradeName) {
		return watchables.containsKey(upgradeName);
	}

	public IWatchable get(String upgradeName) {
		return watchables.get(upgradeName);
	}

	public void put(String upgradeName, IWatchable watchable) {
		watchables.put(upgradeName, watchable);
	}

	public void remove(String upgradeName) {
		watchables.remove(upgradeName);
	}

	@Override
	public void watch(EntityPlayer player, int slotWatch) {
		Set<String> upgradeNames = watchables.keySet();
		for (String upgrade : upgradeNames) {
			watchables.get(upgrade).watch(player, slot);
		}
	}

	@Override
	public void unWatch(EntityPlayer player) {
		Set<String> upgradeNames = watchables.keySet();
		for (String upgrade : upgradeNames) {
			watchables.get(upgrade).unWatch(player);
		}
	}

	@Override
	public boolean onTick(EntityPlayer player) {
		Set<String> upgradeNames = watchables.keySet();
		boolean b = false;
		for (String upgrade : upgradeNames) {
			watchables.get(upgrade).onTick(player);
			b = true;
		}
		return b;
	}

	@Override
	public String toString() {
		
		return Joiner.on(";").join(watchables.keySet().iterator());
	}
}
