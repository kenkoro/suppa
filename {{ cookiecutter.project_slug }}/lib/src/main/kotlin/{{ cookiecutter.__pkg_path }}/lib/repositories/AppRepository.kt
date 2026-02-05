package {{ cookiecutter.pkg_name }}.lib.repositories

import {{ cookiecutter.pkg_name }}.lib.di.scopes.MainScope
import javax.inject.Inject

@MainScope
class AppRepository @Inject constructor() : FeatureRepository {
    override suspend fun get() = Unit

    override suspend fun get(name: String) = Unit
}
