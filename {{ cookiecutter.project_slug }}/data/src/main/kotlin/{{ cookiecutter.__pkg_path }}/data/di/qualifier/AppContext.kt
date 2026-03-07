package {{ cookiecutter.pkg_name }}.data.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppContext
