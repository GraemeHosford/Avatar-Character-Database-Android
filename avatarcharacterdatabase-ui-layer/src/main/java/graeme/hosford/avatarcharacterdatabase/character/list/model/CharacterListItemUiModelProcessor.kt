package graeme.hosford.avatarcharacterdatabase.character.list.model

import graeme.hosford.avatarcharacterdatabase.common.uimodel.pagination.PaginatedListUiModelProcessor
import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity
import javax.inject.Inject

class CharacterListItemUiModelProcessor @Inject constructor(
    converter: CharacterListItemUiModelConverter
) : PaginatedListUiModelProcessor<CharacterEntity, CharacterListItemUiModel>(
    converter,
    CharacterListItemUiModelComparator.nameComparator
)