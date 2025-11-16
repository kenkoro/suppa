plugins {
    alias(libs.plugins.dev.kenkoro.multiplatform.lib)
    alias(libs.plugins.icerock.mobile.multiplatformResources)
}

android.namespace = "dev.kenkoro.i18n"

multiplatformResources {
    resourcesPackage = "dev.kenkoro.i18n"
    resourcesClassName = "MR"
}