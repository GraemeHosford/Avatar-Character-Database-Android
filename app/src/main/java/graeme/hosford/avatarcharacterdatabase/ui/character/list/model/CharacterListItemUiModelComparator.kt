package graeme.hosford.avatarcharacterdatabase.ui.character.list.model

object CharacterListItemUiModelComparator {
    val nameComparator = compareBy(CharacterListItemUiModel::name)
}