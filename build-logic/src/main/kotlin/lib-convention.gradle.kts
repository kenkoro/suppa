plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")

    id("base-convention")
}

dependencies {
    implementation(libs.safeLibrary("kotlinx-coroutines-core"))
    implementation(libs.safeLibrary("napier"))
    implementation(libs.safeLibrary("androidx-viewmodel-ktx"))
    implementation(libs.safeLibrary("androidx-activity"))
    implementation(libs.safeLibrary("androidx-appcompat"))
    implementation(libs.safeLibrary("androidx-fragment-ktx"))

    testImplementation(libs.safeLibrary("kotlin-test"))
    testImplementation(libs.safeLibrary("kotlinx-coroutines-test"))
}