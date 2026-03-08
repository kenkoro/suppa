package {{ cookiecutter.pkg_name }}.feature.di.module

import dagger.Module
import {{ cookiecutter.pkg_name }}.feature.sample.di.SampleModule
import {{ cookiecutter.pkg_name }}.feature.assistedSample.di.AssistedSampleModule

@Module(
    includes = [
        SampleModule::class,
        AssistedSampleModule::class,
    ]
)
interface FeaturesModule
