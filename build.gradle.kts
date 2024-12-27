plugins {
    kotlin("jvm") version "2.0.0"
    java
    id("maven-publish")

    alias(libs.plugins.loom)
    alias(libs.plugins.loom.legacy)
}

group = property("maven_group")!!
version = property("mod_version")!!

val minecraft_version by properties
val yarn_build by properties
val loader_version by properties

repositories {
    mavenCentral()
}

loom {

}

legacyLooming {

}

dependencies {
    "minecraft" ("com.mojang:minecraft:${minecraft_version}")
    "mappings"("net.legacyfabric:yarn:1.8.9+build.$yarn_build:v2")
    "modImplementation" ("net.fabricmc:fabric-loader:${loader_version}")

    //"modImplementation" ("net.legacyfabric.legacy-fabric-api:legacy-fabric-api:${fabric}")
}

tasks.processResources {
    inputs.property("version", version)

    filesMatching("fabric.mod.json") {
        expand(mapOf("version" to version))
    }
}

tasks.jar {

    from("LICENSE") {
        rename {
            "${it}_${archiveBaseName.get()}"
        }
    }
}

kotlin {
    jvmToolchain(17)
}