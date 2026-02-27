package dev.kenkoro.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.kenkoro.app.MainActivity
import dev.kenkoro.feature.di.modules.LibModule
import dev.kenkoro.feature.di.scopes.MainScope

@Module
interface ActivityModule {
    @MainScope
    @ContributesAndroidInjector(modules = [LibModule::class])
    fun contributeMainActivityAndroidInjector(): MainActivity
}
