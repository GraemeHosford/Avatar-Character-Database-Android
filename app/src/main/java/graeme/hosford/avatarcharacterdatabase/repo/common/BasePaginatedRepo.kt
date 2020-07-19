package graeme.hosford.avatarcharacterdatabase.repo.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

abstract class BasePaginatedRepo<Service, DAO, DataType>(
    service: Service,
    dao: DAO
) : BaseRepo<Service, DAO, List<DataType>>(service, dao) {
    private var page = 1

    suspend fun getNextPage() = fetchData {
        val pageSize = getPageSize()
        val offset = getOffset(page, pageSize)
        val localPage = fetchFromLocal(dao, offset, pageSize)
        localPage.collect {
            when (it) {
                is RepoState.Error -> emit(it)
                is RepoState.Loading -> emit(it)
                is RepoState.Completed -> {
                    emit(it)
                    if (it.data.size < pageSize) {
                        fetchFromNetwork(
                            service,
                            page,
                            pageSize,
                            offset
                        ).collect { networkPage ->
                            emit(networkPage)
                        }
                    }
                }
            }
        }

        page++
    }

    abstract fun getPageSize(): Int

    abstract suspend fun fetchFromLocal(
        dao: DAO,
        offset: Int,
        limit: Int
    ): Flow<RepoState<List<DataType>>>

    abstract suspend fun fetchFromNetwork(
        service: Service,
        page: Int,
        pageSize: Int,
        dbOffset: Int
    ): Flow<RepoState<List<DataType>>>

    private fun getOffset(page: Int, pageSize: Int) = (page - 1) * pageSize
}