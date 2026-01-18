package {{ cookiecutter.pkg_name }}.lib.di.modules

import dagger.Module
import dagger.Provides
import {{ cookiecutter.pkg_name }}.lib.di.qualifiers.BaseUrl
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
object ApiModule {
    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
        }
    }

    @Provides
    fun provideMediaType(): MediaType = "application/json".toMediaType()

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
