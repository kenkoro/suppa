import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType

internal val Project.libs: VersionCatalog
    get() = this.extensions.getByType<VersionCatalogsExtension>().named("libs")

/**
 * Get a library from the `libs.versions.toml` by specifying its alias.
 */
internal fun VersionCatalog.getLibrary(
    alias: String,
): Provider<MinimalExternalModuleDependency> {
    return this.findLibrary(alias)
        .orElseThrow {
            GradleException("Library $alias wasn't found in libs.versions.toml")
        }
}

/**
 * Get a version from the `libs.versions.toml` by specifying its alias.
 */
internal fun VersionCatalog.getVersion(alias: String): String {
    return this.findVersion(alias)
        .orElseThrow {
            GradleException("Version alias $alias wasn't found in libs.versions.toml")
        }
        .requiredVersion
}

/**
 * Get a bundle from the `libs.versions.toml` by specifying its alias.
 */
internal fun VersionCatalog.getBundle(alias: String): Provider<ExternalModuleDependencyBundle> {
    return this.findBundle(alias)
        .orElseThrow {
            GradleException("Bundle alias $alias wasn't found in libs.versions.toml")
        }
}

/**
 * Get a feature package name.
 */
internal fun Project.getFeatureNamespace(): String {
    val packageName: String = name.replace("-", ".")
    return "dev.kenkoro.feature.$packageName"
}
