package dev.kenkoro.feature.sample.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dev.kenkoro.feature.sample.SampleFragment
import dev.kenkoro.feature.sample.SampleViewModel
import dev.kenkoro.feature.utils.di.FragmentScope
import dev.kenkoro.feature.utils.di.ViewModelKey

@Module
interface SampleModule {
    @FragmentScope
    @ContributesAndroidInjector
    fun contributeSampleFragmentAndroidInjector(): SampleFragment

    @Binds
    @IntoMap
    @ViewModelKey(SampleViewModel::class)
    fun bindSampleViewModel(implementation: SampleViewModel): ViewModel
}
