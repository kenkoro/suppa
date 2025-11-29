plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.devtools.ksp")

    id("base-convention")
}

android {
    flavorDimensions += listOf("server")
    defaultConfig.multiDexEnabled = true

    signingConfigs {
        create("release") {
            keyAlias = System.getenv("RELEASE_KEY_ALIAS")
            keyPassword = System.getenv("RELEASE_KEY_PASSWORD")
            storeFile = file("signing/release.jks")
            storePassword = System.getenv("RELEASE_STORE_PASSWORD")
        }
    }

    buildTypes {
        release {
            val releaseConfig = signingConfigs.getByName("release")
            signingConfig = when {
                releaseConfig.keyAlias != null -> releaseConfig
                System.getenv("CI") == null -> {
                    logger.warn("Debug signing was used for the release build!")
                    signingConfigs.getByName("debug")
                }

                else -> {
                    val message = "Release signing wasn't configured. Set RELEASE_KEY_ALIAS, " +
                            "RELEASE_KEY_PASSWORD, RELEASE_STORE_PASSWORD environment variables."
                    throw IllegalArgumentException(message)
                }
            }

            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
    }

    productFlavors {
        create("dev") {
            dimension = "server"
            applicationIdSuffix = ".dev"

            val baseUrl = "https://dev.localhost/"
            buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        }
        create("stage") {
            dimension = "server"
            applicationIdSuffix = ".stage"

            val baseUrl = "https://stage.localhost/"
            buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        }
        create("prod") {
            dimension = "server"
            applicationIdSuffix = ".prod"

            val baseUrl = "https://prod.localhost/"
            buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packaging {
        resources.excludes += "META-INF/*.kotlin_module"
        resources.excludes += "META-INF/AL2.0"
        resources.excludes += "META-INF/LGPL2.1"
    }
}