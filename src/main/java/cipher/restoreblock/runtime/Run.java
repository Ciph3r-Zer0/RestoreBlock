package cipher.restoreblock.runtime;

import cipher.restoreblock.Runnable.RestoreOnRun;
import cipher.restoreblock.Runnable.RestoreOnStop;
import cipher.restoreblock.commands.RBCommands;
import cipher.restoreblock.events.V_1_8;
import cipher.restoreblock.network.Metrics;
import cipher.restoreblock.network.UpdateChecker;
import cipher.restoreblock.utils.Info;
import cipher.restoreblock.ymlloader.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Run {

	private final JavaPlugin javaPlugin;
	private final RBCommands commands;

	public Run(final JavaPlugin javaPlugin){
		this.javaPlugin = javaPlugin;
		this.commands = new RBCommands();
	}

	public void splashScreen() {

		Bukkit.getServer().getConsoleSender().sendMessage(Info.Color(" "));
		Bukkit.getServer().getConsoleSender().sendMessage(Info.Color("&b_____           _                   ____  _            _"));
		Bukkit.getServer().getConsoleSender().sendMessage(Info.Color("&b|  __ \\         | |                 |  _ \\| |          | |"));
		Bukkit.getServer().getConsoleSender().sendMessage(Info.Color("&b| |__) |___  ___| |_ ___  _ __ ___  | |_) | | ___   ___| | __"));
		Bukkit.getServer().getConsoleSender().sendMessage(Info.Color("&b|  _  // _ \\/ __| __/ _ \\| '__/ _ \\ |  _ <| |/ _ \\ / __| |/ /"));
		Bukkit.getServer().getConsoleSender().sendMessage(Info.Color("&b| | \\ \\  __/\\__ \\ || (_) | | |  __/ | |_) | | (_) | (__|   <"));
		Bukkit.getServer().getConsoleSender().sendMessage(Info.Color("&b|_|  \\_\\___||___/\\__\\___/|_|  \\___| |____/|_|\\___/ \\___|_|\\_\\"));
		Bukkit.getServer().getConsoleSender().sendMessage(Info.Color(" "));
	}

	public void detectVersion() {

		try {

			Bukkit.getServer().getConsoleSender().sendMessage(Info.Color("&cDetected Server Version: &b" + Info.ServerVersion));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void registerConfig() {

		try {

			Bukkit.getServer().getConsoleSender().sendMessage(Info.Color("&cPreparing Config Files."));
			Config.loadConfig();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void registerCommands() {

		try {

			Bukkit.getServer().getConsoleSender().sendMessage(Info.Color("&cRegistering Commands."));
			javaPlugin.getCommand(commands.MainCommand).setExecutor(commands);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void registerEvents() {

		try {

			Bukkit.getServer().getConsoleSender().sendMessage(Info.Color("&cRegistering Listeners."));
			Bukkit.getServer().getPluginManager().registerEvents(new V_1_8(), javaPlugin);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void registerRunnable() {

		try {

			Bukkit.getServer().getConsoleSender().sendMessage(Info.Color("&cRegistering Runnables."));
			new RestoreOnRun().runTaskTimer(javaPlugin, 0, Config.Configuration.getInt("Check-Interval"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CheckUpdate(){

		try {

			Bukkit.getServer().getConsoleSender().sendMessage(Info.Color("&cChecking For Updates..."));
			Bukkit.getServer().getConsoleSender().sendMessage(Info.Color(" "));
			new UpdateChecker(javaPlugin).checkForUpdate();

		} catch (Exception e) {

		}
	}

	public void Metrics(){

		try {

			new Metrics(javaPlugin,6621);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void restoreOnStop() {

		try {

			new RestoreOnStop().Restore();
			new RestoreOnStop().ClearList();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
