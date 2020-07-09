package graeme.hosford.avatarcharacterdatabase.repo.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class ResponseProcessor<Response, Entity>(
    private val converter: ResponseConverter<Response, Entity>
) {
    @ExperimentalCoroutinesApi
    fun process(responses: List<Response>): Flow<Entity> {
        return responses.asFlow()
            .map {
                converter.toEntity(it)
            }.flowOn(Dispatchers.Default)
    }
}