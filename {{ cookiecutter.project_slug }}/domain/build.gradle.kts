plugins {
    alias(libs.plugins.{{ cookiecutter.pkg_name }}.lib)
}

android.namespace = "{{ cookiecutter.pkg_name }}.domain"

dependencies {
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}