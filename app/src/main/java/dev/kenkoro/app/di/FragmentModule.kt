package dev.kenkoro.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.kenkoro.app.feature.sample.AssistedSampleFragment
import dev.kenkoro.app.feature.sample.SampleFragment
import dev.kenkoro.lib.feature.sample.di.scopes.AssistedSampleScope
import dev.kenkoro.lib.feature.sample.di.scopes.SampleScope

@Module
interface FragmentModule {
    @SampleScope
    @ContributesAndroidInjector
    fun contributeSampleFragmentAndroidInjector(): SampleFragment

    @AssistedSampleScope
    @ContributesAndroidInjector
    fun contributeAssistedSampleFragmentAndroidInjector(): AssistedSampleFragment
}
