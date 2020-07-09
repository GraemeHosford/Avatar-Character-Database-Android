package graeme.hosford.avatarcharacterdatabase.repo.character

import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.character.CharacterResponse
import graeme.hosford.avatarcharacterdatabase.repo.common.ResponseConverter

class CharacterResponseConverter : ResponseConverter<CharacterResponse, CharacterEntity> {
    override fun toEntity(response: CharacterResponse): CharacterEntity {
        return with(response) {
            CharacterEntity(
                characterId = characterId,
                characterName = characterName,
                photoUrl = photoUrl,
                gender = gender,
                eyeColour = eyeColour,
                hairColour = hairColour,
                skinColour = skinColour,
                loves = loves,
                profession = profession,
                predecessor = predecessor,
                affiliation = affiliation
            )
        }
    }
}