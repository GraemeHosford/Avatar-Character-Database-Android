package graeme.hosford.avatarcharacterdatabase.ui.character.detail.model

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
            gender,
            eyeColour,
            hairColour,
            skinColour,
            weapon,
            loves,
            profession,
            position,
            predecessor,
            affiliation,
            first,
            voicedBy
        )
    }
}