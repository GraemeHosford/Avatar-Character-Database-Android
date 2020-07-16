package graeme.hosford.avatarcharacterdatabase.repo.character

import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.repo.common.RepoState
import kotlinx.coroutines.flow.Flow

interface CharacterRepo {
    suspend fun getCharacterList(): List<CharacterEntity>

    suspend fun getSingleCharacter(id: Long, networkId: String): Flow<RepoState<CharacterEntity>>
}