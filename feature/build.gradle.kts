plugins {
    alias(libs.plugins.dev.kenkoro.lib)
}

android.namespace = "dev.kenkoro.feature"

val projectModules = listOf(
    projects.feature.sample,
    projects.feature.assistedSample,
    projects.feature.utils,
)

dependencies {
    projectModules.forEach(::api)
    api(projects.i18n)
    api(projects.domain)

    api(libs.moko.units)

    implementation(libs.dagger)
    api(libs.dagger.android)
    api(libs.dagger.android.support)
    ksp(libs.dagger.compiler)
    ksp(libs.dagger.android.processor)
}