package {{ cookiecutter.pkg_name }}.lib.utils.logout

import kotlinx.coroutines.flow.Flow

/**
 * Handles application-wide logout events, providing a reactive flow to observe logout signals.
 */
interface LogoutHandler {
    val logoutEvents: Flow<Unit>

    suspend fun onLogout()
}
