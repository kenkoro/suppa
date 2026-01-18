package {{ cookiecutter.pkg_name }}.app.feature.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import {{ cookiecutter.pkg_name }}.app.databinding.AssistedSampleFragmentBinding
import {{ cookiecutter.pkg_name }}.app.di.ParamsFragment
import {{ cookiecutter.pkg_name }}.lib.feature.sample.di.AssistedSampleViewModelFactory
import {{ cookiecutter.pkg_name }}.lib.feature.sample.presentation.AssistedSampleViewModel

class AssistedSampleFragment :
    ParamsFragment<AssistedSampleViewModelFactory, AssistedSampleFragmentBinding>() {
    private val viewModel: AssistedSampleViewModel by getViewModel {
        AssistedSampleViewModel.Params(name = "passed info")
    }

    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> AssistedSampleFragmentBinding
        get() = AssistedSampleFragmentBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onStart()
    }
}
