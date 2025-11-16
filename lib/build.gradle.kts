plugins {
    alias(libs.plugins.dev.kenkoro.lib)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.kotlin.android)
}

android.namespace = "dev.kenkoro.lib"

dependencies {
    api(projects.i18n)
    api(libs.bundles.network)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}