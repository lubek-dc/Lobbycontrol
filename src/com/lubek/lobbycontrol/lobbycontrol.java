package com.lubek.lobbycontrol;
import com.lubek.lobbycontrol.cmds.commands;
import com.lubek.lobbycontrol.events.lobbycontrolevents;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class lobbycontrol extends JavaPlugin {
    @Override
    public void onEnable() {
        com.lubek.lobbycontrol.stats.lcstats.setup();
        commands allcommands = new commands();
        getServer().getPluginManager().registerEvents(new lobbycontrolevents(), this);
        getCommand("reloadlc").setExecutor(allcommands);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"[Lobby Control]: Plugin started");
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        com.lubek.lobbycontrol.files.config.setup();
        com.lubek.lobbycontrol.files.config.get().options().copyDefaults(true);
        Plugin[] Plugins = getServer().getPluginManager().getPlugins();
        for(int i = 0; i < Plugins.length; i++) {
            if (Plugins[i].getName().equals("Sandbox")) {
                com.lubek.lobbycontrol.files.config.get().addDefault("SandboxWorld","World");
                com.lubek.lobbycontrol.files.config.get().addDefault("Sandbox-gold-X",86);
                com.lubek.lobbycontrol.files.config.get().addDefault("Sandbox-gold-Y",222);
                com.lubek.lobbycontrol.files.config.get().addDefault("Sandbox-gold-Z",000);
                com.lubek.lobbycontrol.files.config.get().addDefault("Sandbox-diamond-X",86);
                com.lubek.lobbycontrol.files.config.get().addDefault("Sandbox-diamond-Y",222);
                com.lubek.lobbycontrol.files.config.get().addDefault("Sandbox-diamond-Z",000);
            }
        }
        com.lubek.lobbycontrol.files.config.save();
        com.lubek.lobbycontrol.files.jumps.setup();
        com.lubek.lobbycontrol.files.jumps.get().options().copyDefaults(true);
        com.lubek.lobbycontrol.files.jumps.save();

    }
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED+"[Lobby Control]: Plugin disabled");
    }
}
