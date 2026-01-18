package {{ cookiecutter.pkg_name }}.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import {{ cookiecutter.pkg_name }}.app.MainActivity

@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun contributeMainActivityAndroidInjector(): MainActivity
}
