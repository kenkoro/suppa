package {{ cookiecutter.pkg_name }}.data.di

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope
