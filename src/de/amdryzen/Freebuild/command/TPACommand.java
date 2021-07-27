package de.amdryzen.Freebuild.command;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public class TPACommand implements CommandExecutor {

    public static HashMap <UUID, HashMap <UUID, Long>> tpas = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
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

            if(tpas.containsKey(target.getUniqueId())) {
                if(tpas.get(target.getUniqueId()).containsKey(player.getUniqueId())) {
                    player.sendMessage("§7[§6TBK-FreeBuild§7] Der Spieler §6" + name + "§7 hat bereits eine Anfrage von dir.");
                    return true;
                }
                HashMap <UUID, Long> tmp = new HashMap<>(tpas.get(target.getUniqueId()));
                tmp.put(player.getUniqueId(), System.currentTimeMillis());
                tpas.replace(target.getUniqueId(), tmp);
                player.sendMessage("§7[§6TBK-FreeBuild§7] Anfrage an §6" + name + "§7 erfolgreich gesendet.");
                target.sendMessage("§7[§6TBK-FreeBuild§7] Du hast eine Teleportanfrage von §6" + player.getName() + "§7 erhalten.");
                TextComponent accept = new TextComponent("§7[§aAnnehmen§7] ");
                accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Anfrage §aannehmen").create()));
                accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept " + player.getName()));
                TextComponent deny = new TextComponent("§7[§cAblehnen§7] ");
                deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Anfrage §cablehnen").create()));
                deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny " + player.getName()));
                accept.addExtra(deny);
                target.spigot().sendMessage(accept);
                return true;
            }

            HashMap <UUID, Long> tmp = new HashMap<>();
            tmp.put(player.getUniqueId(), System.currentTimeMillis());
            tpas.put(target.getUniqueId(), tmp);
            player.sendMessage("§7[§6TBK-FreeBuild§7] Anfrage an §6" + name + "§7 erfolgreich gesendet.");
            target.sendMessage("§7[§6TBK-FreeBuild§7] §7Du hast eine Teleportanfrage von §6" + player.getName() + "§7 erhalten.");
            TextComponent accept = new TextComponent("§7[§aAnnehmen§7] ");
            //accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Anfrage §aannehmen").create()));
            accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept " + player.getName()));
            TextComponent deny = new TextComponent("§7[§cAblehnen§7] ");
            //deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Anfrage §cablehnen").create()));
            deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny " + player.getName()));
            accept.addExtra(deny);
            target.spigot().sendMessage(accept);
            return true;
        }

        player.sendMessage("§7[§6TBK-FreeBuild§7] Verwendung: /tpa [§6Name§7]");

        return true;
    }
}
