package graeme.hosford.avatarcharacterdatabase.repo.common

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

abstract class BaseRepo<Service, DAO, DataType>(
    protected val service: Service,
    protected val dao: DAO
) {
    protected open suspend fun fetchData(block: suspend FlowCollector<RepoState<DataType>>.() -> Unit) =
        flow<RepoState<DataType>> {
            emit(RepoState.loading())

            try {
                block()
            } catch (e: Exception) {
                emit(RepoState.error())
            }
        }
}