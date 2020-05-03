package mycustomrecipe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		enableMessage();
		Recipe myRecipe = new Recipe(this);
		myRecipe.getMyCustomRecipe();
		Divide myDivide = new Divide(this, myRecipe);
		myDivide.unused();
	}

	@Override
	public void onDisable() {
		disableMessage();
	}

	private void enableMessage() {
		PluginDescriptionFile pdFile = this.getDescription();
		Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "----------------------------------------");
		Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "MCMPS" + ChatColor.DARK_AQUA
				+ "]" + ChatColor.WHITE + " �÷������� Ȱ��ȭ�Ǿ����ϴ�.");
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "�÷����� �̸� : " + ChatColor.WHITE + pdFile.getName());
		Bukkit.getConsoleSender()
				.sendMessage(ChatColor.RED + "Version : " + ChatColor.WHITE + "v" + pdFile.getVersion());
		Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "----------------------------------------");
	}

	private void disableMessage() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "----------------------------------------");
		Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "MCMPS" + ChatColor.DARK_AQUA
				+ "]" + ChatColor.WHITE + " �÷������� ��Ȱ��ȭ�Ǿ����ϴ�.");
		Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "----------------------------------------");
	}
}