package mycustomrecipe;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CommandTabCompletion implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if (alias.equals("recipe")) {
			if (args.length == 1) {
				List<String> myList = new ArrayList<String>();
				myList.add("reload");
				myList.add("list");
				return myList;
			}
		}
		return null;
	}
}
