package dev.kenkoro.lib.di.modules

import dagger.Module
import dev.kenkoro.lib.feature.sample.di.FeatureSampleModule

// TODO: Do something with this
@Module(
    includes = [
        FeatureSampleModule::class,
    ]
)
internal interface FeatureModule {
//    @Binds
//    fun bindViewModelFactory(implementation: ViewModelFactory): ViewModelProvider.Factory
}
