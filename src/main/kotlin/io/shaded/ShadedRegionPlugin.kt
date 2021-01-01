package io.shaded

import io.shaded.config.Serializer
import io.shaded.config.impl.RegionConfig
import io.shaded.listener.PlayerListener
import io.shaded.manager.PlayerRegionManager
import io.shaded.task.PlayerLocationTask
import io.shaded.util.WorldGuardUtil
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class ShadedRegionPlugin : JavaPlugin() {

    override fun onEnable() {
        if (!WorldGuardUtil.isWorldGuardLoaded()) {
            println("[+] Please install world guard")
            server.pluginManager.disablePlugin(this)
            return
        }

        dataFolder.mkdirs()
        instance = this
        configSerializer = Serializer()

        RegionConfig.load()
        RegionConfig.save()

        Bukkit.getScheduler().runTaskTimer(
            this, PlayerLocationTask, 0, 20
        )

        Bukkit.getPluginManager().registerEvents(PlayerListener, this)
    }

    override fun onDisable() {
        for (uid in PlayerRegionManager.players) {
            PlayerRegionManager.showPlayer(Bukkit.getPlayer(uid))
        }

        configSerializer = null
        instance = null
    }

    companion object {
        @JvmStatic
        var instance: ShadedRegionPlugin? = null
            private set

        @JvmStatic
        var configSerializer: Serializer? = null
            private set
    }

}