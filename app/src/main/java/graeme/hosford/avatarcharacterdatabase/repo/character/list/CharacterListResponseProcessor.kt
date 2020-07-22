package graeme.hosford.avatarcharacterdatabase.repo.character.list

import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.layer.character.CharacterResponse
import graeme.hosford.avatarcharacterdatabase.repo.character.common.CharacterResponseConverter
import graeme.hosford.avatarcharacterdatabase.repo.common.ListResponseProcessor
import javax.inject.Inject

class CharacterListResponseProcessor @Inject constructor(
    converter: CharacterResponseConverter
) : ListResponseProcessor<CharacterResponse, CharacterEntity>(converter)