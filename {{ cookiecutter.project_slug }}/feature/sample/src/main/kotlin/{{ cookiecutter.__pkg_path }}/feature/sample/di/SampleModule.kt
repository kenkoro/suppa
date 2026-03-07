package {{ cookiecutter.pkg_name }}.feature.sample.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import {{ cookiecutter.pkg_name }}.feature.sample.SampleFragment
import {{ cookiecutter.pkg_name }}.feature.sample.SampleViewModel
import {{ cookiecutter.pkg_name }}.feature.utils.di.FragmentScope
import {{ cookiecutter.pkg_name }}.feature.utils.di.ViewModelKey

@Module
interface SampleModule {
    @FragmentScope
    @ContributesAndroidInjector
    fun contributeSampleFragmentAndroidInjector(): SampleFragment

    @Binds
    @IntoMap
    @ViewModelKey(SampleViewModel::class)
    fun bindSampleViewModel(implementation: SampleViewModel): ViewModel
}
