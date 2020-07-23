package graeme.hosford.avatarcharacterdatabase.repo.layer.common.pagination

object PagingUtils {
    fun getOffset(page: Int, pageSize: Int): Int = (page - 1) * pageSize
}