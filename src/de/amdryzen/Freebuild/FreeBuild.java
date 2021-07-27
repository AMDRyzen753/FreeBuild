package de.amdryzen.Freebuild;

import de.amdryzen.Freebuild.command.*;
import de.amdryzen.Freebuild.events.JoinQuitListener;
import de.amdryzen.Freebuild.events.JoinVanishListener;
import de.amdryzen.Freebuild.events.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class FreeBuild extends JavaPlugin {

    public void onEnable() {
        System.out.println("Das FreeBuild System wurde geladen");

        getCommand("home").setExecutor(new HomeCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("tpa").setExecutor(new TPACommand());
        getCommand("tpaccept").setExecutor(new TPAccept());
        getCommand("tpdeny").setExecutor(new TPDenyCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("v").setExecutor(new VanishCommand());

        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinVanishListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }
}
