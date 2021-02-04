package cipher.restoreblock.ymlloader;

import cipher.restoreblock.RestoreBlock;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class Config {

	private static Plugin RB = RestoreBlock.getPlugin(RestoreBlock.class);
	public static FileConfiguration Configuration = RB.getConfig();
	public static File CFile = new File(RB.getDataFolder(), "config.yml");

	public static void loadConfig() {

		if (!(CFile).exists()) {

			RB.saveResource("config.yml", false);
		}
	}

	public static void reloadConfig() {

		Configuration = YamlConfiguration.loadConfiguration(CFile);
	}
}
