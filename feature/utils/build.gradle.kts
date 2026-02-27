plugins {
    alias(libs.plugins.dev.kenkoro.lib)
    alias(libs.plugins.google.devtools.ksp)
}

android {
    buildFeatures.viewBinding = true

    namespace = "dev.kenkoro.feature.utils"
}

dependencies {
    implementation(libs.moko.resources)

    api(projects.utils)

    implementation(libs.dagger)
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support)
    ksp(libs.dagger.compiler)
    ksp(libs.dagger.android.processor)
}