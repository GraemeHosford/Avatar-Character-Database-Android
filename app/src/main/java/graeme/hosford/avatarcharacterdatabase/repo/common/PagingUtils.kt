package graeme.hosford.avatarcharacterdatabase.repo.common

object PagingUtils {
    fun getOffset(page: Int, pageSize: Int): Int = (page - 1) * pageSize
}