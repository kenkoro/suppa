enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io") }
    }
}

rootProject.name = "{{ cookiecutter.project_slug }}"

includeBuild("build-logic")

include(":app")
include(":i18n")
include(":core")
include(":uikit")
include(":domain")
include(":data")
include(":feature")
include(":feature:utils")
include(":feature:sample")
include(":feature:assistedSample")