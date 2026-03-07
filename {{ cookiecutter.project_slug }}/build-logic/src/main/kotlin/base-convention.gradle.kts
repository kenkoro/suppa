import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePlugin

plugins {
    id("detekt-convention")
}

configure<BaseExtension> {
    buildFeatures.buildConfig = true

    compileSdkVersion(36)
    defaultConfig {
        minSdk = 24
        targetSdk = 36
    }
}

plugins.withType<KotlinBasePlugin> {
    configure<KotlinBaseExtension> {
        jvmToolchain(11)
    }
}