package graeme.hosford.avatarcharacterdatabase.repo.character.common

import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.character.CharacterResponse
import graeme.hosford.avatarcharacterdatabase.repo.common.ResponseConverter
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