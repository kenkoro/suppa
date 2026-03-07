package {{ cookiecutter.pkg_name }}.domain.sample.interactor

import {{ cookiecutter.pkg_name }}.domain.sample.model.SampleData
import {{ cookiecutter.pkg_name }}.domain.sample.repository.SampleRepository
import javax.inject.Inject

class GetSampleData @Inject constructor(
    private val repository: SampleRepository,
) {
    suspend fun await(): SampleData {
        return repository.getSampleData()
    }
}
