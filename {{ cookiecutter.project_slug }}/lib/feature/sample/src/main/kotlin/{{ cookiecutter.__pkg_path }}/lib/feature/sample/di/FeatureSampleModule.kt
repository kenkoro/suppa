package {{ cookiecutter.pkg_name }}.lib.feature.sample.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import {{ cookiecutter.pkg_name }}.lib.feature.sample.presentation.SampleViewModel
import {{ cookiecutter.pkg_name }}.lib.utils.di.viewmodel.ViewModelKey

@Module
interface FeatureSampleModule {
    @Binds
    @IntoMap
    @ViewModelKey(SampleViewModel::class)
    fun bindSampleViewModel(implementation: SampleViewModel): ViewModel
}
