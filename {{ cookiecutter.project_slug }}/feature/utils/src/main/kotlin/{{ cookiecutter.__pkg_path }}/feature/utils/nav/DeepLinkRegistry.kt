package {{ cookiecutter.pkg_name }}.feature.utils.nav

import android.net.Uri
import androidx.core.net.toUri

sealed class DeepLinkRegistry {
    internal val suppaPrefix = "suppa://dev.kenkoro"

    data class Request(private val destination: String) : DeepLinkRegistry() {
        val uri: Uri
            get() = "$suppaPrefix/$destination".toUri()
    }
}
