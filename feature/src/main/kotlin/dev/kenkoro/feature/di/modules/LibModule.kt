package dev.kenkoro.feature.di.modules

import dagger.Module

@Module(
    includes = [
        FeaturesModule::class,
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
