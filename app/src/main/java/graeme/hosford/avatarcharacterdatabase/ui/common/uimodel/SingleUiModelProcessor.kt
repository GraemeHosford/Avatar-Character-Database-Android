package graeme.hosford.avatarcharacterdatabase.ui.common.uimodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class SingleUiModelProcessor<Entity, UiModel : BaseUiModel>(
    private val converter: UiModelConverter<Entity, UiModel>
) {
    @ExperimentalCoroutinesApi
    fun process(entity: Entity): Flow<UiModel> {
        return flowOf(entity)
            .flowOn(Dispatchers.Default)
            .map { converter.toUiModel(it) }
    }
}