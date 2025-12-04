package dev.kenkoro.lib

import dev.kenkoro.lib.utils.logout.LogoutHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class ChannelLogoutHandler : LogoutHandler {
    private val logoutEventsChannel = Channel<Unit>()

    override val logoutEvents: Flow<Unit>
        get() = logoutEventsChannel.receiveAsFlow()

    override suspend fun onLogout() {
        logoutEventsChannel.send(Unit)
    }
}
