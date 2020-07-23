package graeme.hosford.avatarcharacterdatabase.repo.layer.character.detail

import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.layer.character.CharacterResponse
import graeme.hosford.avatarcharacterdatabase.repo.layer.character.common.CharacterResponseConverter
import graeme.hosford.avatarcharacterdatabase.repo.layer.common.SingleResponseProcessor
import javax.inject.Inject

class SingleCharacterResponseProcessor @Inject constructor(
    converter: CharacterResponseConverter
) : SingleResponseProcessor<CharacterResponse, CharacterEntity>(converter)