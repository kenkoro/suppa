package dev.kenkoro.feature.repositories

import dev.kenkoro.feature.di.scopes.MainScope
import javax.inject.Inject

@MainScope
class AppRepository @Inject constructor() : FeatureRepository {
    override suspend fun get() = Unit

    override suspend fun get(name: String) = Unit
}
