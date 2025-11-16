plugins {
    id("com.android.application")
    id("kotlin-android")

    id("base-convention")
}

android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}