package graeme.hosford.avatarcharacterdatabase.ui.character.list.model

import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.pagination.PaginatedListUiModelProcessor
import javax.inject.Inject

class CharacterListItemUiModelProcessor @Inject constructor(
    converter: CharacterListItemUiModelConverter
) : PaginatedListUiModelProcessor<CharacterEntity, CharacterListItemUiModel>(
    converter,
    CharacterListItemUiModelComparator.nameComparator
)