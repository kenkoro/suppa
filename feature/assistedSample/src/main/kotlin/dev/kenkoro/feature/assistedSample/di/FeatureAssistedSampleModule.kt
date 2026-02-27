package dev.kenkoro.feature.assistedSample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.kenkoro.feature.assistedSample.AssistedSampleFragment
import dev.kenkoro.feature.assistedSample.di.scopes.AssistedSampleScope

@Module
interface FeatureAssistedSampleModule {
    @AssistedSampleScope
    @ContributesAndroidInjector
    fun contributeAssistedSampleFragmentAndroidInjector(): AssistedSampleFragment
}
