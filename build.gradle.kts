plugins {
    kotlin("jvm") version "1.6.10"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        maven(url = "https://papermc.io/repo/repository/maven-public/")
    }

    dependencies {
        implementation(kotlin("stdlib"))

        compileOnly("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT")
        compileOnly("io.github.monun:tap-api:${project.properties["tapVersion"].toString()}")
        compileOnly("io.github.monun:kommand-api:${project.properties["kommandVersion"].toString()}")
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    }
}

// Setup Plugin Modules (https://github.com/monun/paper-sample/blob/master/build.gradle.kts#L55-L76)
tasks {
    register<DefaultTask>("setupModules") {
        doLast {
            val defaultPrefix = "sample"
            val projectPrefix = rootProject.name

            if (defaultPrefix != projectPrefix) {
                fun rename(suffix: String) {
                    val from = "$defaultPrefix-$suffix"
                    val to = "$projectPrefix-$suffix"
                    file(from).takeIf { it.exists() }?.renameTo(file(to))
                }

                rename("api")
                rename("core")
                rename("plugin")
            }
        }
    }
}