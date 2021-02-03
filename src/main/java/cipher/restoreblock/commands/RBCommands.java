package cipher.restoreblock.commands;

import cipher.restoreblock.utils.Info;
import cipher.restoreblock.ymlloader.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class RBCommands implements CommandExecutor, Listener {

	public String MainCommand = "RestoreBlock";
	private String SubCommand1 = "Help";
	private String SubCommand2 = "Reload";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase(MainCommand) && args.length == 0) {

			sender.sendMessage(Info.Color(Info.Prefix + "&cRestoreBlock &b" + Info.Version + " &cBy &b" + Info.Author));
			sender.sendMessage(Info.Color(Info.Prefix + "&b/RestoreBlock Help &8- &cFor More Details!"));

			return true;

		}

		if(args.length == 1 && args[0].equalsIgnoreCase(SubCommand1)) {

			sender.sendMessage(Info.Color("&8-------------------"));
			sender.sendMessage(Info.Color(Info.Prefix + "&b/RestoreBlock  &cor &b/RB"));
			sender.sendMessage(Info.Color(Info.Prefix + "&b/RestoreBlock Reload &8- &cTo Reload The Config File."));
			sender.sendMessage(Info.Color("&8-------------------"));

			return true;

		}


		if(args.length == 1 && args[0].equalsIgnoreCase(SubCommand2)) {

			if(sender.hasPermission("restoreblock.commands.reload")){

				Config.reloadConfig();
				sender.sendMessage(Info.Color(Info.Prefix + "&cReload Successful."));

				return true;

			} else {

				sender.sendMessage(Info.Color(Info.Prefix + "&cCould Not Initiate Reload, Reason : &bInsufficient Permission!"));

				return true;

			}
		}
		return false;
	}
}
