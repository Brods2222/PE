package pe;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NetworkManagerClient extends NetworkManager {

	public void keyUpdate(int keyState) {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(
					byteArrayOutputStream);
			dataOutputStream.writeByte(0);
			dataOutputStream.writeInt(keyState);
			dataOutputStream.close();
			Packet250CustomPayload packet250CustomPayload = new Packet250CustomPayload();
			packet250CustomPayload.channel = "pe";
			packet250CustomPayload.isChunkDataPacket = false;
			packet250CustomPayload.data = byteArrayOutputStream.toByteArray();
			packet250CustomPayload.length = byteArrayOutputStream.size();
			PacketDispatcher.sendPacketToServer(packet250CustomPayload);
		} catch (IOException var5) {
			throw new RuntimeException(var5);
		}
	}

	@Override
	public void watch(int slotWatch, String watchable) {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(
					byteArrayOutputStream);
			dataOutputStream.writeByte(1);
			dataOutputStream.writeInt(slotWatch);
			dataOutputStream.writeUTF(watchable);
			dataOutputStream.close();
			Packet250CustomPayload packet250CustomPayload = new Packet250CustomPayload();
			packet250CustomPayload.channel = "pe";
			packet250CustomPayload.isChunkDataPacket = false;
			packet250CustomPayload.data = byteArrayOutputStream.toByteArray();
			packet250CustomPayload.length = byteArrayOutputStream.size();
			PacketDispatcher.sendPacketToServer(packet250CustomPayload);
		} catch (IOException var5) {
			throw new RuntimeException(var5);
		}
	}

	@Override
	public void unWatch(int slotWatch) {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(
					byteArrayOutputStream);
			dataOutputStream.writeByte(2);
			dataOutputStream.writeInt(slotWatch);
			dataOutputStream.close();
			Packet250CustomPayload packet250CustomPayload = new Packet250CustomPayload();
			packet250CustomPayload.channel = "pe";
			packet250CustomPayload.isChunkDataPacket = false;
			packet250CustomPayload.data = byteArrayOutputStream.toByteArray();
			packet250CustomPayload.length = byteArrayOutputStream.size();
			PacketDispatcher.sendPacketToServer(packet250CustomPayload);
		} catch (IOException var5) {
			throw new RuntimeException(var5);
		}
	}

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		// TODO Auto-generated method stub

	}

}
