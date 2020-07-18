package graeme.hosford.avatarcharacterdatabase.repo.character.list

import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.repo.common.RepoState
import kotlinx.coroutines.flow.Flow

const val CHARACTERS_PER_PAGE = 100

interface CharacterListRepo {
    suspend fun getCharacterList(): Flow<RepoState<List<CharacterEntity>>>
}