package graeme.hosford.avatarcharacterdatabase.repo.character

import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.character.CharacterResponse
import graeme.hosford.avatarcharacterdatabase.repo.common.ResponseProcessor

class CharacterResponseProcessor constructor(
    converter: CharacterResponseConverter
) : ResponseProcessor<CharacterResponse, CharacterEntity>(converter)