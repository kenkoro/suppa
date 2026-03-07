package {{ cookiecutter.pkg_name }}.data.sample.repository

import {{ cookiecutter.pkg_name }}.domain.sample.model.SampleData
import {{ cookiecutter.pkg_name }}.domain.sample.repository.SampleRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class SampleRepositoryImpl @Inject constructor() : SampleRepository {
    override suspend fun getSampleData(): SampleData {
        delay(1.seconds.inWholeMilliseconds)
        return SampleData.create()
    }
}
