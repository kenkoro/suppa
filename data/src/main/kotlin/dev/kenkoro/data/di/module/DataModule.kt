package dev.kenkoro.data.di.module

import dagger.Module

@Module(
    includes = [
        FeaturesModule::class,
        ApiModule::class,
        ExceptionHandlerModule::class,
        PreferenceModule::class,
        SupabaseModule::class,
    ]
)
interface DataModule
