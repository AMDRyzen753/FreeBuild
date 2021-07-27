package de.amdryzen.Freebuild.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§7[§6TBK-FreeBuild§7] Dieser Befehl ist nur für Spieler.");
            return true;
        }
        Player player = (Player) sender;
        if(!(player.hasPermission("FreeBuild.fly"))) {
            player.setAllowFlight(false);
            player.sendMessage("§7[§6TBK-FreeBuild§7] §7Dazu hast du keine Rechte");
            return true;
        }
        if(player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.sendMessage("§7[§6TBK-FreeBuild§7] §7Flugmodus wurde deaktiviert.");
            return true;
        }
        player.setAllowFlight(true);
        player.sendMessage("§7[§6TBK-FreeBuild§7] §7Flugmodus wurde aktiviert.");
        return true;
    }
}
