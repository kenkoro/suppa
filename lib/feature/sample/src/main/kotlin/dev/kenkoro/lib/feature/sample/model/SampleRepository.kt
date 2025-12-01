package dev.kenkoro.lib.feature.sample.model

interface SampleRepository {
    suspend fun fetch(name: String): String
}
