package graeme.hosford.avatarcharacterdatabase.common.uimodel.pagination

import graeme.hosford.avatarcharacterdatabase.common.uimodel.BaseUiModel
import graeme.hosford.avatarcharacterdatabase.common.uimodel.ListUiModelProcessor
import graeme.hosford.avatarcharacterdatabase.common.uimodel.UiModelConverter

abstract class PaginatedListUiModelProcessor<Entity, UiModel : BaseUiModel>(
    converter: UiModelConverter<Entity, UiModel>,
    comparator: Comparator<UiModel>
) : ListUiModelProcessor<Entity, UiModel>(converter, comparator) {
    private val paginatedItems = arrayListOf<UiModel>()

    override suspend fun process(entities: List<Entity>): List<UiModel> {
        paginatedItems.addAll(super.process(entities))
        return paginatedItems
    }
}