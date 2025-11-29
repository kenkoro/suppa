plugins {
    alias(libs.plugins.dev.kenkoro.lib)
    alias(libs.plugins.google.devtools.ksp)
}

android.namespace = "dev.kenkoro.lib.utils"

dependencies {
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}