package dev.kenkoro.app.di.component

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dev.kenkoro.app.BuildConfig
import dev.kenkoro.app.di.DaggerAppComponent
import javax.inject.Inject

open class DaggerApp : Application(), HasAndroidInjector {
    @Inject
    lateinit var appInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<in Any> = appInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .baseUrl(BuildConfig.BASE_URL)
            .supabaseUrl(BuildConfig.SUPABASE_URL)
            .supabaseKey(BuildConfig.SUPABASE_KEY)
            .appContext(applicationContext)
            .build()
            .inject(this)
    }
}
