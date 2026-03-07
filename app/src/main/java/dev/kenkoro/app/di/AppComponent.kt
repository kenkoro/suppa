package dev.kenkoro.app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dev.kenkoro.app.di.component.DaggerApp
import dev.kenkoro.data.di.AppScope
import dev.kenkoro.data.di.module.DataModule
import dev.kenkoro.data.di.qualifier.AppContext
import dev.kenkoro.data.di.qualifier.BaseUrl
import dev.kenkoro.data.di.qualifier.SupabaseKey
import dev.kenkoro.data.di.qualifier.SupabaseUrl

@AppScope
@Component(
    modules = [
        HostModule::class,
        DataModule::class,
        AndroidInjectionModule::class,
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun baseUrl(@BaseUrl baseUrl: String): Builder

        @BindsInstance
        fun supabaseUrl(@SupabaseUrl supabaseUrl: String): Builder

        @BindsInstance
        fun supabaseKey(@SupabaseKey supabaseKey: String): Builder

        @BindsInstance
        fun appContext(@AppContext context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(application: DaggerApp)
}
