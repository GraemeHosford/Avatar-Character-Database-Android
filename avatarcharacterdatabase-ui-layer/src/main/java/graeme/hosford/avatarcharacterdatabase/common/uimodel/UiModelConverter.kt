package graeme.hosford.avatarcharacterdatabase.common.uimodel

interface UiModelConverter<Entity, UiModel : BaseUiModel> {
    fun toUiModel(entity: Entity): UiModel
}