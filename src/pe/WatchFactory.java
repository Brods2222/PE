package pe;

import java.util.HashMap;

import net.minecraftforge.common.Configuration;

public class WatchFactory {

	private static HashMap<String, Class<IWatchable>> map = new HashMap<String, Class<IWatchable>>();

	public static IWatchable getWatchable(String watchName) {
		if (map.containsKey(watchName)) {
			try {
				return map.get(watchName).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void addWatchable(String watchName,
			Class<IWatchable> watchable) {
		if (!map.containsKey(watchName)) {
			map.put(watchName, watchable);
		}
	}

	public static void watchableLoad(Configuration configuration) {
		try {
			Class<IWatchable> class1 = (Class<IWatchable>) Class
					.forName("pe.watchable.TestWatchable");
			addWatchable("test", class1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
