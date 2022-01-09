package io.github.dytroInc.sample.paper

import net.kyori.adventure.text.Component.text
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

object PaperListener : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        event.joinMessage(text("대충 인간이 들어오는 소리"))
    }
}