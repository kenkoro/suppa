package {{ cookiecutter.pkg_name }}.lib.di.modules

import dagger.Binds
import dagger.Module
import {{ cookiecutter.pkg_name }}.lib.feature.sample.model.SampleRepository
import {{ cookiecutter.pkg_name }}.lib.repositories.AppRepository

@Module
interface RepositoryModule {
    @Binds
    fun bindSampleRepository(implementation: AppRepository): SampleRepository
}
