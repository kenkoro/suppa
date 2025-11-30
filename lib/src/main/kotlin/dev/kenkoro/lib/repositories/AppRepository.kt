package dev.kenkoro.lib.repositories

import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

internal class AppRepository @Inject constructor() : BaseRepository {
    override suspend fun fetch(): String {
        delay(1.seconds)
        return "Hello, suppa!"
    }
}
