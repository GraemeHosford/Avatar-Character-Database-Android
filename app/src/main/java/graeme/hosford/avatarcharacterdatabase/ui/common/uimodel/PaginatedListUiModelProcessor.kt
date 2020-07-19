package graeme.hosford.avatarcharacterdatabase.ui.common.uimodel

abstract class PaginatedListUiModelProcessor<Entity, UiModel : BaseUiModel>(
    converter: UiModelConverter<Entity, UiModel>,
    comparator: Comparator<UiModel>
) : ListUiModelProcessor<Entity, UiModel>(converter, comparator) {
    val paginatedItems = arrayListOf<UiModel>()

    override suspend fun process(entities: List<Entity>): List<UiModel> {
        paginatedItems.addAll(super.process(entities))
        return paginatedItems
    }
}