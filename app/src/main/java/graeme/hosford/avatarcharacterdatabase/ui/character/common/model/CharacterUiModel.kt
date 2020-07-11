package graeme.hosford.avatarcharacterdatabase.ui.character.common.model

import androidx.annotation.CallSuper
import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.BaseUiModel

abstract class CharacterUiModel(
    id: Long,
    val name: String,
    val photoUrl: String?
) : BaseUiModel(id) {
    @CallSuper
    override fun <UiModel : BaseUiModel> areContentsTheSame(other: UiModel): Boolean {
        if (other !is CharacterUiModel) {
            return false
        }

        return id == other.id
                && name == other.name
                && photoUrl == other.photoUrl
    }
}