package pe;

import java.util.EnumSet;

import pe.item.PEtItem;


import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.EnumHelper;

@SideOnly(Side.CLIENT)
public class PlatformClient extends Platform {

	public int armorRenderIndex;

	public PlatformClient() {
		armorRenderIndex = RenderingRegistry
				.addNewArmourRendererPrefix("pe/pe_armor");
		TickRegistry.registerTickHandler(this, Side.CLIENT);
	}

	public EntityPlayer getPlayerInstance() {
		return Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {

		if (type.contains(TickType.CLIENT)) {
			PE.keyboard.sendKeyUpdate();
			EntityPlayer player = this.getPlayerInstance();

			if (player != null) {
				// for (int i = 0; i < 40; ++i) {
				// if (player.inventory.getStackInSlot(i) != null
				// && player.inventory.getStackInSlot(i).getItem() instanceof
				// ITickListener) {
				// ((ITickListener) player.inventory.getStackInSlot(i)
				// .getItem()).onTick(player,
				// player.inventory.getStackInSlot(i));
				// }
				// }
				boolean b = true;
				for (int i = 0; i < 40; i++) {
					if (player.inventory.getStackInSlot(i) != null
							&& player.inventory.getStackInSlot(i).getItem() instanceof PEtItem) {
						if (PE.watcher.isWatch(player, i)) {
							PE.watcher.onTick(player, i);
							b = false;
						} else {
							NBTTagCompound compound = StackUtils
									.getNBT(player.inventory.getStackInSlot(i));
							PE.watcher.watch(player, i,
									compound.getString("pe_watch"));
						}
					} else if (b && PE.watcher.isWatch(player, i)) {
						PE.watcher.unWatch(player, i);
					}
				}
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub

	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public int getArmorRenderIndex() {
		// TODO Auto-generated method stub
		return armorRenderIndex;
	}
}
