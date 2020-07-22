package graeme.hosford.avatarcharacterdatabase.repo.common.pagination

import graeme.hosford.avatarcharacterdatabase.repo.common.RepoState
import kotlinx.coroutines.flow.Flow

interface PaginatedRepo<DataType> {
    suspend fun getNextPage(): Flow<RepoState<List<DataType>>>
}