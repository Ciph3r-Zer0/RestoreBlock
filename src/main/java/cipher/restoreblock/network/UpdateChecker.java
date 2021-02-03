package cipher.restoreblock.network;

import cipher.restoreblock.utils.Info;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker {

	private final JavaPlugin javaPlugin;
	private String spigotPluginVersion;


	private static final int ID = 75589;
	private static final String ERR_MSG = Info.Color("&cFailed to check for updates!");
	private static final String UPDATE_MSG = Info.Prefix + Info.Color("&cA new newer version of &bRestoreBlock &cis available at:&b https://www.spigotmc.org/resources/" + ID + "/updates");
	private static final String UPDATE_MGS_CONSOLE = Info.Color("&cA new newer version of &bRestoreBlock &cis available at:&b https://www.spigotmc.org/resources/" + ID + "/updates");
	private static final Permission UPDATE_PERM = new Permission("restoreblock.events.update", PermissionDefault.TRUE);
	private static final long CHECK_INTERVAL = 12_000;

	public UpdateChecker(final JavaPlugin javaPlugin) {
		this.javaPlugin = javaPlugin;
	}

	public void checkForUpdate() {

		new BukkitRunnable() {

			@Override
			public void run() {

				Bukkit.getScheduler().runTaskAsynchronously(javaPlugin, () -> {

					try {

						final HttpsURLConnection connection = (HttpsURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=" + ID).openConnection();
						connection.setRequestMethod("GET");
						spigotPluginVersion = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

					} catch (final IOException e) {

						Bukkit.getServer().getConsoleSender().sendMessage(ERR_MSG);
						e.printStackTrace();
						cancel();

						return;

					}

					if (Info.Version.equals(spigotPluginVersion)) return;

					Bukkit.getServer().getConsoleSender().sendMessage(UPDATE_MGS_CONSOLE);

					Bukkit.getScheduler().runTask(javaPlugin, () -> Bukkit.getPluginManager().registerEvents(new Listener() {

						@EventHandler(priority = EventPriority.MONITOR)
						public void onPlayerJoin(final PlayerJoinEvent event) {

							final Player player = event.getPlayer();
							if (player.hasPermission(UPDATE_PERM)){
								player.sendMessage(UPDATE_MSG);
							}
						}
					}, javaPlugin));

					cancel();
				});
			}
		}.runTaskTimer(javaPlugin, 0, CHECK_INTERVAL);
	}
}