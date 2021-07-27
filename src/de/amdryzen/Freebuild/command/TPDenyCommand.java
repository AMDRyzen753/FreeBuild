package de.amdryzen.Freebuild.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class TPDenyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§7[§6TBK-FreeBuild§7]  Dieser Befehl ist nur für Spieler.");
            return true;
        }
        Player player = (Player) sender;
        if(args.length == 1) {
            String name = args[0];
            if(Bukkit.getPlayer(name) == null) {
                player.sendMessage("§7[§6TBK-FreeBuild§7] Der Spieler §6" + name + "§7 ist nicht online.");
                return true;
            }
            Player target = Bukkit.getPlayer(name);
            if(!(TPACommand.tpas.containsKey(player.getUniqueId()))) {
                player.sendMessage("§7[§6TBK-FreeBuild§7] Du hast keine Anfrage von &6" + name + "§7 erhalten.");
                return true;
            }
            HashMap <UUID, Long> tmp = TPACommand.tpas.get(player.getUniqueId());
            if(!(tmp.containsKey(target.getUniqueId()))) {
                player.sendMessage("§7[§6TBK-FreeBuild§7] Du hast keine Anfrage von §6" + name + "§7 erhalten.");
                return true;
            }
            long time = tmp.get(target.getUniqueId());
            if((System.currentTimeMillis() - time) > 120000) {
                tmp.remove(target.getUniqueId());
                TPACommand.tpas.replace(player.getUniqueId(), tmp);
                player.sendMessage("§7[§6TBK-FreeBuild§7] Die Anfrage von §6" + target.getName() + "§7 ist abgelaufen.");
                return true;
            }
            tmp.remove(target.getUniqueId());
            TPACommand.tpas.replace(player.getUniqueId(), tmp);
            target.sendMessage("§7[§6TBK-FreeBuild§7] Der Spieler §6" + player.getName() + "§7 hat deine Anfrage abgelehnt.");
            player.sendMessage("§7[§6TBK-FreeBuild§7] Du hast die Anfrage von §6" + name + "§7 abgelehnt.");
            return true;
        }
        player.sendMessage("§7[§6TBK-FreeBuild§7] Verwendung: /tpdeny [§6Name§7]");
        return true;
    }

}
