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
}