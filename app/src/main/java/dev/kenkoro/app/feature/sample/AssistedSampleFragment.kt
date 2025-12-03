package dev.kenkoro.app.feature.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.kenkoro.app.databinding.AssistedSampleFragmentBinding
import dev.kenkoro.app.di.ParamsFragment
import dev.kenkoro.lib.feature.sample.di.AssistedSampleViewModelFactory
import dev.kenkoro.lib.feature.sample.presentation.AssistedSampleViewModel

internal class AssistedSampleFragment :
    ParamsFragment<AssistedSampleViewModelFactory, AssistedSampleFragmentBinding>() {
    private val viewModel: AssistedSampleViewModel by getViewModel {
        AssistedSampleViewModel.Params(name = "passed info")
    }

    override fun viewBindingInflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): AssistedSampleFragmentBinding {
        return AssistedSampleFragmentBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onStart()
    }
}
