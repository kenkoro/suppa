package {{ cookiecutter.pkg_name }}.domain.sample.model

data class SampleData(val id: Long) {
    companion object Companion {
        fun create(): SampleData = SampleData(id = -1L)
    }
}
