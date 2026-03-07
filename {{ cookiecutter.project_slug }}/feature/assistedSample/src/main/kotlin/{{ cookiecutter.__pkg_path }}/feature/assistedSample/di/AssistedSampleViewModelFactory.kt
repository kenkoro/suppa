package {{ cookiecutter.pkg_name }}.feature.assistedSample.di

import dagger.assisted.AssistedFactory
import {{ cookiecutter.pkg_name }}.feature.assistedSample.AssistedSampleViewModel
import {{ cookiecutter.pkg_name }}.feature.assistedSample.AssistedSampleViewModel.Params
import {{ cookiecutter.pkg_name }}.feature.utils.di.Factory

@AssistedFactory
interface AssistedSampleViewModelFactory : Factory<Params, AssistedSampleViewModel>
