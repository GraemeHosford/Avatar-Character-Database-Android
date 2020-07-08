package graeme.hosford.avatarcharacterdatabase.ui.common.uimodel

interface UiModelConverter<Entity, UiModel : BaseUiModel> {
    fun toUiModel(entity: Entity): UiModel
}