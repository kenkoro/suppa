plugins {
    alias(libs.plugins.{{ cookiecutter.pkg_name }}.lib)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.sqldelight)
}

android.namespace = "{{ cookiecutter.pkg_name }}.data"

dependencies {
    implementation(projects.domain)

    api(libs.moko.errors)

    api(libs.bundles.network)
    implementation(libs.sqldelight.androidDriver)
    implementation(libs.sqldelight.coroutinesExtensions)
    implementation(libs.moko.network.errors)
    implementation(libs.multiplatformSettings)
    api(platform(libs.supabase.bom))
    api(libs.supabase.postgrest)

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}

sqldelight {
    databases {
        create("SampleDatabase") {
            packageName = "{{ cookiecutter.pkg_name }}.feature.sample"
        }
    }
}
