package com.lubek.lobbycontrol.cmds;

import com.lubek.lobbycontrol.files.config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix_lc = ChatColor.BLUE +"[Lobby Control] ";
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix_lc+"only players can use this command");
            return true;
        }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("reloadlc")) {
            config.reload();
        }


        return true;
    }
}
