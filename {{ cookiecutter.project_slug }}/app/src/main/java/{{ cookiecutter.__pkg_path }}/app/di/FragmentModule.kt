package {{ cookiecutter.pkg_name }}.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import {{ cookiecutter.pkg_name }}.app.feature.sample.AssistedSampleFragment
import {{ cookiecutter.pkg_name }}.app.feature.sample.SampleFragment

@Module
internal interface FragmentModule {
    @ContributesAndroidInjector
    fun contributeSampleFragmentAndroidInjector(): SampleFragment

    @ContributesAndroidInjector
    fun contributeAssistedSampleFragmentAndroidInjector(): AssistedSampleFragment
}
