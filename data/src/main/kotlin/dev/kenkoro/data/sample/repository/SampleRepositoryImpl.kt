package dev.kenkoro.data.sample.repository

import dev.kenkoro.domain.sample.model.SampleData
import dev.kenkoro.domain.sample.repository.SampleRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class SampleRepositoryImpl @Inject constructor() : SampleRepository {
    override suspend fun getSampleData(): SampleData {
        delay(1.seconds.inWholeMilliseconds)
        return SampleData.create()
    }
}
