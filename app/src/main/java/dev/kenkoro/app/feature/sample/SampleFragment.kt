package dev.kenkoro.app.feature.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.kenkoro.app.databinding.SampleFragmentBinding
import dev.kenkoro.app.di.NoParamsFragment
import dev.kenkoro.app.utils.Destination
import dev.kenkoro.lib.feature.sample.presentation.SampleViewModel
import dev.kenkoro.utils.flow.binding.bind

internal class SampleFragment : NoParamsFragment<SampleFragmentBinding>() {
    private val viewModel by getViewModel<SampleViewModel>()

    override fun viewBindingInflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SampleFragmentBinding = SampleFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupCollectors()

        viewModel.onStart()
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

    private fun navigateToAssistedSample() {
        findNavController().navigate(Destination.AssistedSampleFragment.action())
    }
}
