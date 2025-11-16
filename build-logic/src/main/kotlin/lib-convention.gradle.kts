plugins {
    id("com.android.library")

    id("base-convention")
}

dependencies {
    implementation(libs.safeLibrary("kotlinx-coroutines-core"))
    implementation(libs.safeLibrary("napier"))
}