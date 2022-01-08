val projectAPI = project(":${rootProject.name}-api")
val projectCore = project(":${rootProject.name}-core")

dependencies {
    implementation(projectAPI)
}

// Tasks + Extra Variables (https://github.com/monun/paper-sample/blob/master/build.gradle.kts#L28-L70)
project.extra.set("packageName", rootProject.name.replace("-", ""))

val pluginName = rootProject.name.split('-').joinToString("") { it.capitalize() }
project.extra.set("pluginName", pluginName)

tasks {
    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
            expand(extra.properties)
        }
    }

    register<Jar>("paperJar") {
        archiveBaseName.set(pluginName)
        archiveVersion.set("")

        listOf(projectAPI, projectCore, project).forEach { project ->
            from(project.sourceSets["main"].output)
        }

        doLast {
            copy {
                from(archiveFile)
                val plugins = File(rootDir, ".server/plugins/")
                into(if (File(plugins, archiveFileName.get()).exists()) File(plugins, "update") else plugins)
            }
        }
    }
}