package {{ cookiecutter.pkg_name }}.domain.sample.repository

import {{ cookiecutter.pkg_name }}.domain.sample.model.SampleData

interface SampleRepository {
    suspend fun getSampleData(): SampleData
}
