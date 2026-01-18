package {{ cookiecutter.pkg_name }}.app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import {{ cookiecutter.pkg_name }}.lib.di.modules.LibModule
import {{ cookiecutter.pkg_name }}.lib.di.qualifiers.AppContext
import {{ cookiecutter.pkg_name }}.lib.di.qualifiers.BaseUrl
import {{ cookiecutter.pkg_name }}.lib.di.qualifiers.SupabaseKey
import {{ cookiecutter.pkg_name }}.lib.di.qualifiers.SupabaseUrl
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
