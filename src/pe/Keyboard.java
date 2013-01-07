package pe;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;

public class Keyboard {
	private Map altKeyState = new HashMap();
	private Map boostKeyState = new HashMap();
	private Map forwardKeyState = new HashMap();
	private Map modeSwitchKeyState = new HashMap();
	private Map jumpKeyState = new HashMap();
	private Map sideinventoryKeyState = new HashMap();

	public boolean isAltKeyDown(EntityPlayer var1) {
		return this.altKeyState.containsKey(var1) ? ((Boolean) this.altKeyState
				.get(var1)).booleanValue() : false;
	}

	public boolean isBoostKeyDown(EntityPlayer var1) {
		return this.boostKeyState.containsKey(var1) ? ((Boolean) this.boostKeyState
				.get(var1)).booleanValue() : false;
	}

	public boolean isForwardKeyDown(EntityPlayer var1) {
		return this.forwardKeyState.containsKey(var1) ? ((Boolean) this.forwardKeyState
				.get(var1)).booleanValue() : false;
	}

	public boolean isJumpKeyDown(EntityPlayer var1) {
		return this.jumpKeyState.containsKey(var1) ? ((Boolean) this.jumpKeyState
				.get(var1)).booleanValue() : false;
	}

	public boolean isModeSwitchKeyDown(EntityPlayer var1) {
		return this.modeSwitchKeyState.containsKey(var1) ? ((Boolean) this.modeSwitchKeyState
				.get(var1)).booleanValue() : false;
	}

	public boolean isSideinventoryKeyDown(EntityPlayer var1) {
		return this.sideinventoryKeyState.containsKey(var1) ? ((Boolean) this.sideinventoryKeyState
				.get(var1)).booleanValue() : false;
	}

	public boolean isSneakKeyDown(EntityPlayer var1) {
		return var1.isSneaking();
	}

	public void sendKeyUpdate() {
	}

	public void processKeyUpdate(EntityPlayer var1, int keyState) {
		this.altKeyState.put(var1, Boolean.valueOf((keyState & 1) != 0));
		this.boostKeyState.put(var1, Boolean.valueOf((keyState & 2) != 0));
		this.forwardKeyState.put(var1, Boolean.valueOf((keyState & 4) != 0));
		this.modeSwitchKeyState.put(var1, Boolean.valueOf((keyState & 8) != 0));
		this.jumpKeyState.put(var1, Boolean.valueOf((keyState & 16) != 0));
		this.sideinventoryKeyState.put(var1,
				Boolean.valueOf((keyState & 32) != 0));
	}
}