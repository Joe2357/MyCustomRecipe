package mycustomrecipe;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UsingCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (s.equals("recipe")) {
			if (args.length == 0) {
				getHelp((Player) sender);
				return true;
			} else if (args[0].equals("reload")) {
				Recipe.getMyCustomRecipe();
				sender.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "MyCustomRecipe" + ChatColor.DARK_AQUA
						+ "] " + ChatColor.WHITE + "레시피를 다시 불러왔습니다!");
				return true;
			} else if (args[0].equals("list")) {
				sender.sendMessage("§fMyCustomRecipe §aAvailable List ::");
				for (String temp : Recipe.getmyCustomDisplayItem())
					sender.sendMessage("    " + temp);
				return true;
			}
		}
		return false;
	}

	public static void getHelp(Player user) {
		user.sendMessage(ChatColor.WHITE + "------------------------------" + "\n" + ChatColor.DARK_AQUA + "["
				+ ChatColor.AQUA + "MyCustomRecipe" + ChatColor.DARK_AQUA + "]" + ChatColor.WHITE + " 명령어 도움말" + "\n"
				+ ChatColor.YELLOW + "    /recipe" + ChatColor.WHITE + " :: 도움말을 나타냅니다" + "\n" + ChatColor.YELLOW
				+ "    /recipe reload" + ChatColor.WHITE + " :: recipe.txt 파일을 리로드합니다" + "\n" + ChatColor.YELLOW
				+ "    /recipe list " + ChatColor.WHITE + " :: 적용된 커스텀아이템을 확인합니다" + "\n" + ChatColor.WHITE
				+ "------------------------------\n");
	}
}
