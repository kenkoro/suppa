plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")

    id("base-convention")
}

dependencies {
    implementation(libs.getLibrary("kotlinx-coroutines-core"))
    implementation(libs.getLibrary("napier"))
    implementation(libs.getLibrary("androidx-viewmodel-ktx"))
    implementation(libs.getLibrary("androidx-activity"))
    implementation(libs.getLibrary("androidx-appcompat"))
    implementation(libs.getLibrary("androidx-fragment-ktx"))

    testImplementation(libs.getLibrary("kotlin-test"))
    testImplementation(libs.getLibrary("kotlinx-coroutines-test"))
}