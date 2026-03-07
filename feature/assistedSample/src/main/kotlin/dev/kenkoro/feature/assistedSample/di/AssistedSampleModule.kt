package dev.kenkoro.feature.assistedSample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.kenkoro.feature.assistedSample.AssistedSampleFragment
import dev.kenkoro.feature.utils.di.FragmentScope

@Module
interface AssistedSampleModule {
    @FragmentScope
    @ContributesAndroidInjector
    fun contributeAssistedSampleFragmentAndroidInjector(): AssistedSampleFragment
}
