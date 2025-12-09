package {{ cookiecutter.pkg_name }}.lib.apis

import retrofit2.http.GET

interface SampleApi {
    @GET("sample")
    suspend fun get()
}
