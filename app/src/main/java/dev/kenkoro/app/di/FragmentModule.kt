package dev.kenkoro.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.kenkoro.app.feature.sample.AssistedSampleFragment
import dev.kenkoro.app.feature.sample.SampleFragment

@Module
internal interface FragmentModule {
    @ContributesAndroidInjector
    fun contributeSampleFragmentAndroidInjector(): SampleFragment

    @ContributesAndroidInjector
    fun contributeAssistedSampleFragmentAndroidInjector(): AssistedSampleFragment
}
