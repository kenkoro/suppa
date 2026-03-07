package {{ cookiecutter.pkg_name }}.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import {{ cookiecutter.pkg_name }}.app.MainActivity
import {{ cookiecutter.pkg_name }}.data.di.ActivityScope
import {{ cookiecutter.pkg_name }}.feature.di.module.FeaturesModule

@Module
interface HostModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [FeaturesModule::class])
    fun contributeMainActivityAndroidInjector(): MainActivity
}
