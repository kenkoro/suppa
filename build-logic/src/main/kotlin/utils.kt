import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType

internal val Project.libs: VersionCatalog
    get() = this.extensions.getByType<VersionCatalogsExtension>().named("libs")

/** Get a library from the `libs.versions.toml` by specifying its alias. */
internal fun VersionCatalog.safeLibrary(alias: String): Provider<MinimalExternalModuleDependency> {
    try {
        return this.findLibrary(alias).get()
    } catch (e: Exception) {
        throw GradleException("Library $alias not found in libs.versions.toml", e.cause)
    }
}

/** Get a feature package name */
internal fun Project.getFeatureNamespace(): String {
    val packageName: String = name.replace("-", ".")
    return "dev.kenkoro.lib.feature.$packageName"
}