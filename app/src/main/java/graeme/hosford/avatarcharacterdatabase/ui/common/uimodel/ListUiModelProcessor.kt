package graeme.hosford.avatarcharacterdatabase.ui.common.uimodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

abstract class ListUiModelProcessor<Entity, UiModel : BaseUiModel>(
    private val converter: UiModelConverter<Entity, UiModel>,
    private val comparator: Comparator<UiModel>
) {

    open suspend fun process(entities: List<Entity>): List<UiModel> {
        val models = arrayListOf<UiModel>()
        return entities.asFlow()
            .map {
                converter.toUiModel(it)
            }.flowOn(Dispatchers.Default)
            .toList(models)
            .sortedWith(comparator)
    }
}