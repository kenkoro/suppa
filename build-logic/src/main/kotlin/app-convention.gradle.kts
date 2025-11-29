plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.devtools.ksp")

    id("base-convention")
}

android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}