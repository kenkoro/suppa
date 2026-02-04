package dev.kenkoro.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.kenkoro.app.MainActivity
import dev.kenkoro.lib.di.modules.LibModule
import dev.kenkoro.lib.di.scopes.MainScope

@Module
interface ActivityModule {
    @MainScope
    @ContributesAndroidInjector(modules = [LibModule::class, FragmentModule::class])
    fun contributeMainActivityAndroidInjector(): MainActivity
}
