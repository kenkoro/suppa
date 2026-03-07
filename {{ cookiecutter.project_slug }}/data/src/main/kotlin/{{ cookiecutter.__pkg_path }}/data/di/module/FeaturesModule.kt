package {{ cookiecutter.pkg_name }}.data.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import {{ cookiecutter.pkg_name }}.data.di.ViewModelFactory
import {{ cookiecutter.pkg_name }}.data.sample.di.SampleModule

@Module(
    includes = [
        SampleModule::class,
    ]
)
interface FeaturesModule {
    @Binds
    fun bindViewModelFactory(implementation: ViewModelFactory): ViewModelProvider.Factory
}
