plugins {
    alias(libs.plugins.dev.kenkoro.android.app)
}

android {
    buildFeatures.viewBinding = true

    namespace = "dev.kenkoro.app"
    defaultConfig {
        applicationId = "dev.kenkoro.app"
        versionCode = (project.property("VERSION_CODE") as String).toInt()
        versionName = project.property("VERSION_NAME") as String

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.material)
    implementation(libs.bundles.androidx)
    implementation(projects.lib)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}