package graeme.hosford.avatarcharacterdatabase.ui.character.list.model

import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.BaseUiModel

class CharacterListItemUiModel(
    id: Long,
    val name: String,
    val photoUrl: String
) : BaseUiModel(id) {
    override fun <UiModel : BaseUiModel> areContentsTheSame(other: UiModel): Boolean {
        if (other !is CharacterListItemUiModel) {
            return false
        }

        return id == other.id
                && name == other.name
                && photoUrl == other.photoUrl
    }
}