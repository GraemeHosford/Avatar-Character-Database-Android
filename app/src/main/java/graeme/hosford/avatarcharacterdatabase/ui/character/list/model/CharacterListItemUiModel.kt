package graeme.hosford.avatarcharacterdatabase.ui.character.list.model

import graeme.hosford.avatarcharacterdatabase.ui.character.common.model.CharacterUiModel
import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.BaseUiModel

class CharacterListItemUiModel(
    id: Long,
    name: String,
    photoUrl: String?
) : CharacterUiModel(id, name, photoUrl) {
    override fun <UiModel : BaseUiModel> areContentsTheSame(other: UiModel): Boolean {
        if (other !is CharacterListItemUiModel) {
            return false
        }

        return super.areContentsTheSame(other)
    }
}