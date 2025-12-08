plugins {
    alias(libs.plugins.dev.kenkoro.lib)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.sqldelight)
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
    api(platform(libs.supabase.bom))
    api(libs.supabase.postgrest)

    implementation(libs.dagger)
    api(libs.dagger.android)
    api(libs.dagger.android.support)
    ksp(libs.dagger.compiler)
    ksp(libs.dagger.android.processor)

    api(libs.moko.errors)
    api(libs.moko.units)
    implementation(libs.moko.network.errors)
    implementation(libs.sqldelight.androidDriver)
    implementation(libs.sqldelight.coroutinesExtensions)
    api(libs.androidx.security.crypto)
    implementation(libs.multiplatformSettings)
}

sqldelight {
    databases {
        create("SampleDatabase") {
            packageName = "dev.kenkoro.lib.sample"
        }
    }
}