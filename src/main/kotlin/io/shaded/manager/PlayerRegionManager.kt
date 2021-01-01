package io.shaded.manager


import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer

import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.HashSet

object PlayerRegionManager {

    val players = HashSet<UUID>()

    fun isWithinRegion(player: Player): Boolean {
        return players.contains(player.uniqueId)
    }

    fun hidePlayer(player: Player) {
        if (!player.hasPermission("shaded.regions.bypass")) return

        players.add(player.uniqueId)
        val packet = PacketPlayOutEntityDestroy(player.entityId)
        for (user in Bukkit.getOnlinePlayers()) {
            if (user.uniqueId != player.uniqueId) {
                (user as CraftPlayer).handle.playerConnection.sendPacket(packet)
            }
        }
    }

    fun showPlayer(player: Player) {
        players.remove(player.uniqueId)
        val packet = PacketPlayOutNamedEntitySpawn((player as CraftPlayer).handle)

        for (user in Bukkit.getOnlinePlayers()) {
            if (user.uniqueId != player.uniqueId) {
                (user as CraftPlayer).handle.playerConnection.sendPacket(packet)
            }
        }
    }

}