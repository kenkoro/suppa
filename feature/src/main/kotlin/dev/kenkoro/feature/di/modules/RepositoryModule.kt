package dev.kenkoro.feature.di.modules

import dagger.Binds
import dagger.Module
import dev.kenkoro.feature.repositories.AppRepository
import dev.kenkoro.feature.sample.model.SampleRepository

@Module
interface RepositoryModule {
    @Binds
    fun bindSampleRepository(implementation: AppRepository): SampleRepository
}
