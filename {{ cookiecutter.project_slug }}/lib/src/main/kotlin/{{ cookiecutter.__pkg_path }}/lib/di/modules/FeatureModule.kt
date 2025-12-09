package {{ cookiecutter.pkg_name }}.lib.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import {{ cookiecutter.pkg_name }}.lib.di.ViewModelFactory
import {{ cookiecutter.pkg_name }}.lib.feature.sample.di.FeatureSampleModule

@Module(
    includes = [
        FeatureSampleModule::class,
    ]
)
internal interface FeatureModule {
    @Binds
    fun bindViewModelFactory(implementation: ViewModelFactory): ViewModelProvider.Factory
}
