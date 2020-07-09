package graeme.hosford.avatarcharacterdatabase.ui.character.list.model

import graeme.hosford.avatarcharacterdatabase.ui.common.uimodel.BaseUiModel

class CharacterListItemUiModel(
    id: Long,
    val name: String,
    val photoUrl: String
) : BaseUiModel(id)