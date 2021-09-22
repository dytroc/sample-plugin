package io.github.dytro.sample

import io.github.dytro.sample.kommand.SampleKommand
import io.github.dytro.sample.listeners.SampleListener
import org.bukkit.plugin.java.JavaPlugin

class SamplePlugin : JavaPlugin() {
    override fun onEnable() {
        registerAll()
    }
    private fun registerAll() {
        register(SampleListener())
        register(SampleKommand())
    }
}