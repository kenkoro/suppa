plugins {
    alias(libs.plugins.dev.kenkoro.feature)
}

dependencies {
    implementation(projects.feature.utils)
    implementation(projects.i18n)
    implementation(projects.domain)
    implementation(projects.core)

    implementation(libs.moko.errors)
}