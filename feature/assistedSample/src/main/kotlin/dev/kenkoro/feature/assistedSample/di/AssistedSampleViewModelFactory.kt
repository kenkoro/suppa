package dev.kenkoro.feature.assistedSample.di

import dagger.assisted.AssistedFactory
import dev.kenkoro.feature.assistedSample.AssistedSampleViewModel
import dev.kenkoro.feature.assistedSample.AssistedSampleViewModel.Params
import dev.kenkoro.feature.utils.di.viewmodel.Factory

@AssistedFactory
interface AssistedSampleViewModelFactory : Factory<Params, AssistedSampleViewModel>
