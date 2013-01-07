package pe;

import java.io.File;
import java.util.logging.Logger;

import pe.item.Items;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;


import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = "PE")
@NetworkMod(clientSideRequired = true, clientPacketHandlerSpec = @NetworkMod.SidedPacketHandler(channels = { "pe" }, packetHandler = NetworkManagerClient.class), serverPacketHandlerSpec = @NetworkMod.SidedPacketHandler(channels = { "pe" }, packetHandler = NetworkManager.class))
public class PE {

	@SidedProxy(clientSide = "pe.PlatformClient", serverSide = "pe.Platform")
	public static Platform platform;
	@SidedProxy(clientSide = "pe.KeyboardClient", serverSide = "pe.Keyboard")
	public static Keyboard keyboard;
	@SidedProxy(clientSide = "pe.NetworkManagerClient", serverSide = "pe.NetworkManager")
	public static NetworkManager network;
	@SidedProxy(clientSide = "pe.WatcherManagerClient", serverSide = "pe.WatcherManager")
	public static WatcherManager watcher;

	public static Logger log;

	@PreInit
	public void preLoad(FMLPreInitializationEvent event) {
		log = event.getModLog();
		Configuration configuration;
		try {
			File configurationFile = new File(
					event.getModConfigurationDirectory(), "PE.cfg");
			configuration = new Configuration(configurationFile);
			configuration.load();
			log.info("Config loaded from "
					+ configurationFile.getAbsolutePath());
		} catch (Exception exception) {
			log.warning("Error while trying to access configuration! "
					+ exception);
			configuration = null;
		}

		if (configuration != null) {
			WatchFactory.watchableLoad(configuration);
			Items.itemsLoad(configuration);
			configuration.save();
		}
		NetworkRegistry.instance().registerConnectionHandler(
				new ConnectionHandler());
	}
}
