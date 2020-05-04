package mycustomrecipe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UsingCommand implements CommandExecutor {
	private final Recipe myRecipe;

	public UsingCommand(Recipe inst) {
		myRecipe = inst;
	}

	public void unused() {
		return;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] strigs) {
		if (s.equals("recipeReload")) {
			myRecipe.getMyCustomRecipe();
		}
		return false;
	}
}