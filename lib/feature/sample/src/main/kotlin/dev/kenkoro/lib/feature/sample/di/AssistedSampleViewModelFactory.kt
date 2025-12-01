package dev.kenkoro.lib.feature.sample.di

import dagger.assisted.AssistedFactory
import dev.kenkoro.lib.feature.sample.presentation.AssistedSampleViewModel
import dev.kenkoro.lib.feature.sample.presentation.AssistedSampleViewModel.Params
import dev.kenkoro.lib.utils.di.viewmodel.Factory

@AssistedFactory
interface AssistedSampleViewModelFactory : Factory<Params, AssistedSampleViewModel>
