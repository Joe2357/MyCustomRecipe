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

	private static Main myMain;

	public Recipe(Main inst) {
		myMain = inst;
		fileCheck();
	}

	private static List<ShapelessRecipe> shapelessRecipe = new ArrayList<ShapelessRecipe>();
	private static List<ItemStack> myCustomItem = new ArrayList<ItemStack>();
	private static List<Material> myDividedItem = new ArrayList<Material>();
	private static List<String> myNameSpacedKey = new ArrayList<String>();
	private static List<String> myCustomNameItem = new ArrayList<String>();
	private static List<String> myCustomDisplayItem = new ArrayList<String>();

	public void fileCheck() {
		new File("./plugins/MyCustomRecipe").mkdir();
		File file = new File("./plugins/MyCustomRecipe/recipe.txt");
		if (!file.exists()) { // 텍스트 파일이 없다면 생성하는 방법
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}

	public static void clearAllRecipe() {
		myMain.getServer().resetRecipes();
		shapelessRecipe.clear();
		myCustomItem.clear();
		myDividedItem.clear();
		myNameSpacedKey.clear();
		myCustomNameItem.clear();
		myCustomDisplayItem.clear();
	}

	public static void getMyCustomRecipe() {
		clearAllRecipe();
		Bukkit.getConsoleSender()
				.sendMessage("    " + ChatColor.ITALIC + "MyCustomRecipe " + ChatColor.YELLOW + "Available List :: ");
		List<String> loreString = new ArrayList<String>();

		String fileName = "./plugins/MyCustomRecipe/recipe.txt";
		BufferedReader recipeSearch = null;
		try {
			recipeSearch = new BufferedReader(new FileReader(fileName));
			String line;
			// 파일을 1줄씩 읽어 아이템 추가하기
			while ((line = recipeSearch.readLine()) != null) {
				if (!line.contains("name:"))
					continue;
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
					Material tempMaterial = Material
							.getMaterial(line.replace("\"", "").replace("\tmaterial:", "").trim());
					line = recipeSearch.readLine();
					Material tempResultMaterial = Material
							.getMaterial(line.replace("\"", "").replace("\tresultItem:", "").trim());
					getMyCustomItem().add(setItem(new ItemStack(tempResultMaterial, 1), tempDisplay, loreString));
					myDividedItem.add(tempMaterial);
					myNameSpacedKey.add(tempKey);
					myCustomNameItem.add(tempCustomName);
					myCustomDisplayItem.add(tempDisplay);
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
		for (int i = 0; i < getMyCustomItem().size(); i++) {
			shapelessRecipe.add(
					new ShapelessRecipe(new NamespacedKey(myMain, myNameSpacedKey.get(i)), getMyCustomItem().get(i))
							.addIngredient(9, myDividedItem.get(i)));
			Bukkit.getConsoleSender().sendMessage("    " + ChatColor.DARK_AQUA + "[" + ChatColor.WHITE
					+ myCustomNameItem.get(i) + ChatColor.DARK_AQUA + "]" + ChatColor.WHITE + " Availabled");
			myMain.getServer().addRecipe(shapelessRecipe.get(i));
		}
	}

	private static ItemStack setItem(ItemStack itemStack, String name, List<String> lore) {
		ItemMeta myItemMeta = itemStack.getItemMeta();
		myItemMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 0, true);
		myItemMeta.setDisplayName(name);
		myItemMeta.setLore(lore);
		itemStack.setItemMeta(myItemMeta);
		return itemStack;
	}

	public static List<ItemStack> getMyCustomItem() {
		return myCustomItem;
	}

	public static List<String> getmyCustomDisplayItem() {
		return myCustomDisplayItem;
	}

	public static List<Material> getMyDivideItem() {
		return myDividedItem;
	}
}
