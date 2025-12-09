package {{ cookiecutter.pkg_name }}.lib.feature.sample.model

interface SampleRepository {
    suspend fun get()

    suspend fun get(name: String)
}
