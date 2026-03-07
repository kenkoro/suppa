plugins {
    alias(libs.plugins.{{ cookiecutter.pkg_name }}.feature)
}

dependencies {
    implementation(projects.feature.utils)
    implementation(projects.i18n)

    implementation(libs.moko.errors)
}