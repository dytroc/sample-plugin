package io.github.dytro.sample

import io.github.dytro.sample.kommand.SampleKommand
import io.github.dytro.sample.listeners.SampleListener
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class SamplePlugin : JavaPlugin() {
    override fun onEnable() {
        SampleKommand.register(this)
        registerListeners(
            SampleListener,
        )
    }

    private fun registerListeners(vararg listeners: Listener) = listeners.forEach { server.pluginManager.registerEvents(it, this) }
}