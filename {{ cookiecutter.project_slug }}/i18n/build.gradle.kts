plugins {
    alias(libs.plugins.{{ cookiecutter.pkg_name }}.multiplatform.lib)
    alias(libs.plugins.icerock.mobile.multiplatformResources)
}

android.namespace = "{{ cookiecutter.pkg_name }}.i18n"

multiplatformResources {
    resourcesPackage = "{{ cookiecutter.pkg_name }}.i18n"
    resourcesClassName = "MR"
}