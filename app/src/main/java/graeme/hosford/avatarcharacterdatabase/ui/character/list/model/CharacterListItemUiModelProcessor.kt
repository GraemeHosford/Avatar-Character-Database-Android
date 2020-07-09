package graeme.hosford.avatarcharacterdatabase.ui.character.list.model

import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.ListUiModelProcessor
import javax.inject.Inject

class CharacterListItemUiModelProcessor @Inject constructor(
    converter: CharacterListItemUiModelConverter
) : ListUiModelProcessor<CharacterEntity, CharacterListItemUiModel>(converter)