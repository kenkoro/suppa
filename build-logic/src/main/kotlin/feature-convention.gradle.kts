plugins {
    id("lib-convention")
}

android {
    buildFeatures.viewBinding = true

    namespace = getFeatureNamespace()
}

dependencies {
    implementation(libs.safeLibrary("androidx-navigation-fragment-ktx"))
    implementation(libs.safeLibrary("androidx-navigation-ui-ktx"))

    implementation(libs.safeLibrary("dagger"))
    implementation(libs.safeLibrary("dagger-android"))
    ksp(libs.safeLibrary("dagger-compiler"))
    ksp(libs.safeLibrary("dagger-android-processor"))
}