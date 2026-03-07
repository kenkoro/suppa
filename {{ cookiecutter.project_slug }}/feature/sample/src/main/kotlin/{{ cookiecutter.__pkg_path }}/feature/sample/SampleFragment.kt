package {{ cookiecutter.pkg_name }}.feature.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import {{ cookiecutter.pkg_name }}.core.binding.bind
import {{ cookiecutter.pkg_name }}.feature.sample.databinding.SampleFragmentBinding
import {{ cookiecutter.pkg_name }}.feature.sample.nav.SampleNavigable
import {{ cookiecutter.pkg_name }}.feature.utils.di.component.NoParamsFragment

class SampleFragment : NoParamsFragment<SampleFragmentBinding>(), SampleNavigable {
    private val viewModel by getViewModel<SampleViewModel>()

    override val inflate: (LayoutInflater, ViewGroup?, Boolean) -> SampleFragmentBinding
        get() = SampleFragmentBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupExceptionHandler()
        setupListeners()
        setupCollectors()

        viewModel.onStart()
    }

    private fun setupExceptionHandler() {
        viewModel.exceptionHandler.bind(viewLifecycleOwner, requireActivity())
    }

    private fun setupListeners() {
        binding.centralTitle.setOnClickListener { viewModel.onCentralTitleClicked() }
    }

    private fun setupCollectors() {
        viewModel.actions.bind(viewLifecycleOwner) { action ->
            when (action) {
                SampleViewModel.Actions.RouteToAssistedSample -> navigateToAssistedSample()
            }
        }
    }

    override fun navigateToAssistedSample() {
        val assistedSample = NavDeepLinkRequest.Builder
            .fromUri("suppa://{{ cookiecutter.pkg_name }}/assisted-sample".toUri())
            .build()
        findNavController().navigate(assistedSample)
    }
}
