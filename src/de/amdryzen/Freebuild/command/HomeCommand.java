package de.amdryzen.Freebuild.command;

import de.amdryzen.Freebuild.FreeBuild;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        FileConfiguration configuration = FreeBuild.getPlugin(FreeBuild.class).getConfig();

        if (configuration.contains(((Player) sender).getUniqueId().toString())) {
            ((Player) sender).teleport((Location) configuration.get(((Player) sender).getUniqueId().toString()));

        } else {
            sender.sendMessage("§7[§6TBK-FreeBuild§7] Du musst erst ein Home Punkt setzten");
        }


        return true;
    }
}