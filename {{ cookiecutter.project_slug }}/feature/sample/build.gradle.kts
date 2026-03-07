plugins {
    alias(libs.plugins.{{ cookiecutter.pkg_name }}.feature)
}

dependencies {
    implementation(projects.feature.utils)
    implementation(projects.i18n)
    implementation(projects.domain)
    implementation(projects.core)

    implementation(libs.moko.errors)
}