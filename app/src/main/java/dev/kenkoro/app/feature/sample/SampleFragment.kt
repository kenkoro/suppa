package dev.kenkoro.app.feature.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.kenkoro.app.databinding.SampleFragmentBinding
import dev.kenkoro.app.di.NoParamsFragment
import dev.kenkoro.app.feature.sample.nav.SampleNavigable
import dev.kenkoro.app.utils.Destination
import dev.kenkoro.lib.feature.sample.presentation.SampleViewModel
import dev.kenkoro.utils.bindings.bind

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
        findNavController().navigate(resId = Destination.AssistedSample.actionId)
    }
}
