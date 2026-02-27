package dev.kenkoro.feature.assistedSample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.kenkoro.feature.assistedSample.databinding.AssistedSampleFragmentBinding
import dev.kenkoro.feature.assistedSample.di.AssistedSampleViewModelFactory
import dev.kenkoro.feature.utils.di.components.ParamsFragment

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
