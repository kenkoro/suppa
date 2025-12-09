package {{ cookiecutter.pkg_name }}.lib.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppContext
