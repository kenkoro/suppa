package dev.kenkoro.app.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dev.kenkoro.lib.di.modules.LibModule
import dev.kenkoro.lib.di.qualifiers.BaseUrl
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityModule::class,
        AndroidInjectionModule::class,
        LibModule::class,
    ]
)
internal interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun baseUrl(@BaseUrl baseUrl: String): Builder

        fun build(): AppComponent
    }

    fun inject(application: DaggerApp)
}
