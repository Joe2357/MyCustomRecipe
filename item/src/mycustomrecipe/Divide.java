package mycustomrecipe;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Divide implements Listener {

	public Divide(Main inst) {
		Bukkit.getPluginManager().registerEvents(this, inst);
	}

	@EventHandler
	public void divideItem(PlayerInteractEvent event) {
		Player executor = event.getPlayer();
		Action action = event.getAction();
		if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
			ItemStack usingItem = executor.getInventory().getItemInMainHand().clone();
			usingItem.setAmount(1);
			List<ItemStack> myCustomItemList = Recipe.getMyCustomItem();
			List<Material> myDivideItemList = Recipe.getMyDivideItem();
			for (int i = 0; i < myCustomItemList.size(); i++) {
				if (usingItem != null && usingItem.equals(myCustomItemList.get(i))) {
					event.setCancelled(true);
					executor.getInventory().removeItem(usingItem);
					executor.getInventory().addItem(new ItemStack(myDivideItemList.get(i), 9));
				}
			}
		}
	}
}
