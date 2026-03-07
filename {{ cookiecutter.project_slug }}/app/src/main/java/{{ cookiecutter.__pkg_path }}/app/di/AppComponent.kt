package {{ cookiecutter.pkg_name }}.app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import {{ cookiecutter.pkg_name }}.app.di.component.DaggerApp
import {{ cookiecutter.pkg_name }}.data.di.AppScope
import {{ cookiecutter.pkg_name }}.data.di.module.DataModule
import {{ cookiecutter.pkg_name }}.data.di.qualifier.AppContext
import {{ cookiecutter.pkg_name }}.data.di.qualifier.BaseUrl
import {{ cookiecutter.pkg_name }}.data.di.qualifier.SupabaseKey
import {{ cookiecutter.pkg_name }}.data.di.qualifier.SupabaseUrl

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
