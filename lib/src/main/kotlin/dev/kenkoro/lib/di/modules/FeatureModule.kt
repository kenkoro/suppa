package dev.kenkoro.lib.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dev.kenkoro.lib.di.ViewModelFactory
import dev.kenkoro.lib.feature.sample.di.FeatureSampleModule

@Module(
    includes = [
        FeatureSampleModule::class,
    ]
)
internal interface FeatureModule {
    @Binds
    fun bindViewModelFactory(implementation: ViewModelFactory): ViewModelProvider.Factory
}