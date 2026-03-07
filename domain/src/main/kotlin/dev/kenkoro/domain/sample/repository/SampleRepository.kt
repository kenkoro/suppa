package dev.kenkoro.domain.sample.repository

import dev.kenkoro.domain.sample.model.SampleData

interface SampleRepository {
    suspend fun getSampleData(): SampleData
}
