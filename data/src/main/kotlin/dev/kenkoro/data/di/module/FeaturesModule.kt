package dev.kenkoro.data.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dev.kenkoro.data.di.ViewModelFactory
import dev.kenkoro.data.sample.di.SampleModule

@Module(
    includes = [
        SampleModule::class,
    ]
)
interface FeaturesModule {
    @Binds
    fun bindViewModelFactory(implementation: ViewModelFactory): ViewModelProvider.Factory
}
