package mycustomrecipe;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Divide implements Listener {
	private final Main myMain;
	private final Recipe myRecipe;

	public Divide(Main inst1, Recipe inst2) {
		myMain = inst1;
		myRecipe = inst2;
		myMain.getServer().getPluginManager().registerEvents(this, myMain);
	}
	
	public void unused() {
		return;
	}

	@EventHandler
	public void rightClick1(PlayerInteractEvent event) {
		Player executor = event.getPlayer();
		Action action = event.getAction();
		ItemStack usingItem = executor.getInventory().getItemInMainHand();
		if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
			for (int i = 0; i < myRecipe.myCustomItem.size(); i++) {
				if (usingItem != null && usingItem.equals(myRecipe.myCustomItem.get(i))) {
					event.setCancelled(true);
					executor.getInventory().removeItem(usingItem);
					executor.getInventory().addItem(new ItemStack(myRecipe.myDividedItem.get(i), 9));
				}
			}
		}
	}
}
