package mycustomrecipe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
	List<String> myNameSpacedKey = new ArrayList<String>();
	List<String> myCustomNameItem = new ArrayList<String>();

	public void fileCheck() {
		File dir = new File("./plugins/MyCustomRecipe");
		dir.mkdir();
		File file = new File("./plugins/MyCustomRecipe/recipe.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}

	public void getMyCustomRecipe() {
		Bukkit.getConsoleSender()
				.sendMessage("    " + ChatColor.ITALIC + "MyCustomRecipe " + ChatColor.YELLOW + "Available List :: ");
		List<String> loreString = new ArrayList<String>();

		String fileName = "./plugins/MyCustomRecipe/recipe.txt";
		BufferedReader recipeSearch = null;
		try {
			recipeSearch = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = recipeSearch.readLine()) != null) {
				// 파일을 1줄씩 읽어 아이템 추가하기
				String tempKey = line.replace("\"", "").replace("name:", "").trim();
				line = recipeSearch.readLine();
				String tempCustomName = line.replace("\"", "").replace("customName:", "").trim();
				line = recipeSearch.readLine();
				String tempDisplay = line.replace("\"", "").replace("display:", "").trim();
				line = recipeSearch.readLine();
				if (line.trim().equals("lore:")) {
					line = recipeSearch.readLine();
					while (!line.contains("material:")) {
						loreString.add(line.replace("\"", "").trim());
						line = recipeSearch.readLine();
					}
					Material tempMaterial = Material.getMaterial(
							line.replace("\"", "").replace("\tmaterial:", "").trim());
					line = recipeSearch.readLine();
					Material tempResultMaterial = Material.getMaterial(
							line.replace("\"", "").replace("\tresultItem:", "").trim());
					myCustomItem.add(setItem(new ItemStack(tempResultMaterial, 1),
							tempDisplay, loreString));
					myDividedItem.add(tempMaterial);
					myNameSpacedKey.add(tempKey);
					myCustomNameItem.add(tempCustomName);
					loreString.clear();
				}
			}
		} catch (IOException ioe) {
			Bukkit.getConsoleSender().sendMessage(ioe.getMessage());
		} finally {
			try {
				if (recipeSearch != null)
					recipeSearch.close();
			} catch (Exception e) {
			}
		}

		// 아이템 서버에 추가
		for (int i = 0; i < myCustomItem.size(); i++) {
			shapelessRecipe.add(
					new ShapelessRecipe(new NamespacedKey(CustomRecipe, myNameSpacedKey.get(i)), myCustomItem.get(i))
							.addIngredient(9, myDividedItem.get(i)));
			Bukkit.getConsoleSender().sendMessage("    " + ChatColor.DARK_AQUA + "[" + ChatColor.WHITE
					+ myCustomNameItem.get(i) + ChatColor.DARK_AQUA + "]" + ChatColor.WHITE + " Availabled");
			CustomRecipe.getServer().addRecipe(shapelessRecipe.get(i));
		}
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
