package graeme.hosford.avatarcharacterdatabase.repo.common

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

abstract class BasePaginatedRepo<Service, DAO, DataType>(
    service: Service,
    dao: DAO
) : BaseRepo<Service, DAO, List<DataType>>(service, dao), PaginatedRepo<DataType> {
    private var page = 1

    override suspend fun getNextPage() = fetchData {
        val pageSize = getPageSize()
        val offset = PagingUtils.getOffset(page, pageSize)
        val localPage = fetchFromLocal(dao, offset, pageSize)

        if (localPage.isNotEmpty()) {
            emit(RepoState.completed(localPage))
        }

        if (localPage.size < pageSize) {
            val networkPage = fetchFromNetwork(service, page, pageSize)
            saveToLocal(dao, networkPage)
            emit(RepoState.completed(fetchFromLocal(dao, offset, pageSize)))
        }

        page++
    }

    override suspend fun fetchData(block: suspend FlowCollector<RepoState<List<DataType>>>.() -> Unit) =
        flow<RepoState<List<DataType>>> {
            if (page == 1) {
                /* Only emit loading state on getting first page to avoid screen
                flicker on getting new pages */
                emit(RepoState.loading())
            }

            try {
                block()
            } catch (e: Exception) {
                emit(RepoState.error())
            }
        }

    protected abstract fun getPageSize(): Int

    protected abstract suspend fun fetchFromLocal(
        dao: DAO,
        offset: Int,
        limit: Int
    ): List<DataType>

    protected abstract suspend fun saveToLocal(dao: DAO, entities: List<DataType>)

    protected abstract suspend fun fetchFromNetwork(
        service: Service,
        page: Int,
        pageSize: Int
    ): List<DataType>
}