package {{ cookiecutter.pkg_name }}.lib.utils.paging

interface PagingDataSource<T> {
    /**
     * Checks if we reached the end of the page.
     */
    fun isPageFull(list: List<T>): Boolean

    /**
     * Load next page based on the current one.
     */
    suspend fun loadPage(list: List<T>?): List<T>
}

/**
 * [PagingDataSource] implementation to load by one page at a time using the number of all pages
 * and the page size.
 */
@Suppress("FunctionName")
fun <T> PageSizePagingDataSource(
    pageSize: Int,
    loadPage: suspend (page: Int, pageSize: Int) -> List<T>,
): PagingDataSource<T> {
    return object : PagingDataSource<T> {
        override fun isPageFull(list: List<T>): Boolean = list.size == pageSize

        override suspend fun loadPage(list: List<T>?): List<T> {
            val page: Int = if (list == null) 1 else (list.size / pageSize) + 1
            return loadPage(page, pageSize)
        }
    }
}
