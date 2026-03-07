plugins {
    id("lib-convention")
}

android {
    buildFeatures.viewBinding = true

    namespace = getFeatureNamespace()
}

dependencies {
    implementation(libs.getLibrary("androidx-navigation-fragment-ktx"))
    implementation(libs.getLibrary("androidx-navigation-ui-ktx"))

    implementation(libs.getLibrary("dagger"))
    implementation(libs.getLibrary("dagger-android"))
    ksp(libs.getLibrary("dagger-compiler"))
    ksp(libs.getLibrary("dagger-android-processor"))
}