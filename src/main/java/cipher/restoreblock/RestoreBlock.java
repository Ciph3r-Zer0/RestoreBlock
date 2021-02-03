package cipher.restoreblock;

import cipher.restoreblock.network.Metrics;
import cipher.restoreblock.runtime.Run;
import org.bukkit.plugin.java.JavaPlugin;

public final class RestoreBlock extends JavaPlugin {

	@Override
	public void onEnable() {

		new Run(this).splashScreen();

		new Run(this).detectVersion();

		new Run(this).registerConfig();

		new Run(this).registerCommands();

		new Run(this).registerEvents();

		new Run(this).registerRunnable();

		new Run(this).CheckUpdate();

		new Run(this).Metrics();

	}

	@Override
	public void onDisable() {

		new Run(this).restoreOnStop();

	}
}
