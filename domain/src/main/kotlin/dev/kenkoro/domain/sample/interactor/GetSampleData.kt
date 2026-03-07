package dev.kenkoro.domain.sample.interactor

import dev.kenkoro.domain.sample.model.SampleData
import dev.kenkoro.domain.sample.repository.SampleRepository
import javax.inject.Inject

class GetSampleData @Inject constructor(
    private val repository: SampleRepository,
) {
    suspend fun await(): SampleData {
        return repository.getSampleData()
    }
}
