plugins {
    alias(libs.plugins.{{ cookiecutter.pkg_name }}.lib)
    alias(libs.plugins.google.devtools.ksp)
}

android {
    buildFeatures.viewBinding = true

    namespace = "{{ cookiecutter.pkg_name }}.feature.utils"
}

dependencies {
    implementation(libs.moko.resources)

    implementation(libs.dagger)
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support)
    ksp(libs.dagger.compiler)
    ksp(libs.dagger.android.processor)
}