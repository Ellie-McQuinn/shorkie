import dev.compasses.multiloader.Constants
import dev.compasses.multiloader.task.ProcessJsonTask

plugins {
    id("multiloader-loader")
    id("fabric-loom")
}

evaluationDependsOn(":common")

dependencies {
    minecraft("com.mojang:minecraft:${Constants.MINECRAFT_VERSION}")

    @Suppress("UnstableApiUsage")
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${Constants.PARCHMENT_MINECRAFT}:${Constants.PARCHMENT_RELEASE}@zip")
    })
}

fabricApi {
    configureDataGeneration {
        modId = Constants.MOD_ID
        outputDirectory = file("src/generated/resources")
    }
}

loom {
    val accessWidener = project(":common").file("src/main/resources/${Constants.MOD_ID}.accesswidener")
    if (accessWidener.exists()) {
        accessWidenerPath = accessWidener
    }

    @Suppress("UnstableApiUsage")
    mixin {
        defaultRefmapName = "${Constants.MOD_ID}.refmap.json"
        useLegacyMixinAp = false
    }

    runs {
        named("client") {
            client()

            configName = "Fabric Client"
            isIdeConfigGenerated = true
        }

        named("server") {
            server()

            configName = "Fabric Server"
            isIdeConfigGenerated = true
        }

        named("datagen") {
            configName = "Fabric Data"
            isIdeConfigGenerated = true
        }
    }
}

tasks.remapJar.configure {
    archiveClassifier = "fat"
}

tasks.processResources {
    exclude("META-INF/accesstransformer.cfg")
}

tasks.register("processJson", ProcessJsonTask::class) {
    group = "multiloader"
    dependsOn(tasks.remapJar)
    input.set(tasks.remapJar.get().outputs.files.singleFile)
    archiveClassifier = ""
}

tasks.build {
    dependsOn("processJson")
}

configurations.configureEach configureConfiguration@ {
    if (name == "modRuntimeClasspathMainMapped") {
        dependencies.configureEach {
            if (name == "fabric-loader" && group == "net.fabricmc") {
                this@configureConfiguration.exclude(group = group, module = name)
            } else if (name == "quilt-loader" && group == "org.quiltmc") {
                this@configureConfiguration.exclude(group = group, module = name)
            }
        }
    }
}