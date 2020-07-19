package graeme.hosford.avatarcharacterdatabase.repo.character.list

import graeme.hosford.avatarcharacterdatabase.database.character.CharacterDao
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.character.AvatarCharacterRetrofitService
import graeme.hosford.avatarcharacterdatabase.repo.common.BasePaginatedRepo
import graeme.hosford.avatarcharacterdatabase.repo.common.RepoState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterListRepoImpl @Inject constructor(
    service: AvatarCharacterRetrofitService,
    dao: CharacterDao,
    private val characterListProcessor: CharacterListResponseProcessor
) : BasePaginatedRepo<AvatarCharacterRetrofitService, CharacterDao, CharacterEntity>(
    service,
    dao
), CharacterListRepo {

    override fun getPageSize() = CHARACTERS_PER_PAGE

    override suspend fun fetchFromLocal(
        dao: CharacterDao,
        offset: Int,
        limit: Int
    ) = flow<RepoState<List<CharacterEntity>>> {
        emit(
            RepoState.completed(
                dao.getAllCharacters(
                    offset,
                    limit,
                    CharacterOrderBy.CHARACTER_NAME
                )
            )
        )
    }

    override suspend fun fetchFromNetwork(
        service: AvatarCharacterRetrofitService,
        page: Int,
        pageSize: Int,
        dbOffset: Int
    ) = flow<RepoState<List<CharacterEntity>>> {
        val responses = service.getAllCharacters(pageSize, page)

        val entities = characterListProcessor.process(responses)
        dao.save(entities)

        /* Can't just returns the result of processing as database Ids are needed */
        emit(
            RepoState.completed(
                dao.getAllCharacters(
                    dbOffset,
                    pageSize,
                    CharacterOrderBy.CHARACTER_NAME
                )
            )
        )
    }
}