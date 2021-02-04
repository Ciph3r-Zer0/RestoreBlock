package cipher.restoreblock.Runnable;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

import java.util.Map;

public class RestoreOnStop {

	public void Restore() {

		//Restore Broken Blocks When Server Stops/Restarts
		for (int i = RestoreOnRun.Break_list.size() - 1; i >= 0; i--) {

			Map.Entry<BlockState, Long> break_entry = RestoreOnRun.Break_list.get(i);

			break_entry.getKey().update(true, false);
			RestoreOnRun.Break_list.remove(i);
		}

		//Restore Placed Blocks When Server Stops/Restarts
		for (int i = RestoreOnRun.Place_list.size() - 1; i >= 0; i--) {

			Map.Entry<Block, Long> Place_entry = RestoreOnRun.Place_list.get(i);

			Place_entry.getKey().setType(Material.AIR);
			RestoreOnRun.Place_list.remove(i);
		}

		//Restore Burnt Blocks When Server Stops/Restarts
		for (int i = RestoreOnRun.Burn_list.size() - 1; i >= 0; i--) {

			Map.Entry<BlockState, Long> Burn_entry = RestoreOnRun.Burn_list.get(i);

			Burn_entry.getKey().update(true, false);
			RestoreOnRun.Burn_list.remove(i);
		}

		//Block Explode Restoration Event
		for (int i = RestoreOnRun.Explode_list.size() - 1; i >= 0; i--) {

			Map.Entry<BlockState, Long> entry = RestoreOnRun.Explode_list.get(i);

			entry.getKey().update(true, false);
			RestoreOnRun.Explode_list.remove(i);
		}
	}

	public void ClearList() {

		RestoreOnRun.Break_list.clear();
		RestoreOnRun.Place_list.clear();
		RestoreOnRun.Burn_list.clear();
		RestoreOnRun.Explode_list.clear();
	}
}
