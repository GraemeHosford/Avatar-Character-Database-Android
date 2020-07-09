package graeme.hosford.avatarcharacterdatabase.ui.character.list.model

import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.UiModelConverter

class CharacterListItemUiModelConverter :
    UiModelConverter<CharacterEntity, CharacterListItemUiModel> {
    override fun toUiModel(entity: CharacterEntity): CharacterListItemUiModel {
        return with(entity) {
            CharacterListItemUiModel(
                id,
                characterName,
                photoUrl
            )
        }
    }
}