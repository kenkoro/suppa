package dev.kenkoro.lib.repositories

import javax.inject.Inject

internal class AppRepository @Inject constructor() : BaseRepository {
    override suspend fun get() = Unit

    override suspend fun get(name: String) = Unit
}
