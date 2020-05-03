package mycustomrecipe;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Recipe {
	private final Main CustomRecipe;
	public Recipe(Main instance) {
		CustomRecipe = instance;
	}
	
	List<ShapelessRecipe> shapelessRecipe = new ArrayList<ShapelessRecipe>();
	List<ItemStack> myCustomItem = new ArrayList<ItemStack>();
	List<Material> myDividedItem = new ArrayList<Material>();

	public void getMyCustomRecipe() {
		Bukkit.getConsoleSender()
				.sendMessage("    " + ChatColor.ITALIC + "MyCustomRecipe " + ChatColor.YELLOW + "Available List :: ");
		List<String> loreString = new ArrayList<String>();
		
		// �� ������ �߰�
		loreString.add("��f�� 9���� ������ִ� ������ ��");
		myCustomItem.add(setItem(new ItemStack(Material.STONE, 1), "��e����� ��7��", loreString));
		myDividedItem.add(Material.STONE);
		shapelessRecipe.add(new ShapelessRecipe(
				new NamespacedKey(CustomRecipe, "compressedstone"), myCustomItem.get(myCustomItem.size() - 1))
				.addIngredient(9, myDividedItem.get(myDividedItem.size() - 1)));
		Bukkit.getConsoleSender().sendMessage(
				"    " + ChatColor.DARK_AQUA + "[" + ChatColor.WHITE + "Compressed Stone" + ChatColor.DARK_AQUA + "]" + ChatColor.WHITE + " Availabled");
		loreString.clear();

		// ������ ������ �߰�
		for (int i = 0; i < shapelessRecipe.size(); i++)
			CustomRecipe.getServer().addRecipe(shapelessRecipe.get(i));
	}

	private ItemStack setItem(ItemStack itemStack, String name, List<String> lore) {
		ItemMeta myItemMeta = itemStack.getItemMeta();
		myItemMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 0, true);
		myItemMeta.setDisplayName(name);
		myItemMeta.setLore(lore);
		itemStack.setItemMeta(myItemMeta);
		return itemStack;
	}
}
