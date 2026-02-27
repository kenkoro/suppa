plugins {
    alias(libs.plugins.dev.kenkoro.feature)
}

dependencies {
    implementation(projects.feature.utils)
    implementation(projects.i18n)

    implementation(libs.moko.errors)
}