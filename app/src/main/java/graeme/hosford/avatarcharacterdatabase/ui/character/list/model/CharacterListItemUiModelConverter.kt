package graeme.hosford.avatarcharacterdatabase.ui.character.list.model

import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.UiModelConverter
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