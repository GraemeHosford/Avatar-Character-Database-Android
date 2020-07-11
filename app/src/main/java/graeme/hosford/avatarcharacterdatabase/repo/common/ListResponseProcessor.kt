package graeme.hosford.avatarcharacterdatabase.repo.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

abstract class ListResponseProcessor<Response, Entity>(
    private val converter: ResponseConverter<Response, Entity>
) {
    @ExperimentalCoroutinesApi
    suspend fun process(responses: List<Response>): List<Entity> {
        val entities = arrayListOf<Entity>()
        return responses.asFlow()
            .map {
                converter.toEntity(it)
            }.flowOn(Dispatchers.Default)
            .toList(entities)
    }
}