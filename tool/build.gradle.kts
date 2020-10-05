import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.spongepowered.configurate.build.format

plugins {
    application
    kotlin("jvm")
    id("org.spongepowered.configurate.build.component")
}

dependencies {
    // Configurate
    implementation(format("xml"))
    implementation(format("yaml"))
    implementation(format("gson"))
    implementation(format("hocon"))
    implementation(project(":extra:extra-kotlin"))

    // Libraries
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.github.ajalt:clikt:2.8.0")
    implementation("org.fusesource.jansi:jansi:1.18")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs = listOf("-Xnew-inference")
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Specification-Version" to project.version,
                "Implementation-Version" to project.version
            )
        )
    }
}

application {
    mainClassName = "org.spongepowered.configurate.tool.ToolKt"
}
