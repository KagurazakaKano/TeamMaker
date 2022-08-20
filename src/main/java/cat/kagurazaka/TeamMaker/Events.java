package cat.kagurazaka.TeamMaker;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import static cat.kagurazaka.TeamMaker.Checker.*;
import static cat.kagurazaka.TeamMaker.TeamOptions.getTeamNumber;

/**
 * Purpose:
 *   1. Detect if is player hitting player
 *     - If so:
 *       Detect if using the right tool & having permission of OP
 *         - If so:
 *           Execute the command
 *           Set the damage to 0
 *     - If not:
 *       Do Nothing
 */

public final class Events implements Listener {
    @EventHandler
    public void onHitPlayer(EntityDamageEvent e) {
        if (isLegalDamageCauser(e)) {
            Player damageTaker = (Player) e.getEntity();
            Player damageCauser = (Player) ((EntityDamageByEntityEvent) e).getDamager();

            ItemStack itemInMainHand = damageCauser.getInventory().getItemInMainHand();
            if (isLegalItemForTeamMaking(itemInMainHand) && isCauserOP(damageCauser)) {
                damageCauser.performCommand("team join " +  getTeamNumber() + " " + damageTaker.getName());
                e.setDamage(0.0);
            }
            if (isLegalItemForTeamLeaving(itemInMainHand) && isCauserOP(damageCauser)) {
                damageCauser.performCommand("team leave " +  damageTaker.getName());
                e.setDamage(0.0);
            }
        }
    }
}
