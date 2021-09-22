package io.github.dytro.sample.listeners

import io.github.dytro.sample.RegistrableListener
import net.kyori.adventure.text.Component.text
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent

class SampleListener : RegistrableListener() {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        event.joinMessage(text("사람이당"))
    }
}