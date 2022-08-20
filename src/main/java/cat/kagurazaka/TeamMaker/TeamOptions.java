package cat.kagurazaka.TeamMaker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

import static cat.kagurazaka.TeamMaker.TeamMaker.plugin;

/**
 * Purpose: Use command to control the team to make
 */

public class TeamOptions {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.toString().equalsIgnoreCase("tm")) {
            if(sender instanceof Player && args.length == 1) {
                File configFile = new File(plugin.getDataFolder(), "config.yml");
                YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
                config.set("team-to-make", args[0]);
                try {
                    config.save(configFile);
                } catch (IOException e) {
                    plugin.getServer().getLogger().warning("The configuration file does not exist!");
                    sender.sendMessage("Internal Error! The configuration file does not exist!");
                    return true;
                }
            } else {
                sender.sendMessage("You have to be Player to execute that command!");
                return true;
            }
        }
        return false;
    }

    public static String getTeamNumber() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        return config.getString("team-to-make");
    }
}
