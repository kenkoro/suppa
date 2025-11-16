package dev.kenkoro.lib.repositories

import dev.kenkoro.lib.api.DummyJsonApi
import javax.inject.Inject

internal class AppRepository @Inject constructor(
    private val dummyJsonApi: DummyJsonApi,
) : BaseRepository {
    suspend fun json() {
        dummyJsonApi.json()
    }

    override fun logout() {
        TODO("Not yet implemented")
    }
}