plugins {
    alias(libs.plugins.{{ cookiecutter.pkg_name }}.feature)
}

dependencies {
    implementation(projects.lib.utils)

    implementation(libs.moko.errors)
}