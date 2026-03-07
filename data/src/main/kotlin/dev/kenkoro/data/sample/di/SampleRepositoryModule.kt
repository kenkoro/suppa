package dev.kenkoro.data.sample.di

import dagger.Binds
import dagger.Module
import dev.kenkoro.data.sample.repository.SampleRepositoryImpl
import dev.kenkoro.domain.sample.repository.SampleRepository

@Module
interface SampleRepositoryModule {
    @Binds
    fun bindSampleRepository(implementation: SampleRepositoryImpl): SampleRepository
}
