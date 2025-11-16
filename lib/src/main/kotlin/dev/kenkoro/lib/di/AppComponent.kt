package dev.kenkoro.lib.di

import dagger.BindsInstance
import dagger.Component
import dev.kenkoro.lib.di.modules.ApiModule
import dev.kenkoro.lib.di.modules.RepositoryModule
import dev.kenkoro.lib.di.qualifiers.BaseUrl
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        RepositoryModule::class,
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun baseUrl(@BaseUrl baseUrl: String): Builder

        fun build(): AppComponent
    }
}