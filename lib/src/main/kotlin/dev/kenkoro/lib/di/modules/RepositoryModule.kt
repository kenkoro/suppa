package dev.kenkoro.lib.di.modules

import dagger.Binds
import dagger.Module
import dev.kenkoro.lib.feature.sample.model.SampleRepository
import dev.kenkoro.lib.repositories.AppRepository

@Module
internal interface RepositoryModule {
    @Binds
    fun bindSampleRepository(implementation: AppRepository): SampleRepository
}