package graeme.hosford.avatarcharacterdatabase.ui.character.detail.model

import androidx.annotation.StringRes
import graeme.hosford.avatarcharacterdatabase.R
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.UiModelConverter
import javax.inject.Inject

class CharacterDetailUiModelConverter @Inject constructor() :
    UiModelConverter<CharacterEntity, CharacterDetailUiModel> {
    override fun toUiModel(entity: CharacterEntity) = with(entity) {
        CharacterDetailUiModel(
            id,
            characterName,
            photoUrl,
            listOfNotNull(
                /* Not getting an Id from database to use with these UiModels but a hardcoded value
                * works for nested UiModels here. Just need to ensure they are each distinct and
                * negative so they can't possibly overlap any database Id. This shouldn't be an
                * issue in this app but could be in a screen which uses a RecyclerView with multiple
                * different view types. */
                getDetailUiModel(-1L, R.string.character_detail_gender, gender),
                getDetailUiModel(-2L, R.string.character_detail_eye_colour, eyeColour),
                getDetailUiModel(-3L, R.string.character_detail_hair_colour, hairColour),
                getDetailUiModel(-4L, R.string.character_detail_skin_colour, skinColour),
                getDetailUiModel(-5L, R.string.character_detail_weapon, weapon),
                getDetailUiModel(-6L, R.string.character_detail_loves, loves),
                getDetailUiModel(-7L, R.string.character_detail_profession, profession),
                getDetailUiModel(-8L, R.string.character_detail_position, position),
                getDetailUiModel(-9L, R.string.character_detail_predecessor, predecessor),
                getDetailUiModel(-10L, R.string.character_detail_affiliation, affiliation),
                getDetailUiModel(-11L, R.string.character_detail_first, first),
                getDetailUiModel(-12L, R.string.character_detail_voiced_by, voicedBy)
            )
        )
    }

    private fun getDetailUiModel(
        modelId: Long,
        @StringRes titleString: Int,
        detail: String?
    ): CharacterSingleDetailUiModel? {
        return detail?.let {
            CharacterSingleDetailUiModel(
                modelId,
                titleString,
                detail
            )
        }
    }
}