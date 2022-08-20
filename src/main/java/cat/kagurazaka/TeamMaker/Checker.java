package cat.kagurazaka.TeamMaker;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.ItemStack;

public final class Checker {
    public static boolean isCauserOP(Player p) {
        if(p.hasPermission("teammaker.maketeam")) {
            return true;
        }
        return false;
    }

    public static boolean isLegalDamageCauser(EntityEvent e) {
        if((e.getEntity() instanceof Player) && (e instanceof EntityDamageByEntityEvent)) {
            if(((EntityDamageByEntityEvent) e).getDamager() instanceof Player) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLegalItem(ItemStack item) {
        if ((item.getType() == Material.STICK) && (item.getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) && (item.getItemMeta().getEnchantLevel(Enchantment.SILK_TOUCH) == 1)) {
            return true;
        }
        return false;
    }
}
