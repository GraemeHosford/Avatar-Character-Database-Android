package graeme.hosford.avatarcharacterdatabase.ui.common.uimodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class ListUiModelProcessor<Entity, UiModel : BaseUiModel>(
    private val converter: UiModelConverter<Entity, UiModel>
) {
    @ExperimentalCoroutinesApi
    fun process(entities: List<Entity>): Flow<UiModel> {
        return entities.asFlow()
            .flowOn(Dispatchers.Default)
            .map { converter.toUiModel(it) }
    }
}