package graeme.hosford.avatarcharacterdatabase.repo.character.detail

import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.character.CharacterResponse
import graeme.hosford.avatarcharacterdatabase.repo.character.common.CharacterResponseConverter
import graeme.hosford.avatarcharacterdatabase.repo.common.SingleResponseProcessor
import javax.inject.Inject

class SingleCharacterResponseProcessor @Inject constructor(
    converter: CharacterResponseConverter
) : SingleResponseProcessor<CharacterResponse, CharacterEntity>(converter)