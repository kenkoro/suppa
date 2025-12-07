package dev.kenkoro.app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dev.kenkoro.lib.di.modules.LibModule
import dev.kenkoro.lib.di.qualifiers.AppContext
import dev.kenkoro.lib.di.qualifiers.BaseUrl
import dev.kenkoro.lib.di.qualifiers.SupabaseKey
import dev.kenkoro.lib.di.qualifiers.SupabaseUrl
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityModule::class,
        FragmentModule::class,
        AndroidInjectionModule::class,
        LibModule::class,
    ]
)
internal interface AppComponent {
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
