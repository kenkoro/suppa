package dev.kenkoro.data.sample.di

import dagger.Module

@Module(
    includes = [
        SampleRepositoryModule::class,
    ]
)
interface SampleModule
