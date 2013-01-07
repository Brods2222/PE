package pe;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class NetworkManager implements IPacketHandler {

	public void keyUpdate(int keyState) {
	}

	public void watch(int slotWatch, String watchable) {
		
	}

	public void unWatch(int slotWatch) {
		
	}

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		EntityPlayerMP playerMP = (EntityPlayerMP) player;
		ByteArrayInputStream var5 = new ByteArrayInputStream(packet.data, 1,
				packet.data.length - 1);
		try {
			DataInputStream dataInputStream;
			switch (packet.data[0]) {
			case 0:
				dataInputStream = new DataInputStream(var5);
				int keyState = dataInputStream.readInt();
				PE.keyboard.processKeyUpdate(playerMP, keyState);
				break;
			case 1:
				dataInputStream = new DataInputStream(var5);
				int slotWatch = dataInputStream.readInt();
				String watchable = dataInputStream.readUTF();
				PE.watcher.watch(playerMP, slotWatch, watchable);
				break;
			case 2:
				dataInputStream = new DataInputStream(var5);
				int slotWatch1 = dataInputStream.readInt();
				PE.watcher.unWatch(playerMP, slotWatch1);
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
