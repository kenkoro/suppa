import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePlugin

configure<BaseExtension> {
    buildFeatures.buildConfig = true

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