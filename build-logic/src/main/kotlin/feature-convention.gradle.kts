plugins {
    id("lib-convention")
}

android.namespace = getFeatureNamespace()

dependencies {
    implementation(libs.safeLibrary("dagger"))
    ksp(libs.safeLibrary("dagger-compiler"))
}