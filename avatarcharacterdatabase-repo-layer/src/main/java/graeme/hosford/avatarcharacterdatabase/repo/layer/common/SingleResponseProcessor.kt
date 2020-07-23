package graeme.hosford.avatarcharacterdatabase.repo.layer.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single

abstract class SingleResponseProcessor<Response, Entity>(
    private val converter: ResponseConverter<Response, Entity>
) {
    suspend fun process(response: Response): Entity {
        return flowOf(response)
            .map {
                converter.toEntity(it)
            }.flowOn(Dispatchers.Default)
            .single()
    }
}