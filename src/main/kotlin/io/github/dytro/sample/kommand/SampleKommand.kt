package io.github.dytro.sample.kommand

import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component.text
import org.bukkit.plugin.Plugin

object SampleKommand {
    fun register(plugin: Plugin) {
        plugin.kommand {
            register("sample") {
                executes {
                    feedback(text("sample"))
                }
            }
        }
    }
}