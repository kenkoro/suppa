package {{ cookiecutter.pkg_name }}.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import {{ cookiecutter.pkg_name }}.app.feature.sample.AssistedSampleFragment
import {{ cookiecutter.pkg_name }}.app.feature.sample.SampleFragment
import {{ cookiecutter.pkg_name }}.lib.feature.sample.di.scopes.AssistedSampleScope
import {{ cookiecutter.pkg_name }}.lib.feature.sample.di.scopes.SampleScope

@Module
interface FragmentModule {
    @SampleScope
    @ContributesAndroidInjector
    fun contributeSampleFragmentAndroidInjector(): SampleFragment

    @AssistedSampleScope
    @ContributesAndroidInjector
    fun contributeAssistedSampleFragmentAndroidInjector(): AssistedSampleFragment
}
