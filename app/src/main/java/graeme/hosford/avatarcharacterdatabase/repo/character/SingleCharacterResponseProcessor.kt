package graeme.hosford.avatarcharacterdatabase.repo.character

import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.character.CharacterResponse
import graeme.hosford.avatarcharacterdatabase.repo.common.SingleResponseProcessor
import javax.inject.Inject

class SingleCharacterResponseProcessor @Inject constructor(
    converter: CharacterResponseConverter
) : SingleResponseProcessor<CharacterResponse, CharacterEntity>(converter)