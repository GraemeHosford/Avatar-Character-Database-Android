package graeme.hosford.avatarcharacterdatabase.ui.character.list.model

import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.ListUiModelProcessor

class CharacterListItemUiModelProcessor constructor(
    converter: CharacterListItemUiModelConverter
) : ListUiModelProcessor<CharacterEntity, CharacterListItemUiModel>(converter)