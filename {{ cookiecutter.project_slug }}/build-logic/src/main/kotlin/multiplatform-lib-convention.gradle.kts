import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")

    id("base-convention")
}

kotlin {
    androidTarget()

    sourceSets {
        applyDefaultHierarchyTemplate()
    }
}

dependencies {
    commonMainApi(libs.safeLibrary("moko-resources"))
}

tasks.withType<KotlinCompile>() {
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}