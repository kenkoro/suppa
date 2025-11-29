package dev.kenkoro.lib.feature.sample.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.kenkoro.lib.feature.sample.SampleViewModel
import dev.kenkoro.lib.utils.di.ViewModelKey

@Module
interface FeatureSampleModule {
    @Binds
    @IntoMap
    @ViewModelKey(SampleViewModel::class)
    fun bindSampleViewModel(implementation: SampleViewModel): ViewModel
}