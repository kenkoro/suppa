package {{ cookiecutter.pkg_name }}.feature.assistedSample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import {{ cookiecutter.pkg_name }}.feature.assistedSample.AssistedSampleFragment
import {{ cookiecutter.pkg_name }}.feature.utils.di.FragmentScope

@Module
interface AssistedSampleModule {
    @FragmentScope
    @ContributesAndroidInjector
    fun contributeAssistedSampleFragmentAndroidInjector(): AssistedSampleFragment
}
