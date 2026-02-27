package dev.kenkoro.feature.apis

import retrofit2.http.GET

interface SampleApi {
    @GET("sample")
    suspend fun get()
}
