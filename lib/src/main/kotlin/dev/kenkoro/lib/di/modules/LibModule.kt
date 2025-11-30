package dev.kenkoro.lib.di.modules

import dagger.Module

@Module(
    includes = [
        FeatureModule::class,
        ApiModule::class,
        RepositoryModule::class,
    ]
)
interface LibModule
