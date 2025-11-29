package dev.kenkoro.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.kenkoro.app.MainActivity

@Module
internal interface ActivityModule {
    @ContributesAndroidInjector
    fun contributeMainActivityAndroidInjector(): MainActivity
}