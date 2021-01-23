package me.roopekoo.vanillacursefix;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class VanillaCurseFix extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("VanillaCurseFix starting up.");
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        System.out.println("VanillaCurseFix shut down.");
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        World world = player.getWorld();

        for (int i = 0; i < player.getInventory().getSize(); i++) {
            ItemStack item = player.getInventory().getItem(i);
            if (item == null) {
                continue;
            }
            if (item.containsEnchantment(Enchantment.VANISHING_CURSE)) {
                player.getInventory().clear(i);
                continue;
            }
            if ((i >= 36) && (i < 40)) {
                if (item.containsEnchantment(Enchantment.BINDING_CURSE)) {
                    player.getInventory().clear(i);
                    world.dropItem(player.getLocation(), item).setPickupDelay(40);
                }
            }
        }
    }
}