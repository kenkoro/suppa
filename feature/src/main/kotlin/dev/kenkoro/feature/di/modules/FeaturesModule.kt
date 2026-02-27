package dev.kenkoro.feature.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dev.kenkoro.feature.assistedSample.di.FeatureAssistedSampleModule
import dev.kenkoro.feature.di.ViewModelFactory
import dev.kenkoro.feature.sample.di.FeatureSampleModule

@Module(
    includes = [
        FeatureSampleModule::class,
        FeatureAssistedSampleModule::class,
    ]
)
interface FeaturesModule {
    @Binds
    fun bindViewModelFactory(implementation: ViewModelFactory): ViewModelProvider.Factory
}
