import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugin.use.PluginDependency

internal val Project.libs: VersionCatalog
    get() = this.extensions.getByType<VersionCatalogsExtension>().named("libs")

/** Get a plugin from the `libs.versions.toml` by specifying its alias. */
internal fun VersionCatalog.safePlugin(alias: String): Provider<PluginDependency> {
    try {
        return this.findPlugin(alias).get()
    } catch (e: Exception) {
        throw GradleException("Plugin $alias not found in libs.versions.toml", e.cause)
    }
}