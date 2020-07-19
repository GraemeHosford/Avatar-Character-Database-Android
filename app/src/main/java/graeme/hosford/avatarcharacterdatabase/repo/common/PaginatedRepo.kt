package graeme.hosford.avatarcharacterdatabase.repo.common

import kotlinx.coroutines.flow.Flow

interface PaginatedRepo<DataType> {
    suspend fun getNextPage(): Flow<RepoState<List<DataType>>>
}