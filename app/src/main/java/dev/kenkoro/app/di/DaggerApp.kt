package dev.kenkoro.app.di

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dev.kenkoro.app.BuildConfig
import javax.inject.Inject

internal open class DaggerApp : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<in Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .inject(this)
    }
}
