package dev.kenkoro.data.di.module

import dagger.Module
import dagger.Provides
import dev.kenkoro.data.di.AppScope
import dev.kenkoro.data.di.qualifier.BaseUrl
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
object ApiModule {
    @AppScope
    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
        }
    }

    @AppScope
    @Provides
    fun provideMediaType(): MediaType = "application/json".toMediaType()

    @AppScope
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
}
