package pe;

import pe.item.PEtItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;

public class ConnectionHandler implements IConnectionHandler {

	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler,
			INetworkManager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler,
			INetworkManager manager) {
		// PE.log.info("connectionReceived(isClient: " + PE.platform.isClient()
		// + " );");
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server,
			int port, INetworkManager manager) {
		// PE.log.info("connectionOpened1(isClient: " + PE.platform.isClient() +
		// " );");
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler,
			MinecraftServer server, INetworkManager manager) {
		// PE.log.info("connectionOpened2(isClient: " + PE.platform.isClient() +
		// " );");
	}

	@Override
	public void connectionClosed(INetworkManager manager) {
		if (PE.platform.isClient()) {
			EntityPlayer player = PE.platform.getPlayerInstance();
			for (int i = 0; i < 1; i++) {
				if (player.inventory.getStackInSlot(i) != null
						&& player.inventory.getStackInSlot(i).getItem() instanceof PEtItem
						&& PE.watcher.isWatch(player, i)) {
					PE.watcher.unWatch(PE.platform.getPlayerInstance(), i);
				}
			}
		}
		PE.log.info("connectionClosed(isClient: " + PE.platform.isClient()
				+ " );");
	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler,
			INetworkManager manager, Packet1Login login) {
		// PE.log.info("clientLoggedIn(isClient: " + PE.platform.isClient() +
		// " );");
	}

}
