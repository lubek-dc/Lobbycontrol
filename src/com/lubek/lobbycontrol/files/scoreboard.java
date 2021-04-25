package com.lubek.lobbycontrol.files;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.List;

public class scoreboard {

    public static void resetscoreboard(Player player) {
        List<String> scoreboardworlds = config.get().getStringList("ScoreboardWorld");
        for(int i = 0; i < scoreboardworlds.size(); i++) {
            if (player.getWorld().getName().equals(scoreboardworlds.get(i))) {

                jumps.get().addDefault(player.getName(), 0);
                jumps.get().options().copyDefaults(true);
                jumps.save();
                ScoreboardManager m = Bukkit.getScoreboardManager();
                Scoreboard b = m.getNewScoreboard();
                Objective o = b.registerNewObjective("Gold", "");
                o.setDisplaySlot(DisplaySlot.SIDEBAR);
                o.setDisplayName(ChatColor.GREEN + "[LUBEK SERVER]");
                Score Przywitanie = o.getScore("Witaj Drogi graczu");
                Score plr = o.getScore("Nazwa: " + player.getName());

                Score diamond = o.getScore("Skoków: " + com.lubek.lobbycontrol.files.jumps.get().getInt(player.getName()));
                Przywitanie.setScore(9);
                plr.setScore(8);
                diamond.setScore(7);

                player.setScoreboard(b);
            }
        }
    }
}
