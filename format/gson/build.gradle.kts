import org.spongepowered.configurate.build.core

plugins {
    id("org.spongepowered.configurate-component")
}

dependencies {
    api(core())
    // version must be kept in sync with MC's version
    implementation("com.google.code.gson:gson:2.8.0")
    testImplementation("com.google.guava:guava:29.0-jre")
}
