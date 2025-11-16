plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.google.devtools.ksp).apply(false)
    alias(libs.plugins.jetbrains.kotlin.serialization).apply(false)
    alias(libs.plugins.gitlab.arturbosch.detekt).apply(false)
    alias(libs.plugins.icerock.mobile.multiplatformResources).apply(false)
    alias(libs.plugins.jetbrains.kotlin.multiplatform).apply(false)
}