package io.shaded.listener

import io.shaded.ShadedRegionPlugin
import io.shaded.manager.PlayerRegionManager
import io.shaded.util.WorldGuardUtil
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

object PlayerListener : Listener {

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        Bukkit.getScheduler().runTask(ShadedRegionPlugin.instance) {
            for (uid in PlayerRegionManager.players) {
                PlayerRegionManager.hidePlayer(Bukkit.getPlayer(uid))
            }

            if (WorldGuardUtil.isWithinRegion(event.player.location)) {
                PlayerRegionManager.hidePlayer(event.player)
            }
        }
    }


    @EventHandler
    fun onPlayerLeaveEvent(event: PlayerQuitEvent) {
        if (PlayerRegionManager.isWithinRegion(event.player)) {
            PlayerRegionManager.players.remove(event.player.uniqueId)
        }
    }

}