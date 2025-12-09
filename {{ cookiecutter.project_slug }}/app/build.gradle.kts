plugins {
    alias(libs.plugins.{{ cookiecutter.pkg_name }}.android.app)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.google.services)
}

android {
    buildFeatures.viewBinding = true

    namespace = "{{ cookiecutter.pkg_name }}.app"
    defaultConfig {
        applicationId = "{{ cookiecutter.pkg_name }}.app"
        versionCode = (project.property("VERSION_CODE") as String).toInt()
        versionName = project.property("VERSION_NAME") as String

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.material)
    implementation(libs.bundles.androidx)
    implementation(libs.bundles.moko.crashReporting)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.moko.units.viewBinding)

    implementation(projects.lib)
    implementation(projects.utils)
    implementation(projects.uikit)

    ksp(libs.dagger.compiler)
    ksp(libs.dagger.android.processor)
}