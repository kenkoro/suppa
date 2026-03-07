package {{ cookiecutter.pkg_name }}.data.sample.di

import dagger.Module

@Module(
    includes = [
        SampleRepositoryModule::class,
    ]
)
interface SampleModule
