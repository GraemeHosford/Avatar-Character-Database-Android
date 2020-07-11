package graeme.hosford.avatarcharacterdatabase.ui.character.detail.model

import graeme.hosford.avatarcharacterdatabase.ui.character.common.model.CharacterUiModel
import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.BaseUiModel

class CharacterDetailUiModel(
    id: Long,
    name: String,
    photoUrl: String?,
    val details: List<CharacterSingleDetailUiModel>
) : CharacterUiModel(id, name, photoUrl) {
    override fun <UiModel : BaseUiModel> areContentsTheSame(other: UiModel): Boolean {
        if (other !is CharacterDetailUiModel) {
            return false
        }

        return super.areContentsTheSame(other)
                && details == other.details
    }
}