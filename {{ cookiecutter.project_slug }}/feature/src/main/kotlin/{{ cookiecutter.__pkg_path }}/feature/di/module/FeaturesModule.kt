package {{ cookiecutter.pkg_name }}.feature.di.module

import dagger.Module
import {{ cookiecutter.pkg_name }}.feature.sample.di.SampleModule

@Module(
    includes = [
        SampleModule::class,
    ]
)
interface FeaturesModule
