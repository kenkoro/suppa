package dev.kenkoro.lib.api

import retrofit2.http.GET

internal interface DummyJsonApi {
    @GET("json")
    suspend fun json()
}