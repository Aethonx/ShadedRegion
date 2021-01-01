package io.shaded.task

import io.shaded.manager.PlayerRegionManager
import io.shaded.util.WorldGuardUtil
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

object PlayerLocationTask : BukkitRunnable() {

    override fun run() {
        for (player in Bukkit.getOnlinePlayers()) {

            if (!PlayerRegionManager.isWithinRegion(player) && WorldGuardUtil.isWithinRegion(player.location)) {
                PlayerRegionManager.hidePlayer(player)
            } else if (!WorldGuardUtil.isWithinRegion(player.location) && PlayerRegionManager.isWithinRegion(player)) {
                PlayerRegionManager.showPlayer(player)
            }

        }
    }

}