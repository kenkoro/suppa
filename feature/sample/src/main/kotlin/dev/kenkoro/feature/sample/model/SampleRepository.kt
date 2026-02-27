package dev.kenkoro.feature.sample.model

interface SampleRepository {
    suspend fun get()

    suspend fun get(name: String)
}
