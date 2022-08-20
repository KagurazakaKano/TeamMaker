package cat.kagurazaka.TeamMaker;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeamMaker extends JavaPlugin {

    public static TeamMaker plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        plugin.getServer().getPluginManager().registerEvents(new Events(), plugin);
        plugin.getCommand("tmake").setExecutor(new TeamOptions());
        plugin.getServer().getLogger().info("Plugin has registered successfully!");

    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(plugin);
        plugin.getServer().getLogger().info("Plugin has shut down successfully!");
    }
}
