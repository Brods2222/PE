package pe;

import java.util.EnumSet;

import pe.item.PEtItem;


import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.EnumHelper;

public class Platform implements ITickHandler {

	private EnumArmorMaterial peMaterial;
	public Platform() {
		TickRegistry.registerTickHandler(this, Side.SERVER);
	}

	public EntityPlayer getPlayerInstance() {
		return null;
	}

	public void messagePlayer(EntityPlayer player, String message) {
		if (player instanceof EntityPlayerMP) {
			((EntityPlayerMP) player).playerNetServerHandler
					.sendPacketToPlayer(new Packet3Chat(message));
		} else {
			player.addChatMessage(message);
		}
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if (type.contains(TickType.PLAYER)) {
			EntityPlayer player = (EntityPlayer) tickData[0];

			if (player.isDead) {
				return;
			}

			boolean b = false;

			for (int i = 0; i < 1; ++i) {
				if (player.inventory.getStackInSlot(i) != null
						&& player.inventory.getStackInSlot(i).getItem() instanceof PEtItem
						&& PE.watcher.isWatch(player, i)) {
					PE.watcher.onTick(player, i);
					b = true;
				}
			}

			if (b) {
				player.openContainer.updateCraftingResults();
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub

	}

	@Override
	public EnumSet<TickType> ticks() {
		// TODO Auto-generated method stub
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "PE";
	}

	public boolean isClient() {
		return FMLCommonHandler.instance().getEffectiveSide().isClient();
	}

	public EnumArmorMaterial getArmorMaterial() {
		if(peMaterial == null) {
			peMaterial = EnumHelper.addArmorMaterial("PE", 100, new int[] {1, 0, 0, 0}, 0);
		}
		return peMaterial;
	}

	public int getArmorRenderIndex() {
		return -1;
		
	}
}
