package dev.kenkoro.lib.di.modules

import dagger.Module
import dagger.Provides
import dev.kenkoro.lib.api.DummyJsonApi
import dev.kenkoro.lib.di.qualifiers.BaseUrl
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
internal class ApiModule {
    @Singleton
    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
        }
    }

    @Singleton
    @Provides
    fun provideMediaType(): MediaType = "application/json".toMediaType()

    @Singleton
    @Provides
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
        json: Json,
        mediaType: MediaType,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                json.asConverterFactory(contentType = mediaType)
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideDummyJsonApi(retrofit: Retrofit): DummyJsonApi = retrofit.create()
}