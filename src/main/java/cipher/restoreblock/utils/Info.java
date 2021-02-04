package cipher.restoreblock.utils;

import cipher.restoreblock.RestoreBlock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class Info {

	private static Plugin RB = RestoreBlock.getPlugin(RestoreBlock.class);
	private static final String ServerVersionName = getServer().getClass().getPackage().getName();

	public static String Color(String s) {

		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public static final String Prefix = Color("&bRestoreBlock &8>> ");
	public static final String Version = RB.getDescription().getVersion();
	public static final String Author = "Cipher";
	public static final String ServerVersion = ServerVersionName.substring(ServerVersionName.lastIndexOf('.') + 1);
}
