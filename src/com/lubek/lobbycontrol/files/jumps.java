package com.lubek.lobbycontrol.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class jumps {
    private static File file;
    private static FileConfiguration customFile;
    //finds or generates the custom config file
    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("LobbyControl").getDataFolder(), "jumps.yml");
        if(!file.exists()){
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                //owww
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return customFile;
    }

    public static void save() {
        try {
            customFile.save(file);
        }
        catch (IOException e) {
            System.out.println("Couldnt save file");
        }
    }

    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
