package graeme.hosford.avatarcharacterdatabase.repo.character

import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterRepo {
    suspend fun getCharacterList(): List<CharacterEntity>

    suspend fun getSingleCharacter(id: Long, networkId: String): Flow<CharacterEntity>
}