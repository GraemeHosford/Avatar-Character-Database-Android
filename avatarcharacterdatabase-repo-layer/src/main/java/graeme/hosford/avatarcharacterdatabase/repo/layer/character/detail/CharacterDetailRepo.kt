package graeme.hosford.avatarcharacterdatabase.repo.layer.character.detail

import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.repo.layer.common.RepoState
import kotlinx.coroutines.flow.Flow

interface CharacterDetailRepo {
    suspend fun getSingleCharacter(id: Long, networkId: String): Flow<RepoState<CharacterEntity>>
}