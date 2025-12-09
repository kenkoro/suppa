package {{ cookiecutter.pkg_name }}.lib.di.modules

import dagger.Module

@Module(
    includes = [
        FeatureModule::class,
        ApiModule::class,
        RepositoryModule::class,
        SupabaseModule::class,
        ExceptionHandlerModule::class,
        SqlDelightModule::class,
        PlatformModule::class,
        StorageModule::class,
    ]
)
interface LibModule
