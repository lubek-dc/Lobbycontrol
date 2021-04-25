package com.lubek.lobbycontrol.events;

import com.lubek.lobbycontrol.files.config;
import com.lubek.lobbycontrol.files.jumps;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;
import com.lubek.lobbycontrol.files.scoreboard;
import java.util.List;

public class lobbycontrolevents implements Listener {
    @EventHandler
    public static void OnPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        jumps.get().addDefault(player.getName(),0);
        jumps.get().options().copyDefaults(true);
        jumps.save();
        scoreboard.resetscoreboard(player);
    }
    @EventHandler
    public static void onWorldChange(PlayerChangedWorldEvent event) {
        scoreboard.resetscoreboard(event.getPlayer());
    }
    @EventHandler
    public static void onPlayerWalk(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.JUMP, 25, 3);
        List<String> whitelist = config.get().getStringList("Whitelist");
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        Material block = player.getWorld().getBlockAt(x,y-1,z).getType();
        if(block.equals(Material.BLACK_WOOL)) {
            for(int i = 0; i < whitelist.size(); i++) {
                if (player.getWorld().getName().equals(whitelist.get(i))) {

                    player.addPotionEffect(potionEffect);
                } else {
                    if(config.get().getBoolean("Debug")) {
                        player.sendMessage("Else world index: "+i);
                    }

                }
            }
        }
        if(player.getWorld().getBlockAt(x,y-2,z).getType().equals(Material.BLACK_WOOL)&& !player.isOnGround() && config.get().getBoolean("Particles")) {
            player.getWorld().spawnParticle(Particle.TOTEM,x,y,z,5);
            jumps.get().set(player.getName(),jumps.get().getInt(player.getName())+1);
            jumps.get().options().copyDefaults(true);
            jumps.save();
            scoreboard.resetscoreboard(player);
        }

    }

}
