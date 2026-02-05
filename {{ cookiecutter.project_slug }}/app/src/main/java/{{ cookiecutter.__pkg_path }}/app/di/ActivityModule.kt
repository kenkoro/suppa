package {{ cookiecutter.pkg_name }}.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import {{ cookiecutter.pkg_name }}.app.MainActivity
import {{ cookiecutter.pkg_name }}.lib.di.modules.LibModule
import {{ cookiecutter.pkg_name }}.lib.di.scopes.MainScope

@Module
interface ActivityModule {
    @MainScope
    @ContributesAndroidInjector(modules = [LibModule::class, FragmentModule::class])
    fun contributeMainActivityAndroidInjector(): MainActivity
}
