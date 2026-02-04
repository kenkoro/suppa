package dev.kenkoro.lib.repositories

import dev.kenkoro.lib.di.scopes.MainScope
import javax.inject.Inject

@MainScope
class AppRepository @Inject constructor() : FeatureRepository {
    override suspend fun get() = Unit

    override suspend fun get(name: String) = Unit
}
