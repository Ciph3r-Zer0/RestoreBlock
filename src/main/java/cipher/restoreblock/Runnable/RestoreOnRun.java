package cipher.restoreblock.Runnable;

import cipher.restoreblock.ymlloader.Config;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestoreOnRun extends BukkitRunnable implements Listener {

	public static final List<Map.Entry<BlockState, Long>> Break_list = new ArrayList<>();
	public static final List<Map.Entry<Block, Long>> Place_list = new ArrayList<>();
	public static final List<Map.Entry<BlockState, Long>> Burn_list = new ArrayList<>();
	public static final List<Map.Entry<BlockState, Long>> Explode_list = new ArrayList<>();

	@Override
	public void run() {

		Long time = System.currentTimeMillis();

		//Block Break Restoration Event.
		for (int i = Break_list.size() - 1; i >= 0; i--) {

			Map.Entry<BlockState, Long> break_entry = Break_list.get(i);

			if (time - break_entry.getValue() > Config.Configuration.getLong("Restore-Break.Delay")) {

				break_entry.getKey().update(true, false);
				Break_list.remove(i);
			}
		}

		//Block Place Restoration Event.
		for (int i = Place_list.size() - 1; i >= 0; i--) {

			Map.Entry<Block, Long> Place_entry = Place_list.get(i);

			if (time - Place_entry.getValue() > Config.Configuration.getLong("Restore-Place.Delay")) {

				Place_entry.getKey().setType(Material.AIR);
				Place_list.remove(i);
			}
		}

		//Block Burn Restoration Event.
		for (int i = Burn_list.size() - 1; i >= 0; i--) {

			Map.Entry<BlockState, Long> Burn_entry = Burn_list.get(i);

			if (time - Burn_entry.getValue() > Config.Configuration.getLong("Restore-Burn.Delay")) {

				Burn_entry.getKey().update(true, false);
				Burn_list.remove(i);
			}
		}

		//Block Explode Restoration Event
		for (int i = Explode_list.size() - 1; i >= 0; i--) {

			Map.Entry<BlockState, Long> entry = Explode_list.get(i);

			if (time - entry.getValue() >= Config.Configuration.getLong("Restore-Explosion.Delay")) {

				if (!entry.getKey().getType().equals(Material.TNT)) {

					entry.getKey().update(true, false);
					Explode_list.remove(i);
				}
			}
		}
	}
}
