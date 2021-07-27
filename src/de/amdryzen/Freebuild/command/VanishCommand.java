package de.amdryzen.Freebuild.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishCommand implements CommandExecutor {
    public static ArrayList<Player> v = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("v")) {
                if (player.hasPermission("FreeBuild.v")) {
                    if (v.contains(player)) {
                        v.remove(player);
                        player.sendMessage("§7[§6TBK-FreeBuild§7] §7Du bist nun nicht mehr im Vanish");
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.showPlayer(player);
                        }
                        Bukkit.broadcastMessage("§8[§a+§8]§7 " + player.getName());
                    } else {
                        v.add(player);
                        player.sendMessage("§7[§6TBK-FreeBuild§7] Du bist nun im Vanish");
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.hidePlayer(player);
                            if(!all.hasPermission("FreeBuild.v.see")) {
                                all.hidePlayer(player);
                            }
                        }

                        Bukkit.broadcastMessage("§8[§c-§8]§7 " + player.getName());

                    }
                } else {
                    player.sendMessage("§7[§6TBK-FreeBuild§7] Dazu hast du keine Rechte");
                }
            }
        }
        return false;
    }
}
