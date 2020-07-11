package graeme.hosford.avatarcharacterdatabase.repo.character

import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity

interface CharacterRepo {
    suspend fun getCharacterList(): List<CharacterEntity>

    suspend fun getSingleCharacter(id: String): CharacterEntity
}