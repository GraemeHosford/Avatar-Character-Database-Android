package graeme.hosford.avatarcharacterdatabase.repo.layer.common.pagination

import graeme.hosford.avatarcharacterdatabase.repo.layer.common.RepoState
import kotlinx.coroutines.flow.Flow

interface PaginatedRepo<DataType> {
    suspend fun getNextPage(): Flow<RepoState<List<DataType>>>
}