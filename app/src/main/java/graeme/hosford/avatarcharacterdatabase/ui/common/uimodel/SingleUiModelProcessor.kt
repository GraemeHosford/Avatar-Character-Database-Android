package graeme.hosford.avatarcharacterdatabase.ui.common.uimodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single

abstract class SingleUiModelProcessor<Entity, UiModel : BaseUiModel>(
    private val converter: UiModelConverter<Entity, UiModel>
) {
    suspend fun process(entity: Entity): UiModel {
        return flowOf(entity)
            .map { converter.toUiModel(it) }
            .flowOn(Dispatchers.Default)
            .single()
    }
}