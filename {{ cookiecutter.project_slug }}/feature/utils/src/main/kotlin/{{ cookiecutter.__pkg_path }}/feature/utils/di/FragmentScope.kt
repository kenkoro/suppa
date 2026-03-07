package {{ cookiecutter.pkg_name }}.feature.utils.di

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope
