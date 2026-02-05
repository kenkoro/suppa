package {{ cookiecutter.pkg_name }}.lib.feature.sample.di.scopes

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AssistedSampleScope
