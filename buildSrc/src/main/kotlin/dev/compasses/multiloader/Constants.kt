package dev.compasses.multiloader

import org.gradle.jvm.toolchain.JavaLanguageVersion

object Constants {
    const val GROUP = "dev.compasses.shorkie"
    const val MOD_ID = "shorkie"
    const val MOD_NAME = "Shorkie"
    const val MOD_VERSION = "2101.1.0.2"
    const val LICENSE = "MIT"
    const val DESCRIPTION = """
        Adds the infamous IKEA BLÅHAJ Soft toy shark to minecraft along with:
          - A Gray shark plushie.
          - A whale plushie.
          - A bread pillow plushie.
          - A various collection of Pride shark plushies.
    
        Thank you to all the previous contributors who made this port possible:
          - hibi: Original Author
          - infoplayerstart: Swedish Translator
          - DaFuqs: Previous Maintainer
          - MusicalSkele: Artist / Code Contributor
          - Roll54: Ukrainian Translator
          - HerrChaos: Code Contributor
          - SoundEffectsFactory: Plushie squeaking noises.
    """

    const val HOMEPAGE = "https://www.curseforge.com/minecraft/mc-mods/shorkie"
    const val ISSUE_TRACKER = "https://github.com/Ellie-McQuinn/shorkie/issues"
    const val SOURCES_URL = "https://github.com/Ellie-McQuinn/shorkie"

    @Suppress("RedundantNullableReturnType")
    val curseforgeProperties: CurseForgeProperties? = object : CurseForgeProperties() {
        override val projectId = "1283248"
        override val projectSlug = "shorkie"
    }

    @Suppress("RedundantNullableReturnType")
    val modrinthProperties: ModrinthProperties? = object : ModrinthProperties() {
        override val projectId: String = "wj0EvN9T"
    }

    const val PUBLISH_WEBHOOK_VARIABLE = "PUBLISH_WEBHOOK"

    const val COMPARE_URL = "https://github.com/Ellie-McQuinn/shorkie/compare/"

    // Quinn Semele, Ellie Rose, HerrChaos, MusicalSkele, DaFuqs, hibi
    // ME, ME, Code Contributor, Code / Art, Code, Original Owner
    val CONTRIBUTORS = linkedMapOf(
        "Ellie McQuinn / Toybox System" to "Maintainer"
    )

    val CREDITS = linkedMapOf<String, String>(

    )

    val EXTRA_MOD_INFO_REPLACEMENTS = mapOf<String, String>(

    )

    val JAVA_VERSION = JavaLanguageVersion.of(21)
    const val JETBRAIN_ANNOTATIONS_VERSION = "24.1.0"
    const val FINDBUGS_VERSION = "3.0.2"

    const val MIXIN_VERSION = "0.8.5"
    const val MIXIN_EXTRAS_VERSION = "0.3.5"

    const val MINECRAFT_VERSION = "1.21"
    const val FL_MINECRAFT_CONSTRAINT = ">=1.21- <1.22"
    const val NF_MINECRAFT_CONSTRAINT = "[1.21, 1.22)"
    val SUPPORTED_MINECRAFT_VERSIONS = listOf(MINECRAFT_VERSION, "1.21.1")

    // https://parchmentmc.org/docs/getting-started#choose-a-version/
    const val PARCHMENT_MINECRAFT = "1.21"
    const val PARCHMENT_RELEASE = "2024.07.07"

    // https://fabricmc.net/develop/
    const val FABRIC_API_VERSION = "0.102.0+1.21"
    const val FABRIC_LOADER_VERSION = "0.15.11"

    const val NEOFORM_VERSION = "1.21-20240613.152323" // // https://projects.neoforged.net/neoforged/neoform/
    const val NEOFORGE_VERSION = "21.0.143" // https://projects.neoforged.net/neoforged/neoforge/
    const val FML_CONSTRAINT = "[4,)" // https://projects.neoforged.net/neoforged/fancymodloader/
}