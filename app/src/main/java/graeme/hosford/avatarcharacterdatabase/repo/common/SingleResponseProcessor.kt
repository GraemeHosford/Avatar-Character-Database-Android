package graeme.hosford.avatarcharacterdatabase.repo.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class SingleResponseProcessor<Response, Entity>(
    private val converter: ResponseConverter<Response, Entity>
) {
    @ExperimentalCoroutinesApi
    suspend fun process(response: Response): Entity {
        return flowOf(response)
            .map {
                converter.toEntity(it)
            }.flowOn(Dispatchers.Default)
            .first()
    }
}