plugins {
    alias(libs.plugins.dev.kenkoro.lib)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android.namespace = "dev.kenkoro.lib"

val projectModules = listOf(
    projects.lib.feature.sample,
    projects.lib.utils,
)

dependencies {
    projectModules.forEach(::api)

    api(projects.i18n)
    api(libs.bundles.network)

    implementation(libs.dagger)
    api(libs.dagger.android)
    implementation(libs.dagger.android.support)
    ksp(libs.dagger.compiler)
    ksp(libs.dagger.android.processor)
}