package dev.kenkoro.feature.di.module

import dagger.Module
import dev.kenkoro.feature.assistedSample.di.AssistedSampleModule
import dev.kenkoro.feature.sample.di.SampleModule

@Module(
    includes = [
        SampleModule::class,
        AssistedSampleModule::class,
    ]
)
interface FeaturesModule
