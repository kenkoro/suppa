plugins {
    alias(libs.plugins.dev.kenkoro.feature)
}

dependencies {
    implementation(projects.lib.utils)

    implementation(libs.moko.errors)
}