package su.pank.simplescanner.ui.components

object AnimKeys {
    private val CONTAINER_KEY = "container"
    private val PAGE_KEY = "page"


    fun pageKey(page: Int, key: String) = "$PAGE_KEY $key $page"
    fun containerKey(page: Int, key: String) = "$CONTAINER_KEY $key $page"

}