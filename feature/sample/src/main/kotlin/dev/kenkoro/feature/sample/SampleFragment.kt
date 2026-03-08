package dev.kenkoro.feature.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import dev.kenkoro.core.binding.bind
import dev.kenkoro.feature.sample.databinding.SampleFragmentBinding
import dev.kenkoro.feature.sample.nav.SampleNavigable
import dev.kenkoro.feature.utils.di.component.NoParamsFragment
import dev.kenkoro.feature.utils.nav.DeepLinkRegistry

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
            .fromUri(DeepLinkRegistry.Request(assistedSampleKey).uri)
            .build()
        findNavController().navigate(assistedSample)
    }

    override val assistedSampleKey = "assisted-sample"
}
