plugins {
    alias(libs.plugins.dev.kenkoro.lib)
}

android.namespace = "dev.kenkoro.domain"

dependencies {
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}