package graeme.hosford.avatarcharacterdatabase.character.list.model

object CharacterListItemUiModelComparator {
    val nameComparator = compareBy(CharacterListItemUiModel::name)
}