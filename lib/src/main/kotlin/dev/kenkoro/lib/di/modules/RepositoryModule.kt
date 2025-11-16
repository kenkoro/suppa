package dev.kenkoro.lib.di.modules

import dagger.Binds
import dagger.Module
import dev.kenkoro.lib.repositories.AppRepository
import dev.kenkoro.lib.repositories.BaseRepository
import javax.inject.Singleton

@Module
internal interface RepositoryModule {
    @Singleton
    @Binds
    fun bindBaseRepository(implementation: AppRepository): BaseRepository
}