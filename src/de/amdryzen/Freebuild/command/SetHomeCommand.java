package de.amdryzen.Freebuild.command;

import de.amdryzen.Freebuild.FreeBuild;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Location location = ((Player) sender).getLocation();

       FileConfiguration configuration = FreeBuild.getPlugin(FreeBuild.class).getConfig();

       configuration.set(((Player) sender).getUniqueId().toString() , location);

       FreeBuild.getPlugin(FreeBuild.class).saveConfig();

       sender.sendMessage("§7[§6TBK-FreeBuild§7] Es wurde ein Home Punkt gesetzt");


        return false;
    }
}
