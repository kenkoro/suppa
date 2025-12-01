package dev.kenkoro.lib.feature.sample.di

import dagger.assisted.AssistedFactory
import dev.kenkoro.lib.feature.sample.SampleViewModel
import dev.kenkoro.lib.utils.di.viewmodel.Factory

@AssistedFactory
interface SampleViewModelFactory : Factory<SampleViewModel.Params, SampleViewModel>
