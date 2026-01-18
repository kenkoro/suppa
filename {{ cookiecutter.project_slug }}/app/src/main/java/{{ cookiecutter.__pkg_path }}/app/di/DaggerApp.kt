package {{ cookiecutter.pkg_name }}.app.di

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import {{ cookiecutter.pkg_name }}.app.BuildConfig
import javax.inject.Inject

open class DaggerApp : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<in Any> = androidInjector

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
