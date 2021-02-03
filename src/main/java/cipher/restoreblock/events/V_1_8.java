package cipher.restoreblock.events;

import cipher.restoreblock.Runnable.RestoreOnRun;
import cipher.restoreblock.ymlloader.Config;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;

public class V_1_8 implements Listener {

	//Block Break Event Handler
	@EventHandler
	public void onBreak(BlockBreakEvent event) {

		Player player = event.getPlayer();
		Block block = event.getBlock();
		BlockState blockState = block.getState();
		Material material = blockState.getType();
		Long time = System.currentTimeMillis();
		Location loc = block.getLocation();
		GameMode gameMode = player.getGameMode();

		if(Config.Configuration.getBoolean("Restore-Break.Ignore-Creative") && gameMode.equals(GameMode.CREATIVE)){ return; }

		boolean HasPermission = player.hasPermission("restoreblock.events.blockbreak");

		if (!(Config.Configuration.getBoolean("Restore-Break.Item-Drops"))) {

			block.setType(Material.AIR);

		}

		if (Config.Configuration.getBoolean("Restore-Break.Restore")) {

			if(!(Config.Configuration.getStringList("Restore-Break.Disabled-Worlds").contains(loc.getWorld().getName()))){

				if (!HasPermission) {

					if(!(Config.Configuration.getList("Restore-Break.Excluded-Materials").contains(material.toString()))){

						RestoreOnRun.Break_list.add(new AbstractMap.SimpleImmutableEntry<>(blockState, time));

					}
				}
			}
		}
	}

	//Block Place Event Handler
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {

		Player player = event.getPlayer();
		Block block = event.getBlock();
		BlockState blockState = block.getState();
		Material material = blockState.getType();
		Long time = System.currentTimeMillis();
		Location loc = block.getLocation();
		GameMode gameMode = player.getGameMode();

		if(Config.Configuration.getBoolean("Restore-Place.Ignore-Creative") && !(gameMode.equals(GameMode.CREATIVE))){ return; }

		boolean HasPermission = player.hasPermission("restoreblock.events.blockplace");

		if (Config.Configuration.getBoolean("Restore-Place.Restore")) {

			if(!(Config.Configuration.getStringList("Restore-Place.Disabled-Worlds").contains(loc.getWorld().getName()))){

				if (!HasPermission) {

					if(!(Config.Configuration.getList("Restore-Place.Excluded-Materials").contains(material.toString()))){

						RestoreOnRun.Place_list.add(new AbstractMap.SimpleImmutableEntry<>(block, time));

					}

				}
			}
		}
	}

	//Water/Lava Place Handler
	@EventHandler
	public void onBucketEmpty(PlayerBucketEmptyEvent event) {

		Player player = event.getPlayer();
		Block block = event.getBlockClicked().getRelative(event.getBlockFace());
		Location loc = block.getLocation();
		Long time = System.currentTimeMillis();
		GameMode gameMode = player.getGameMode();

		if(Config.Configuration.getBoolean("Restore-Place.Ignore-Creative") && !(gameMode.equals(GameMode.CREATIVE))){ return; }

		boolean HasPermission = player.hasPermission("restoreblock.events.bucketempty");

		if (Config.Configuration.getBoolean("Restore-Place.Restore")) {

			if(!(Config.Configuration.getStringList("Restore-Place.Disabled-Worlds").contains(loc.getWorld().getName()))){

				if (!HasPermission) {

					RestoreOnRun.Place_list.add(new AbstractMap.SimpleImmutableEntry<>(block, time));

				}
			}
		}
	}

	//Block Burn Event Handler
	@EventHandler
	public void onBurn(BlockBurnEvent event) {

		Block block = event.getBlock();
		BlockState blockState = block.getState();
		Material material = blockState.getType();
		Long time = System.currentTimeMillis();
		Location loc = block.getLocation();

		if (Config.Configuration.getBoolean("Restore-Burn.Restore")) {

			if(!(Config.Configuration.getStringList("Restore-Burn.Disabled-Worlds").contains(loc.getWorld().getName()))){

				if(!(Config.Configuration.getList("Restore-Burn.Excluded-Materials").contains(material.toString()))){

					RestoreOnRun.Burn_list.add(new AbstractMap.SimpleImmutableEntry<>(blockState, time));

				}
			}
		}
	}

	//Block Explode Event Handler
	@EventHandler
	public void onExplode(EntityExplodeEvent event) {

		List<Block> blocks = event.blockList();
		Long time = System.currentTimeMillis();
		Location loc = event.getLocation();

		blocks.sort(Comparator.comparingInt(Block::getY));

		if (!(Config.Configuration.getBoolean("Restore-Explosion.Item-Drops"))) {

			event.setYield(0);

		}

		if (Config.Configuration.getBoolean("Restore-Explosion.Restore")) {

			if(!(Config.Configuration.getStringList("Restore-Explosion.Disabled-Worlds").contains(loc.getWorld().getName()))){

				for (Block b : blocks) {

					time = time + Config.Configuration.getLong("Restore-Explosion.Block-Delay");

					BlockState blockState = b.getState();
					Material material = blockState.getType();

					if(!(Config.Configuration.getList("Restore-Explosion.Excluded-Materials").contains(material.toString()))){

						RestoreOnRun.Explode_list.add(new AbstractMap.SimpleImmutableEntry<>(blockState, time));

					}
				}
			}
		}
	}
}
