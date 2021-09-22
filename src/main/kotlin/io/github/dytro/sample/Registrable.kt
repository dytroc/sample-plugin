package io.github.dytro.sample

import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

interface Registrable {
    fun run(plugin: Plugin)
}
open class RegistrableListener : Registrable, Listener {
    override fun run(plugin: Plugin) {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }
}

fun Plugin.register(register: Registrable) {
    register.run(this)
}