package {{ cookiecutter.pkg_name }}.data.sample.di

import dagger.Binds
import dagger.Module
import {{ cookiecutter.pkg_name }}.data.sample.repository.SampleRepositoryImpl
import {{ cookiecutter.pkg_name }}.domain.sample.repository.SampleRepository

@Module
interface SampleRepositoryModule {
    @Binds
    fun bindSampleRepository(implementation: SampleRepositoryImpl): SampleRepository
}
