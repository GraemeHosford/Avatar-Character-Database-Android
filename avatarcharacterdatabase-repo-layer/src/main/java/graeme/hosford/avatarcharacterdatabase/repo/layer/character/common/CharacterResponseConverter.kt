package graeme.hosford.avatarcharacterdatabase.repo.layer.character.common

import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.layer.character.CharacterResponse
import graeme.hosford.avatarcharacterdatabase.repo.layer.common.ResponseConverter
import javax.inject.Inject

class CharacterResponseConverter @Inject constructor() :
    ResponseConverter<CharacterResponse, CharacterEntity> {
    override fun toEntity(response: CharacterResponse): CharacterEntity {
        return with(response) {
            CharacterEntity(
                characterId = characterId,
                characterName = characterName,
                photoUrl = photoUrl,
                gender = gender,
                allies = allies,
                enemies = enemies,
                eyeColour = eyeColour,
                hairColour = hairColour,
                skinColour = skinColour,
                weapon = weapon,
                loves = loves,
                profession = profession,
                position = position,
                predecessor = predecessor,
                affiliation = affiliation,
                first = first,
                voicedBy = voicedBy
            )
        }
    }
}