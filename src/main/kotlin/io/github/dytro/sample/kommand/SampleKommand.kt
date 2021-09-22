package io.github.dytro.sample.kommand

import io.github.dytro.sample.Registrable
import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component.text
import org.bukkit.plugin.Plugin

class SampleKommand : Registrable {
    override fun run(plugin: Plugin) {
        plugin.kommand {
            register("sample") {
                executes {
                    feedback(text("sample"))
                }
            }
        }
    }
}