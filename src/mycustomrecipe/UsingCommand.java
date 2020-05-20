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
						+ "] " + ChatColor.WHITE + "�����Ǹ� �ٽ� �ҷ��Խ��ϴ�!");
				return true;
			} else if (args[0].equals("list")) {
				sender.sendMessage("��fMyCustomRecipe ��aAvailable List ::");
				for (String temp : Recipe.getmyCustomDisplayItem())
					sender.sendMessage("    " + temp);
				return true;
			}
		}
		return false;
	}

	public static void getHelp(Player user) {
		user.sendMessage(ChatColor.WHITE + "------------------------------" + "\n" + ChatColor.DARK_AQUA + "["
				+ ChatColor.AQUA + "MyCustomRecipe" + ChatColor.DARK_AQUA + "]" + ChatColor.WHITE + " ��ɾ� ����" + "\n"
				+ ChatColor.YELLOW + "    /recipe" + ChatColor.WHITE + " :: ������ ��Ÿ���ϴ�" + "\n" + ChatColor.YELLOW
				+ "    /recipe reload" + ChatColor.WHITE + " :: recipe.txt ������ ���ε��մϴ�" + "\n" + ChatColor.YELLOW + "    /recipe list "
				+ ChatColor.WHITE + " :: ����� Ŀ���Ҿ������� Ȯ���մϴ�" + "\n" + ChatColor.WHITE
				+ "------------------------------\n");
	}
}
