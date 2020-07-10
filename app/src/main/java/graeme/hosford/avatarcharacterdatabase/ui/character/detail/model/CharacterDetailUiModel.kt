package graeme.hosford.avatarcharacterdatabase.ui.character.detail.model

import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.BaseUiModel

class CharacterDetailUiModel(
    id: Long,
    val name: String,
    val photoUrl: String?,
    val gender: String?,
    val eyeColour: String?,
    val hairColour: String?,
    val skinColour: String?,
    val weapon: String?,
    val loves: String?,
    val profession: String?,
    val position: String?,
    val predecessor: String?,
    val affiliation: String?,
    val first: String?,
    val voicedBy: String?
) : BaseUiModel(id) {
    override fun <UiModel : BaseUiModel> areContentsTheSame(other: UiModel): Boolean {
        if (other !is CharacterDetailUiModel) {
            return false
        }

        return id == other.id
                && name == other.name
                && photoUrl == other.photoUrl
                && gender == other.gender
                && eyeColour == other.eyeColour
                && hairColour == other.hairColour
                && skinColour == other.skinColour
                && weapon == other.weapon
                && loves == other.loves
                && profession == other.profession
                && position == other.position
                && predecessor == other.predecessor
                && affiliation == other.affiliation
                && first == other.first
                && voicedBy == other.voicedBy
    }
}