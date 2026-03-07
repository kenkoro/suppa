package dev.kenkoro.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.kenkoro.app.MainActivity
import dev.kenkoro.data.di.ActivityScope
import dev.kenkoro.feature.di.module.FeaturesModule

@Module
interface HostModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [FeaturesModule::class])
    fun contributeMainActivityAndroidInjector(): MainActivity
}
