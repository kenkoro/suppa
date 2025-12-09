package {{ cookiecutter.pkg_name }}.lib.feature.sample.di

import dagger.assisted.AssistedFactory
import {{ cookiecutter.pkg_name }}.lib.feature.sample.presentation.AssistedSampleViewModel
import {{ cookiecutter.pkg_name }}.lib.feature.sample.presentation.AssistedSampleViewModel.Params
import {{ cookiecutter.pkg_name }}.lib.utils.di.viewmodel.Factory

@AssistedFactory
interface AssistedSampleViewModelFactory : Factory<Params, AssistedSampleViewModel>
