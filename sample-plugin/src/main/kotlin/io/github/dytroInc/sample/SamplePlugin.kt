package io.github.dytroInc.sample

import io.github.dytroInc.sample.paper.PaperListener
import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class SamplePlugin : JavaPlugin() {
    override fun onEnable() {
        registerListeners(
            PaperListener,
        )

        kommand {
            register("sample") {
                executes {
                    feedback(Component.text("sample"))
                }
            }
        }
    }

    private fun registerListeners(vararg listeners: Listener) = listeners.forEach { server.pluginManager.registerEvents(it, this) }
}