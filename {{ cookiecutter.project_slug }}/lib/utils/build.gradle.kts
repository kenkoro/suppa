plugins {
    alias(libs.plugins.{{ cookiecutter.pkg_name }}.lib)
    alias(libs.plugins.google.devtools.ksp)
}

android.namespace = "{{ cookiecutter.pkg_name }}.lib.utils"

dependencies {
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    implementation(libs.moko.resources)
}