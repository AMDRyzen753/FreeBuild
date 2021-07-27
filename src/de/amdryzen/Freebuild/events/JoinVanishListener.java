package de.amdryzen.Freebuild.events;

import de.amdryzen.Freebuild.command.VanishCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinVanishListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        for (Player vanish : VanishCommand.v) {
            if(!(event.getPlayer().hasPermission("FreeBuild.v.see"))) {
                event.getPlayer().hidePlayer(vanish);
            }
        }
    }
}
