package graeme.hosford.avatarcharacterdatabase.ui.character.detail.model

import androidx.annotation.StringRes
import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.BaseUiModel

class CharacterSingleDetailUiModel(
    id: Long,
    @StringRes val titleString: Int,
    val detail: String
) : BaseUiModel(id) {
    override fun <UiModel : BaseUiModel> areContentsTheSame(other: UiModel): Boolean {
        if (other !is CharacterSingleDetailUiModel) {
            return false
        }

        return id == other.id
                && titleString == other.titleString
                && detail == other.detail
    }
}