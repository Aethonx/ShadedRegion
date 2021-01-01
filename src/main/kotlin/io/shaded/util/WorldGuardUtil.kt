package io.shaded.util

import com.sk89q.worldguard.bukkit.WorldGuardPlugin
import io.shaded.config.impl.RegionConfig
import org.bukkit.Bukkit
import org.bukkit.Location

object WorldGuardUtil {

    @JvmStatic
    fun isWorldGuardLoaded(): Boolean {
        return Bukkit.getPluginManager().getPlugin("WorldGuard") != null
    }

    @JvmStatic
    fun isWithinRegion(location: Location): Boolean {

        val regions = WorldGuardPlugin.inst().globalRegionManager.get(location.world)?.getApplicableRegions(location)

        if (regions != null) {
            for (region in regions) {
                if (RegionConfig.instance.regions.contains(region.id.toLowerCase())) {
                    return true
                }
            }
        }

        return false
    }

}