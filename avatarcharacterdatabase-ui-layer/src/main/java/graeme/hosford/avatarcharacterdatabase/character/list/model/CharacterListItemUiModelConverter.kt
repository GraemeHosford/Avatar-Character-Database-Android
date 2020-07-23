package graeme.hosford.avatarcharacterdatabase.character.list.model

import graeme.hosford.avatarcharacterdatabase.common.uimodel.UiModelConverter
import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity
import javax.inject.Inject

class CharacterListItemUiModelConverter @Inject constructor() :
    UiModelConverter<CharacterEntity, CharacterListItemUiModel> {
    override fun toUiModel(entity: CharacterEntity): CharacterListItemUiModel {
        return with(entity) {
            CharacterListItemUiModel(
                id,
                characterId,
                characterName,
                photoUrl
            )
        }
    }
}